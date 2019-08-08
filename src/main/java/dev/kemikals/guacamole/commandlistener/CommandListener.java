package dev.kemikals.guacamole.commandlistener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    Properties properties;

    public CommandListener() {

    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        
    }
}