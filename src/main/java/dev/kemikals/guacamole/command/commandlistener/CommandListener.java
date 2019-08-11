package dev.kemikals.guacamole.command.commandlistener;

import java.util.HashMap;
import dev.kemikals.guacamole.command.Context;
import dev.kemikals.guacamole.command.commandloader.CommandLoader;
import dev.kemikals.guacamole.commands.GuacamoleCommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
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
    
    String command = event.getMessage().getContentRaw().trim();

    if (channelCommands.containsKey(command)) {
      channelCommands.get(command).execute(new Context(event.getAuthor(), event.getMember(), event.getChannel()));
    }

  }

}
