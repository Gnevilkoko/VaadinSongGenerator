package me.gnevilkoko.threads.StreamThread;

import me.gnevilkoko.Application;
import me.gnevilkoko.exceptions.NotEnoughKeyPairsException;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsAccesTokenConfig;
import me.gnevilkoko.threads.StreamThread.services.DonationAlertsHttpClient;
import me.gnevilkoko.threads.ThreadBase;

import java.io.File;
import java.util.Scanner;

public class StreamThread extends ThreadBase {
    private DonationAlertsHttpClient donationAlertsClient = new DonationAlertsHttpClient();

    @Override
    public void run() {
        if(!isDonationTokenExist()){
            generateDonationToken();
        }



        super.run();
    }

    private void generateDonationToken(){
        Scanner scanner = new Scanner(System.in);

        Application.sendMessage("1. Please, go to this link and allow using API:\n"+ donationAlertsClient.getUserAuthorizationDataLink());
        Application.sendMessage("2. Copy full url where you was redirected and paste it here to give us credentials.");
        System.out.print("FULL URL -=> ");

        String fullUrl = scanner.nextLine();

        Application.sendMessage("Parsing...");
        DonationAlertsAccesTokenConfig accesTokenConfig = null;
        try {
            accesTokenConfig = new DonationAlertsAccesTokenConfig(fullUrl);
        } catch (NotEnoughKeyPairsException e) {
            e.printStackTrace();
            Application.stopWithError();
        }
        Application.sendMessage("Parsed successfully");

        accesTokenConfig.save("donationalerts_credentials.yml");
    }

    private boolean isDonationTokenExist(){
        return new File("donationalerts_credentials.yml").exists();
    }
}
