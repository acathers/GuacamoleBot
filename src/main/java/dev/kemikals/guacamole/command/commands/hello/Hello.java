package dev.kemikals.guacamole.command.commands.hello;

import java.util.List;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import dev.kemikals.guacamole.command.Source;
import dev.kemikals.guacamole.command.commandloader.LoadCommand;
import dev.kemikals.guacamole.command.commands.*;

@LoadCommand
public class Hello implements GuacamoleCommand {

	@Override
	public LiteralCommandNode<Source> getCommand(CommandDispatcher<Source> dispatcher) {
        
        return literal("hello").executes(sendMessage())
       
        .build();
  }

  private static Command<Source> sendMessage() {
    return context -> sendMessage(context);
  }

  private static int sendMessage(CommandContext<Source> context) {
    Source source = context.getSource();
    source.getMessageSend
        .sendMessage("Hello", source.getChannel());
    return 0;
  }
}



  

  public static LiteralArgumentBuilder<Source> literal(String literal) {
    return LiteralArgumentBuilder.literal(literal);
  }
    
   /* @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        List<Member> members = event.getMessage().getMentionedMembers();
        if (!members.isEmpty()) {
            if (members.get(0).getUser() == event.getJDA().getSelfUser()) {
                event.getChannel().sendMessage("Don't @ me " + event.getAuthor().getName()).queue();
            }
        }

    }*/

}