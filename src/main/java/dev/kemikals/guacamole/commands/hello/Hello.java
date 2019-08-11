package dev.kemikals.guacamole.commands.hello;

import dev.kemikals.guacamole.command.commandloader.annotations.ChannelCommand;
import dev.kemikals.guacamole.command.commandloader.annotations.MentionCommand;
import dev.kemikals.guacamole.commands.GuacamoleCommand;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

@MentionCommand
@ChannelCommand
public class Hello implements GuacamoleCommand {

    private final String commandName = "hello";

    /*
     * @Override public void onGuildMessageReceived(GuildMessageReceivedEvent event)
     * { List<Member> members = event.getMessage().getMentionedMembers(); if
     * (!members.isEmpty()) { if (members.get(0).getUser() ==
     * event.getJDA().getSelfUser()) {
     * event.getChannel().sendMessage("Don't Fn @ me " +
     * event.getAuthor().getName()).queue(); } }
     * 
     * }
     */

    public String getCommandName() {
        return commandName;
    }

    @Override
    public void execute(TextChannel channel) {
        channel.sendMessage("Hello").queue();
    }

    @Override
    public void execute(User user) {

        user.openPrivateChannel().queue((channel) -> channel.sendMessage("Hello" + user.getName()).queue());

    }

}