package dev.kemikals.guacamole.messaging;
import net.dv8tion.jda.core.entities.MessageChannel;

@FunctionalInterface
public interface MessageSender {

  /**
   * Sends the given {@link BotMessage}.
   *
   * @param message the message to send
   * @param channel the channel to send it to
   */
  void sendMessage(String message, MessageChannel channel);
}