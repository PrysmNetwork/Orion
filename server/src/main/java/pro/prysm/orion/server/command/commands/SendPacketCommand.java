package pro.prysm.orion.server.command.commands;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.command.Command;
import pro.prysm.orion.server.net.ChannelHandler;

import java.util.logging.Level;

/**
 * @author 254n_m
 * @since 12/18/21 / 8:57 PM
 * This file was created as a part of Orion
 */
public class SendPacketCommand extends Command {
    private final ChannelHandler channelHandler;
    public SendPacketCommand(ChannelHandler channelHandler) {
        super("sendpacket", "Sends a packet");
        this.channelHandler = channelHandler;
    }

    @Override
    public void execute(String[] args, String name) {
        if (args.length > 2) {

        } else Orion.getLogger().log(Level.WARNING, "sendpacket <PacketID> <PacketParameters <DataType::Value>>");
    }
}
