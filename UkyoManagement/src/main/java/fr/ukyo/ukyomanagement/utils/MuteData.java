package fr.ukyo.ukyomanagement.utils;

import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

public class MuteData {
    private final UUID playerId;
    private final PermissionAttachment attachment;
    private final long unmuteTime;

    public MuteData(UUID playerId, PermissionAttachment attachment, long unmuteTime) {
        this.playerId = playerId;
        this.attachment = attachment;
        this.unmuteTime = unmuteTime;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public PermissionAttachment getAttachment() {
        return attachment;
    }

    public long getUnmuteTime() {
        return unmuteTime;
    }
}

