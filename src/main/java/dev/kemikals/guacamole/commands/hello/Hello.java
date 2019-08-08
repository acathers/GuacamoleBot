package dev.kemikals.guacamole.commands.hello;

import java.util.List;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Hello extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        List<Member> members = event.getMessage().getMentionedMembers();
        if (!members.isEmpty()) {
            if (members.get(0).getUser() == event.getJDA().getSelfUser()) {
                event.getChannel().sendMessage("Don't @ me " + event.getAuthor().getName()).queue();
            }
        }

    }

}