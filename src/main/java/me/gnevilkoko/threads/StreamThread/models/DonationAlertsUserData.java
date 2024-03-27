package me.gnevilkoko.threads.StreamThread.models;

import java.util.HashMap;

public class DonationAlertsUserData {
    private HashMap<String, Object> data;

    public DonationAlertsUserData(HashMap<String, Object> data) {
        this.data = data;
    }

    public DonationAlertsUserData() {
    }

    public String getSocketConnectionToken(){
        return (String) data.get("socket_connection_token");
    }
    public double getUserID(){
        return (double) data.get("id");
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
