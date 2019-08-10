package dev.kemikals.guacamole.command.commandlistener;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import dev.kemikals.guacamole.command.Source;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class CommandListener extends ListenerAdapter {
    CommandDispatcher<Source> dispatcher;
    public CommandListener(CommandDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
      
      if (event.getAuthor().isBot() || event.getAuthor().equals(event.getJDA().getSelfUser())) {
        return;
      }
  
      Message message = event.getMessage();
      String command = message.getContentRaw();
      Source source = new Source(message);
  
      ParseResults<Source> parseResults = dispatcher.parse(command, source);

  
      executeCommand(parseResults, source);
    }

    
  private void executeCommand(ParseResults<Source> parseResults, Source source) {
    try {
      dispatcher.execute(parseResults);
    } catch (CommandSyntaxException e) {
      List<ParsedCommandNode<Source>> nodes = parseResults.getContext().getNodes();
      ParsedCommandNode<Source> lastNode = nodes.get(nodes.size() - 1);
    }
  }
}