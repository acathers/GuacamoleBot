package dev.kemikals.guacamole.command;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class Context {

  private User user;
  private Member member;
  private TextChannel channel;
  
  

  public Context(User user, Member member, TextChannel channel) {
    this.user = user;
    this.member = member;
    this.channel = channel;
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



}
