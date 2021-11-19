package me.jonakls.api;

import org.bukkit.entity.Player;

public interface NMS {

    void sendTitle(Player player, Title title);
    void sendActionbar(Player player, ActionBar actionBar);
    void sendPacket(Player player, Object packet);
}
