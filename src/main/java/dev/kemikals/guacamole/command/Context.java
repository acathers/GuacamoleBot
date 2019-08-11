package dev.kemikals.guacamole.command;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class Context {

  private User user;
  private Member member;
  private TextChannel channel;
  private Message message;
  private Guild guild;



  public Context(User user, Member member, TextChannel channel, Message message, Guild guild) {
    this.user = user;
    this.member = member;
    this.channel = channel;
    this.message = message;
    this.guild = guild;
  }

  public Context(User user, Member member, TextChannel channel) {
    this(user, member, channel, null, null);
  }

  public Context(User user, Member member, Message message) {
    this(user, member, null, message, null);
  }


  public User getUser() {
    return user;
  }

  public Member getMember() {
    return member;
  }

  public TextChannel getChannel() {
    return channel;
  }

  public Message getMessage() {
    return message;
  }

  public Guild getGuild() {
    return guild;
  }



}
