/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supertaxis.model;

/**
 *
 * @author Michaël
 */
public class Vehicle {
    
    long mId;
    private int mSeats;
    private boolean mElectric;
    private boolean mDisabled;
    private Status mStatus;
    private Driver mDriver;
    private LocationEntry mLastLocationEntry;    

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public int getSeats() {
        return mSeats;
    }

    public void setSeats(int seats) {
        this.mSeats = seats;
    }

    public boolean isElectric() {
        return mElectric;
    }

    public void setElectric(boolean electric) {
        this.mElectric = electric;
    }

    public boolean isDisabled() {
        return mDisabled;
    }

    public void setDisabled(boolean disabled) {
        this.mDisabled = disabled;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        this.mStatus = status;
    }

    public Driver getDriver() {
        return mDriver;
    }

    public void setDriver(Driver driver) {
        this.mDriver = driver;
    }

    public LocationEntry getLastLocationEntry() {
        return mLastLocationEntry;
    }

    public void setLastLocationEntry(LocationEntry lastLocationEntry) {
        this.mLastLocationEntry = lastLocationEntry;
    }
    
    

    public static enum Status { FREE, OCCUPIED, BOOKED, DEFECT }
            
}
