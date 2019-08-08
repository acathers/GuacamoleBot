package dev.kemikals.guacamole.commandlistener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import com.mojang.brigadier.Command;

public class CommandListener extends ListenerAdapter {
    public static final String COMMAND_PREFIX = "!";

    private Properties properties;
    private List<String> validCommands;

    public CommandListener() {

    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (!message.startsWith(COMMAND_PREFIX)) {
            return;
        }
        Command command = new Command(parseCommand(message), parseArguments(message));

    }

    private String parseCommand(String message) {
        return message.substring(1, message.indexOf(" "));
    }

    private String parseArguments(String message) {
        return message.substring(message.indexOf(" ") + 1);
    }

    public void executeCommand(Command command){
        if(validCommands.contains(command.getCommand())){
            switch()
        }
    }
}