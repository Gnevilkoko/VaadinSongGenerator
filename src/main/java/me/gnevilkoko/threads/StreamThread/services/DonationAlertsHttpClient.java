package me.gnevilkoko.threads.StreamThread.services;

import com.google.gson.Gson;
import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.models.yaml.YamlModel;
import me.gnevilkoko.services.HttpClient;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsChannelToken;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsChannelsSubscriptionData;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsConfig;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsUserData;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class DonationAlertsHttpClient extends HttpClient {

    private DonationAlertsConfig config;

    public DonationAlertsHttpClient() {
        super(new OkHttpClient(), new Gson());

        YamlModel yaml = new YamlParser().parse(new YamlReader("donationalerts_config.yml"));
        config = new DonationAlertsConfig(yaml);
    }

    public String getUserAuthorizationDataLink(){
        HashMap<String, String> params = new HashMap<>();
        params.put("client_id", config.getClientId());
        params.put("redirect_uri", config.getRedirectUri());
        params.put("response_type", "token");
        params.put("scope", "oauth-donation-subscribe+oauth-custom_alert-store+oauth-user-show");

        return addParameters("https://www.donationalerts.com/oauth/authorize", params);
    }

    public CompletableFuture<DonationAlertsUserData> getDonationAlertsUserData(String userToken){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", userToken);

        return get("https://www.donationalerts.com/api/v1/user/oauth", DonationAlertsUserData.class, headers);
    }

    public CompletableFuture<DonationAlertsChannelToken> getDonationAlertsChannelToken(String userToken, DonationAlertsChannelsSubscriptionData subscriptionData){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, getGson().toJson(subscriptionData));

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", userToken);

        return post("https://www.donationalerts.com/api/v1/centrifuge/subscribe", body, headers, DonationAlertsChannelToken.class);
    }
}
