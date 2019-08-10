package dev.kemikals.guacamole;

import dev.kemikals.guacamole.command.commandlistener.CommandListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class App {
    public static JDA jda;

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        jda = new JDABuilder(config.getDiscordKey()).addEventListener(new CommandListener()).build();
        jda.awaitReady();
        
        
    }
}
