package me.gnevilkoko.threads.StreamThread;

import me.gnevilkoko.Application;
import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.exceptions.NotEnoughKeyPairsException;
import me.gnevilkoko.models.yaml.YamlModel;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsAccesTokenConfig;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsUserData;
import me.gnevilkoko.threads.StreamThread.services.DonationAlertsHttpClient;
import me.gnevilkoko.threads.StreamThread.services.DonationAlertsWebSocket;
import me.gnevilkoko.threads.ThreadBase;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class StreamThread extends ThreadBase {
    private DonationAlertsHttpClient donationAlertsClient = new DonationAlertsHttpClient();

    @Override
    public void run() {
        if(!isDonationTokenExist()){
            getDonationCredentials();
        }

        YamlModel yamlModel = new YamlParser().parse(new YamlReader("donationalerts_credentials.yml"));
        DonationAlertsAccesTokenConfig donationAlertsAccesTokenConfig = new DonationAlertsAccesTokenConfig(yamlModel);

        DonationAlertsWebSocket donationAlertsWebSocket = new DonationAlertsWebSocket();
        super.run();
    }

    private void getDonationCredentials(){
        Scanner scanner = new Scanner(System.in);

        Application.sendMessage("1. Please, go to this link and allow using API:\n"+ donationAlertsClient.getUserAuthorizationDataLink());
        Application.sendMessage("2. Copy full url where you was redirected and paste it here to give us credentials.");
        System.out.print("FULL URL -=> ");

        String fullUrl = scanner.nextLine();

        Application.sendMessage("Parsing...");
        try {
            DonationAlertsAccesTokenConfig accesTokenConfig = new DonationAlertsAccesTokenConfig(fullUrl);
            DonationAlertsUserData user = donationAlertsClient.getDonationAlertsUserData(accesTokenConfig.getFullToken()).get();

            accesTokenConfig.put("socket_connection_token", user.getData().getSocketConnectionToken());
            accesTokenConfig.put("user_id", user.getData().getId());
            accesTokenConfig.save("donationalerts_credentials.yml");
        } catch (NotEnoughKeyPairsException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Application.stopWithError();
        }
        Application.sendMessage("Parsed successfully");
    }

    private boolean isDonationTokenExist(){
        return new File("donationalerts_credentials.yml").exists();
    }
}
