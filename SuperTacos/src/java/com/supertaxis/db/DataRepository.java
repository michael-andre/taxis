package com.supertaxis.db;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.supertaxis.model.Vehicle;
import com.supertaxis.model.LocationEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michaël
 */
public class DataRepository {
    
    private static DataRepository sInstance;
    
    public static DataRepository getInstance() {
        if (sInstance == null) {
            sInstance = new DataRepository();
        }
        return sInstance;
    }
    
    private DataRepository() { }
    
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/super_tacos?characterEncoding=UTF-8";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    
    
    private Connection openConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();        
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new IllegalStateException("JDBC driver failed to load", ex);
        }
    }
    
    public List<Vehicle> getVehicles() throws SQLException {
        /*Connection connection = openConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM vehiclesa");
            try {
                ResultSet res = stmt.executeQuery();
                try {
                    while (res.next()) {
                        // Do something with line
                    }
                } finally {
                    res.close();
                }
            } finally {
                stmt.close();
            }
        } finally {
            connection.close();
        }*/
        
        try (Connection connection = openConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "SELECT *, X(location) AS lng, Y(location) AS lat, timestamp"
                    + " FROM vehicles"
                    + " LEFT JOIN vehicle_locations ON id = vehicle_id"
                    + " WHERE timestamp = (SELECT MAX(timestamp) FROM vehicle_locations"
                    + " GROUP BY id"
            )) {
                try (ResultSet res = stmt.executeQuery()) {
                    List<Vehicle> list = new ArrayList<>();
                    while (res.next()) {
                        Vehicle v = new Vehicle();
                        v.setId(res.getInt("id"));
                        v.setSeats(res.getInt("seats"));
                        v.setDisabled(res.getBoolean("disabled"));
                        v.setElectric(res.getBoolean("electric"));
                        v.setStatus(Vehicle.Status.valueOf(res.getString("status").toUpperCase()));
                        v.setLastLocationEntry(new LocationEntry(
                                res.getDouble("lat"),
                                res.getDouble("lng"),
                                res.getDate("timestamp")
                        ));
                        list.add(v);
                    }
                    return list;
                }
            }
        }
        
    }
    
    public void updateVehicleLocation(long vehicleId, double lat, double lng) throws SQLException {
        try (Connection connection = openConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO vehicle_locations (vehicle_id, timestamp, location) VALUES"
                    + " (?, NOW(), POINT(?, ?));"
            )) {
                stmt.setLong(1, vehicleId);
                stmt.setDouble(2, lng);
                stmt.setDouble(3, lat);
                stmt.execute();
            }
        }
            
    }

}
