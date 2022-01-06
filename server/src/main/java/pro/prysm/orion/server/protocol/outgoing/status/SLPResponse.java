package pro.prysm.orion.server.protocol.outgoing.status;

import pro.prysm.orion.api.protocol.ServerListResponse;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class SLPResponse extends OutgoingPacket implements pro.prysm.orion.api.protocol.outgoing.status.SLPResponse {
    private ServerListResponse response;

    public SLPResponse() {
        id = 0x00;
    }

    public SLPResponse(ServerListResponse response) {
        this();
        this.response = response;
    }

    @Override
    public ServerListResponse getResponse() {
        return response;
    }

    @Override
    public void setResponse(ServerListResponse response) {
        this.response = response;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(response.toJsonString());
    }
}
