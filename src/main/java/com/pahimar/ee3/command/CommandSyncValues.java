package com.pahimar.ee3.command;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSyncEnergyValues;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.PlayerHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

import java.util.List;

public class CommandSyncValues extends CommandEE
{
    @Override
    public String getCommandName()
    {
        return "sync-values";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            if (args[1].equalsIgnoreCase("self"))
            {
                LogHelper.info(String.format("Syncing EnergyValues with player '%s' at their request", commandSender.getCommandSenderName()));
                PacketHandler.INSTANCE.sendTo(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()), (EntityPlayerMP) commandSender);
                commandSender.addChatMessage(new ChatComponentTranslation("command.ee3.sync-values.self.success"));
            }
            else if (args[1].equalsIgnoreCase("all") && PlayerHelper.isPlayerOp((EntityPlayer) commandSender))
            {
                LogHelper.info(String.format("Syncing EnergyValues with all players at %s's request", commandSender.getCommandSenderName()));
                PacketHandler.INSTANCE.sendToAll(new MessageSyncEnergyValues(EnergyValueRegistry.getInstance()));
                func_152373_a(commandSender, this, "command.ee3.sync-values.all.success", new Object[]{commandSender.getCommandSenderName()});
            }
            else
            {
                throw new WrongUsageException("command.ee3.sync-values.usage");
            }
        }
        else
        {
            throw new WrongUsageException("command.ee3.sync-values.usage");
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            return getListOfStringsMatchingLastWord(args, "self", "all");
        }

        return null;
    }
}