/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supertaxis.db;

import com.supertaxis.model.Vehicle;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michaël
 */
public class DataRepositoryTest {
    
    public DataRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class DataRepository.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DataRepository result = DataRepository.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getVehicles method, of class DataRepository.
     */
    @Test
    public void testGetVehicles() throws Exception {
        System.out.println("getVehicles");
        DataRepository instance = DataRepository.getInstance();
        List<Vehicle> expResult = new ArrayList<>();
        List<Vehicle> result = instance.getVehicles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateVehicleLocation method, of class DataRepository.
     */
    @Test
    public void testUpdateVehicleLocation() throws Exception {
        System.out.println("updateVehicleLocation");
        long vehicleId = 0L;
        double lat = 0.0;
        double lng = 0.0;
        DataRepository instance = null;
        instance.updateVehicleLocation(vehicleId, lat, lng);
        // TODO review the generated test code and remove the default call to fail.
        instance.updateVehicleLocation(1, 91, 0);
        fail("updateVehicleLocation");
       
    }
    
}
