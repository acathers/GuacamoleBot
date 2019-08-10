package dev.kemikals.guacamole.command.commandlistener;

import java.util.HashMap;
import dev.kemikals.guacamole.command.commandloader.CommandLoader;
import dev.kemikals.guacamole.commands.GuacamoleCommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    private HashMap<String, GuacamoleCommand> mentionCommands;
    private HashMap<String, GuacamoleCommand> channelCommands;
    private HashMap<String, GuacamoleCommand> privateCommands;

    public CommandListener() {
      System.out.println("Am I FUCKING RUNNING");
        CommandLoader loader = new CommandLoader();
        mentionCommands = loader.getMentionCommands();
        channelCommands = loader.getChannelCommands();
        privateCommands = loader.getPrivateCommands();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) { 
      event.getChannel().sendMessage("AM I FUCKING WORKING AT ALL!?!?!?").queue();
      System.out.println(event.getChannel());
      if(channelCommands == null) {
        System.out.println("sup");
             return;
      }
        String command = event.getMessage().getContentRaw().trim();  
        if(channelCommands.containsKey("hello")){
            channelCommands.get(command).execute(event.getChannel());
        }

    }


}