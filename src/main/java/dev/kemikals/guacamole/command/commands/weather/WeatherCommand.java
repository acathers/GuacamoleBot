package dev.kemikals.guacamole.command.commands.weather;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class WeatherCommand extends ListenerAdapter {
    private String apiKey;

    public WeatherCommand(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();

        if (event.getMessage().getContentRaw().startsWith("!weather")) {
            String[] command = event.getMessage().getContentRaw().split(" ");
            if (command.length == 2) {
                String weatherQuery = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=imperial&APPID=%s";
                Gson gson = new Gson();
                Weather weather = gson.fromJson(getJson(weatherQuery, command[1], apiKey), Weather.class);
                channel.sendMessage("City: " + weather.getName()).queue();
                channel.sendMessage("Current Temp: " + weather.getMain().getTemp()).queue();
                channel.sendMessage("  Max: " + weather.getMain().getTempMax()).queue();
                channel.sendMessage("  Min: " + weather.getMain().getTempMin()).queue();
                channel.sendMessage("Visibility: " + weather.getVisibility()).queue();
                channel.sendMessage("Wind Speed: " + weather.getWind().getSpeed()).queue();
            } else {
                channel.sendMessage("Command only takes one paramter, city, try again").queue();
            }
        }
    }

    public static String getJson(String query, String input, String apikey) {
        String inline = "";

        try {
            URL url = new URL(String.format(query, input.trim(), apikey));

            Scanner sc = new Scanner(url.openStream());

            while (sc.hasNext()) {
                inline += sc.nextLine();
            }
            sc.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inline;

    }
}