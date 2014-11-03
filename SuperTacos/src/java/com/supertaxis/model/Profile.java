package com.supertaxis.model;

import java.util.Map;

/**
 *
 * @author Michaël
 */
public class Profile {
    
    private boolean mDisabled;
    private boolean mPrefersElectric;
    private Map<Address.Type, Address> mAddresses;
    private String mLanguage;

    public boolean isDisabled() {
        return mDisabled;
    }

    public void setDisabled(boolean disabled) {
        this.mDisabled = disabled;
    }

    public boolean isPrefersElectric() {
        return mPrefersElectric;
    }

    public void setPrefersElectric(boolean prefersElectric) {
        this.mPrefersElectric = prefersElectric;
    }

    public Map<Address.Type, Address> getAddresses() {
        return mAddresses;
    }

    public void addAddress(Address.Type type, Address address) {
        this.mAddresses.put(type, address);
    }
    
    public void removeAddress(Address.Type type) {
        this.mAddresses.remove(type);
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        this.mLanguage = language;
    }
    
}
