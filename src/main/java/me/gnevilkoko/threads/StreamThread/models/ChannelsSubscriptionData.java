package me.gnevilkoko.threads.StreamThread.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChannelsSubscriptionData {
    private ArrayList<String> channels;

    @SerializedName("client")
    private String centrifugoClientID;

    public ChannelsSubscriptionData(String centrifugoClientID) {
        this.channels = new ArrayList<>();
        this.centrifugoClientID = centrifugoClientID;
    }

    public ChannelsSubscriptionData() {
    }

    public void addChannel(String name){
        channels.add(name);
    }

    public ArrayList<String> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<String> channels) {
        this.channels = channels;
    }

    public String getCentrifugoClientID() {
        return centrifugoClientID;
    }

    public void setCentrifugoClientID(String centrifugoClientID) {
        this.centrifugoClientID = centrifugoClientID;
    }
}
