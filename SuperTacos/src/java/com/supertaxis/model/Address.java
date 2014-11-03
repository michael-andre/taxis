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
public class Address {
    
    private String mNum;
    private String mStreet;
    private String mZipCode;
    private String mCity;
    
    public enum Type {
        HOME,
        WORK,
        OTHER;
    }

    public Address(String num, String street, String zipCode, String city) {
        mNum = num;
        mStreet = street;
        mZipCode = zipCode;
        mCity = city;
    }
    
}
