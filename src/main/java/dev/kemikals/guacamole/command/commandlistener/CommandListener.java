package dev.kemikals.guacamole.command.commandlistener;

import java.util.HashMap;
import dev.kemikals.guacamole.command.Context;
import dev.kemikals.guacamole.command.commandloader.CommandLoader;
import dev.kemikals.guacamole.commands.GuacamoleCommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
  private HashMap<String, GuacamoleCommand> mentionCommands;
  private HashMap<String, GuacamoleCommand> channelCommands;
  private HashMap<String, GuacamoleCommand> privateCommands;

  public CommandListener(CommandLoader loader) throws InterruptedException {
    mentionCommands = loader.getMentionCommands();
    channelCommands = loader.getChannelCommands();
    privateCommands = loader.getPrivateCommands();
  }

  @Override
  public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
    if (!event.getMessage().getContentRaw().startsWith("!")) {
      return;
    }
    

    String commandKey = event.getMessage().getContentRaw().trim().split(" ")[0].substring(1);
    String arguments = null;
    if (event.getMessage().getContentRaw().contains(" ")) {
      arguments = event.getMessage().getContentRaw()
          .substring(event.getMessage().getContentRaw().indexOf(" "));
    }

    if (channelCommands.containsKey(commandKey)) {
      GuacamoleCommand command = channelCommands.get(commandKey);
      if (event.getMember().hasPermission(command.getPermissionRequired())) {
        
        channelCommands.get(commandKey).execute(new Context(event.getAuthor(), event.getMember(),
            event.getChannel(), event.getMessage(), event.getGuild()), arguments);
      } else {
        System.out.println("nope");
      }
    }

  }
  
  @Override
  public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
    String message = event.getMessage().getContentRaw();
    String arguments = "";
    if(privateCommands.containsKey(message)) {
      privateCommands.get(message).execute(new Context(event.getAuthor(), null, event.getMessage()), arguments);
    }
  
  }

}
