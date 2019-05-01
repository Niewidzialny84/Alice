package it.efekt.alice.commands.util;

import it.efekt.alice.commands.core.Command;
import it.efekt.alice.commands.core.CommandCategory;
import it.efekt.alice.core.AliceBootstrap;
import it.efekt.alice.db.TextChannelConfig;
import it.efekt.alice.lang.Message;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.util.concurrent.TimeUnit;

public class ImgOnlyCmd extends Command {

    public ImgOnlyCmd(String alias) {
        super(alias);
        addPermission(Permission.ADMINISTRATOR);
        setCategory(CommandCategory.DISCORD_ADMIN_UTILS);
        setDescription(Message.CMD_IMGONLY_DESC);
        setFullUsageInfo(Message.CMD_IMGONLY_FULL_USAGE_INFO);
    }

    @Override
    public boolean onCommand(MessageReceivedEvent e) {
        TextChannelConfig textChannelConfig = AliceBootstrap.alice.getTextChannelConfigManager().get(e.getTextChannel());
        e.getMessage().delete().complete();

        if (textChannelConfig.isImgOnly()){
            textChannelConfig.setImgOnly(false);
            e.getTextChannel().sendMessage("disabled imgOnly").complete().delete().completeAfter(1, TimeUnit.SECONDS);
        } else {
            textChannelConfig.setImgOnly(true);
            e.getTextChannel().sendMessage("enabled imgOnly").queue();
        }
        return true;
    }



}