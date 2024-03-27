package me.gnevilkoko.threads.StreamThread.services;

import me.gnevilkoko.Application;
import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.models.yaml.YamlModel;
import me.gnevilkoko.threads.StreamThread.models.ChannelsSubscriptionData;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsAccesTokenConfig;
import me.gnevilkoko.threads.StreamThread.models.WebSocketBase;
import me.gnevilkoko.threads.StreamThread.models.WebSocketRequests.RequestCentrifugoClientID;
import me.gnevilkoko.threads.StreamThread.models.WebSocketResponses.ResponseCentrifugoClientID;
import okhttp3.Response;
import okhttp3.WebSocket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DonationAlertsWebSocket extends WebSocketBase {
    private DonationAlertsHttpClient httpClient = new DonationAlertsHttpClient();
    private DonationAlertsAccesTokenConfig credentials;
    private ResponseCentrifugoClientID centrifugoClientID;

    public DonationAlertsWebSocket() {
        super("wss://centrifugo.donationalerts.com/connection/websocket");

        YamlModel yaml = new YamlParser().parse(new YamlReader("donationalerts_credentials.yml"));
        credentials = new DonationAlertsAccesTokenConfig(yaml);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        System.out.println(text);
        centrifugoClientID = getGson().fromJson(text, ResponseCentrifugoClientID.class);

        ChannelsSubscriptionData subscriptionData = new ChannelsSubscriptionData(centrifugoClientID.getClientID());
        subscriptionData.addChannel("$alerts:donation_"+credentials.getUserID());

        System.out.println("WORK");
        httpClient.getDonationAlertsChannelToken(credentials.getFullToken(), subscriptionData).thenAccept(t -> System.out.println(t.getChannels().get(0).getName()));

        super.onMessage(webSocket, text);
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        Application.sendMessage("Successfully opened Donation Alerts WebSocket!");
        requestCentrifugoClientID();

        super.onOpen(webSocket, response);
    }

    public void requestCentrifugoClientID(){
        Application.sendMessage("Requesting centrifugo client ID...");

        sendMessage(new RequestCentrifugoClientID(credentials.getSocketConnectionToken()));
    }
}
