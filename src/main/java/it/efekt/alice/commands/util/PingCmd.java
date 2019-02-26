package it.efekt.alice.commands.util;

import it.efekt.alice.commands.core.Command;
import it.efekt.alice.commands.core.CommandCategory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PingCmd extends Command {

    public PingCmd(String alias){
        super(alias);
        setCategory(CommandCategory.UTILS);
        setDescription("cmd-ping-description");
    }

    @Override
    public boolean onCommand(MessageReceivedEvent e) {
        e.getChannel().sendMessage("Pong: " + e.getJDA().getPing() + "ms").queue();
        return true;
    }
}
