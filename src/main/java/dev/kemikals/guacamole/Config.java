package dev.kemikals.guacamole;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String discordKey;
    private String weatherKey;

    public Config() {

        try (InputStream input = new FileInputStream("conf/settings.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            discordKey = properties.getProperty("discord.key");
            weatherKey = properties.getProperty("weather.key");

        } catch (IOException e) {
            System.out.println("Cannot read settings file");
        }

    }

    public String getDiscordKey() {
        return discordKey;
    }

    public String getWeatherKey() {
        return weatherKey;
    }
}