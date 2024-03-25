package me.gnevilkoko.threads.StreamThread.services;

import com.google.gson.Gson;
import me.gnevilkoko.services.HttpClient;
import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.models.yaml.YamlModel;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsConfig;
import okhttp3.OkHttpClient;

import java.util.HashMap;

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
        params.put("scope", "oauth-donation-subscribe+oauth-custom_alert-store");

        return addParameters("https://www.donationalerts.com/oauth/authorize", params);
    }
}
