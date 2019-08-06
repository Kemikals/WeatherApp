package dev.kemikals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Config {

    private String key;

    public Config() {

        try {
            loadConfig();
        } catch (IOException e) {
            System.err.println("Cannot read from config file, exiting...");
            System.exit(0);
        }
    }

    public void loadConfig() throws IOException {
        Path path = Path.of("conf", "settings.conf");
        List<String> configContents = null;
        configContents = Files.readAllLines(path);

        for (String setting : configContents) {
            if (setting.startsWith("api_key")) {
                key = setting.split("=")[1].trim();
            }
        }
    }

    public String getKey() {
        return key;
    }

}