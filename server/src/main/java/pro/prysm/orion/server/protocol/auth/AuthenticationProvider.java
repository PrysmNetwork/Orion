package pro.prysm.orion.server.protocol.auth;

import pro.prysm.orion.api.entity.player.GameProfile;

public interface AuthenticationProvider {
    GameProfile join(String serverId, String username);
}
