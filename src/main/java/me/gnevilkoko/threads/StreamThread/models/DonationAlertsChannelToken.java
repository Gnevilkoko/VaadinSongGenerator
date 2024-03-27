package me.gnevilkoko.threads.StreamThread.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class DonationAlertsChannelToken {
    private Map<String, Channel> channels;

    public DonationAlertsChannelToken(Map<String, Channel> channels) {
        this.channels = channels;
    }

    public Map<String, Channel> getChannels() {
        return channels;
    }

    public void setChannels(Map<String, Channel> channels) {
        this.channels = channels;
    }

    public class Channel {
        @SerializedName("channel")
        private String name;
        private String token;

        public Channel(String name, String token) {
            this.name = name;
            this.token = token;
        }

        public Channel() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
