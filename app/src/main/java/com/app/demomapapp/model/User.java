package com.app.demomapapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "user_fullName")
    public String userName;

    @ColumnInfo(name = "user_email")
    public String userEmail;

    @ColumnInfo(name = "user_address")
    public String userAddress;

    @ColumnInfo(name = "user_latitude")
    public String userLatitude;

    @ColumnInfo(name = "user_longitude")
    public String userLongitude;

    public User(int uid, String userName, String userEmail, String userAddress, String userLatitude, String userLongitude) {
        this.uid = uid;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(String userLatitude) {
        this.userLatitude = userLatitude;
    }

    public String getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(String userLongitude) {
        this.userLongitude = userLongitude;
    }
}
