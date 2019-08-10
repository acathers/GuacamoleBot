package dev.kemikals.guacamole;

import dev.kemikals.guacamole.messaging.MessageSender;

public class Context {

    private MessageSender sender;

    public Context(MessageSender sender){
        this.sender = sender;
    }

    public MessageSender getSender() {
        return sender;
    }

    public void setSender(MessageSender sender) {
        this.sender = sender;
    }

    
}