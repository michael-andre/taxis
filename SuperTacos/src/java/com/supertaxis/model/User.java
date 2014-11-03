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
public class User {
    
    private String mLogin;
    private String mPassword;
    private Profile mProfile;

    public User(String login, String password) {
        this.mLogin = login;
        this.mPassword = password;
    }
    
    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        this.mLogin = login;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }
    
}
