package dev.kemikals.guacamole;

import dev.kemikals.guacamole.command.commandlistener.CommandListener;
import dev.kemikals.guacamole.command.commandloader.CommandLoader;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class App {
    public static JDA jda;
    public static Config config;

    public static void main(String[] args) throws Exception {
        config = new Config();
        CommandLoader loader = new CommandLoader();
        jda = new JDABuilder(config.getDiscordKey()).addEventListener(new CommandListener(loader)).build();
        jda.awaitReady();
    }
}
