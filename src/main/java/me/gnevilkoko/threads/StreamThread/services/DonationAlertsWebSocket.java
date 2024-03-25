package me.gnevilkoko.threads.StreamThread.services;

import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.models.yaml.YamlModel;
import me.gnevilkoko.threads.StreamThread.models.DonationAlertsConfig;

public class DonationAlertsWebSocket {
    private DonationAlertsConfig config;

    public DonationAlertsWebSocket() {
        YamlModel yaml = new YamlParser().parse(new YamlReader("donationalerts_config.yml"));
        config = new DonationAlertsConfig(yaml);
    }
}
