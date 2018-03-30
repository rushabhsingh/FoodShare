package com.ksapps.shelfshare;

/**
 * Created by Kshitij on 20-03-2018.
 */

public class AdInfo {
    String username;
    String description;
    String days;
    String freeDonate;

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    String adName;
    public AdInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getFreeDonate() {
        return freeDonate;
    }

    public void setFreeDonate(String freeDonate) {
        this.freeDonate = freeDonate;
    }

}
