package dev.kemikals.guacamole.command;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Source {

    private User user;
    private Message message;
    private MessageChannel channel;

    public Source(Message message) {
        this.message = message;
        this.user = message.getAuthor();
        this.channel = message.getChannel();
    }

    public User getUser() {
        return user;
    }

    public Member getMember() {
        return getMessage().getGuild().getMember(getUser());
    }

    public Message getMessage() {
        return message;
    }

    public MessageChannel getChannel() {
        return channel;
    }
}