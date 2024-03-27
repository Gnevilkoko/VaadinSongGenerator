package me.gnevilkoko.threads.StreamThread.models;

import com.google.gson.annotations.SerializedName;

public class DonationAlertsUserData {
    private Data data;

    public DonationAlertsUserData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private int id;

        @SerializedName("socket_connection_token")
        private String socketConnectionToken;

        public Data(int id, String socketConnectionToken) {
            this.id = id;
            this.socketConnectionToken = socketConnectionToken;
        }

        public String getSocketConnectionToken() {
            return socketConnectionToken;
        }

        public int getId() {
            return id;
        }



        public void setId(int id) {
            this.id = id;
        }

        public void setSocketConnectionToken(String socketConnectionToken) {
            this.socketConnectionToken = socketConnectionToken;
        }
    }
}
