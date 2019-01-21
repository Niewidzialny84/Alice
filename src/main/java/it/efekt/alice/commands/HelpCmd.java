package it.efekt.alice.commands;

import it.efekt.alice.core.AliceBootstrap;
import it.efekt.alice.core.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCmd extends Command {

    public HelpCmd(String alias){
        super(alias);
        this.setDesc("Wyświetla pomoc");
    }

    @Override
    public void onCommand(MessageReceivedEvent e) {
        String prefix = AliceBootstrap.alice.getConfig().getPrefix();

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("Alice bot - Pomoc");


        for (Command cmd : AliceBootstrap.alice.getCmdManager().getCommands().values()){
            embedBuilder.addField(prefix + cmd.getAlias(), cmd.getDesc(), false);
        }
        e.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
