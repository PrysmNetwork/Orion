package pro.prysm.orion.server;

import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.protocol.ServerListResponse;
import pro.prysm.orion.server.command.CommandHandler;
import pro.prysm.orion.server.command.commands.HelpCommand;
import pro.prysm.orion.server.event.EventBus;
import pro.prysm.orion.server.event.EventHandler;
import pro.prysm.orion.server.event.events.OutgoingPacketEvent;
import pro.prysm.orion.server.net.TCPListener;
import pro.prysm.orion.server.protocol.outgoing.status.SLPResponse;
import pro.prysm.orion.server.util.Logger;

import java.util.logging.Level;

public class Orion implements pro.prysm.orion.server.event.Listener {
    private static final Logger logger = new Logger("Orion");
    public static final EventBus EVENT_BUS = new EventBus();
    private final TCPListener TCPListener;
    private final CommandHandler commandHandler;

    public Orion() {
        logger.setLevel(Level.FINE);
        logger.info("Starting Orion...");
        EVENT_BUS.subscribe(this);
        commandHandler = new CommandHandler();
        commandHandler.registerCommand(new HelpCommand());
        TCPListener = new TCPListener();
    }

    @EventHandler
    public void onSLPResponse(OutgoingPacketEvent e, SLPResponse packet) {
        ServerListResponse response = new ServerListResponse();
        response.setProtocolVersion(757);
        response.setServerName("Orion");
        response.setMaxPlayers(20);
        response.setOnlinePlayers(0);
        response.setDescription(Component.text("Test Server"));
        packet.setResponse(response);
    }

    public static Logger getLogger() {
        return logger;
    }

    /**
     * Main Entry Point
     * @param args
     */
    public static void main(String[] args) {
        new Orion();
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public TCPListener getTCPListener() {
        return TCPListener;
    }
}
