package pro.prysm.orion.server.net.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.Protocol;

import java.net.SocketAddress;
import java.util.HashMap;

@ChannelHandler.Sharable
public class ChannelHandler extends ChannelInboundHandlerAdapter {
    private final HashMap<Integer, Connection> connections;
    private final Protocol protocol;

    public ChannelHandler(Protocol protocol) {
        connections = new HashMap<>();
        this.protocol = protocol;
    }

    public HashMap<Integer, Connection> getConnections() {
        return connections;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        ctx.channel().config().setOption(ChannelOption.TCP_NODELAY, true);
        connections.put(ctx.channel().remoteAddress().hashCode(), new Connection(ctx, protocol));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        connections.remove(ctx.channel().remoteAddress().hashCode());
    }

    public Connection getConnection(SocketAddress remoteAddress) {
        return connections.get(remoteAddress.hashCode());
    }
}
