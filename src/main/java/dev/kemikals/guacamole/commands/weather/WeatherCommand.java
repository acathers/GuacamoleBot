package dev.kemikals.guacamole.commands.weather;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;
import dev.kemikals.guacamole.App;
import dev.kemikals.guacamole.command.Context;
import dev.kemikals.guacamole.command.commandloader.annotations.ChannelCommand;
import dev.kemikals.guacamole.commands.GuacamoleCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;

@ChannelCommand
public class WeatherCommand implements GuacamoleCommand {
  private final String commandName = "weather";
  private String apiKey = App.config.getWeatherKey();

  @Override
  public void execute(Context event, String arguments) {
    TextChannel channel = event.getChannel();
    String[] command = event.getMessage().getContentRaw().split(" ");

    String weatherQuery =
        "http://api.openweathermap.org/data/2.5/weather?q=%s&units=imperial&APPID=%s";
    Gson gson = new Gson();
    Weather weather =
        gson.fromJson(getJson(weatherQuery, arguments, apiKey, channel), Weather.class);
    if (weather == null)
      return;
    channel.sendMessage("```City: " + weather.getName() + "\nCurrent Temp: " + weather.getMain().getTemp() + "\nMax: "
        + weather.getMain().getTempMax() + "\nMin: " + weather.getMain().getTempMin()
        + "\nVisibility: " + weather.getVisibility() + "\nWind Speed: " + weather.getWind().getSpeed() + "```")
        .queue();

  }


  public static String getJson(String query, String input, String apikey, TextChannel channel) {
    String inline = "";

    try {
      URL url = new URL(String.format(query, input.trim(), apikey));

      Scanner sc = new Scanner(url.openStream());

      while (sc.hasNext()) {
        inline += sc.nextLine();
      }
      sc.close();

    } catch (FileNotFoundException e) {
      channel.sendMessage("City not found").queue();
    }

    catch (IOException e) {

      e.printStackTrace();
    }

    return inline;

  }


  @Override
  public String getCommandName() {
    return commandName;
  }


  @Override
  public Permission getPermissionRequired() {
    return null;
  }
}
