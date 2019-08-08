package dev.kemikals.guacamole;

import dev.kemikals.guacamole.commands.hello.Hello;
import dev.kemikals.guacamole.commands.trivia.Trivia;
import dev.kemikals.guacamole.commands.weather.WeatherCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class App {
    public static JDA jda;

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        jda = new JDABuilder(AccountType.BOT).setToken(config.getDiscordKey()).build();
        jda.awaitReady();    
        jda.addEventListener(new Hello());
        jda.addEventListener(new Trivia());
        jda.addEventListener(new WeatherCommand(config.getWeatherKey()));
    }
}
