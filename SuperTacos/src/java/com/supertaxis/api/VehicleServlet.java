/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supertaxis.api;

import com.supertaxis.db.DataRepository;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michaël
 */
public class VehicleServlet extends HttpServlet {

    static private final Pattern LOCATION_PATTERN = Pattern.compile("^(\\d+\\.\\d+),(\\d+\\.\\d+)$");

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        long vehicleId;
        try {
            vehicleId = Long.parseLong(request.getPathInfo().substring(1));
        } catch (NumberFormatException ex) {
            throw new ServletException("Invalid vehicle id", ex);
        }
        
        double lat, lng;
        /*String[] location = request.getParameter("location").split(",");
        try {
            lat = Double.parseDouble(location[0]);
            lng = Double.parseDouble(location[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            throw new ServletException("Invalid location", ex);
        }*/
        
        Matcher matcher = LOCATION_PATTERN.matcher(request.getParameter("location"));
        if (matcher.matches()) {
            try {
                lat = Double.parseDouble(matcher.group(1));
                lng = Double.parseDouble(matcher.group(2));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                throw new ServletException("Invalid location", ex);
            }
        } else {
            throw new ServletException("Invalid location");
        }
        
        try {
            DataRepository.getInstance().updateVehicleLocation(vehicleId, lat, lng);
        } catch (SQLException ex) {
            Logger.getLogger(VehicleServlet.class.getName()).log(Level.SEVERE, "Failed to insert location", ex);
            throw new ServletException(ex);
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
