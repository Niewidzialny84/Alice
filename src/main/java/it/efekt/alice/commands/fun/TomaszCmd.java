package it.efekt.alice.commands.fun;

import it.efekt.alice.commands.core.Command;
import it.efekt.alice.commands.core.CommandCategory;
import it.efekt.alice.core.AliceBootstrap;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class TomaszCmd extends Command {

    public TomaszCmd(String alias) {
        super(alias);
        setDescription("Tomasz");
        setCategory(CommandCategory.FUN);
    }

    @Override
    public void onCommand(MessageReceivedEvent e) {
        try {
            e.getChannel().sendFile(AliceBootstrap.class.getClassLoader().getResourceAsStream("assets/images/tomasz.jpg"), "tomasz.jpg").queue();
        } catch (NullPointerException exc) {
            e.getChannel().sendMessage("Nie znaleziono pliku :(").queue();
        }
    }
}