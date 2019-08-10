package dev.kemikals.guacamole.command;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;


public class CommandSource {

  private Event event;
  private User user;
  private Message message;

  public CommandSource(Event event) {
    this.event = event;
  }

  public User getUser() {
    return user;
  }

  public MessageChannel getChannel(){
      if(event instanceof GuildMessageReceivedEvent){
          return ((GuildMessageReceivedEvent)event).getChannel();
      }
      throw new UnsupportedOperationException("This source did not come from a channel");
  }

  public Member getMember() {
    return getMessage().getGuild().getMember(getUser());
  }

  public Message getMessage() {
    return message;
  }

}
