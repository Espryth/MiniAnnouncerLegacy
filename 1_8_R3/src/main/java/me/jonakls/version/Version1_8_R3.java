package me.jonakls.version;

import me.jonakls.api.ActionBar;
import me.jonakls.api.NMS;
import me.jonakls.api.Title;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Version1_8_R3 implements NMS {

    @Override
    public void sendTitle(Player player, Title title) {

        IChatBaseComponent titleText = new ChatMessage(title.getTitle());
        IChatBaseComponent subTitleText = new ChatMessage(title.getSubTitle());

        PacketPlayOutTitle outTitle = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.TITLE,
                titleText,
                title.getFadeIn(),
                title.getStay(),
                title.getFadeOut()
        );

        PacketPlayOutTitle outSubTitle = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                subTitleText,
                title.getFadeIn(),
                title.getStay(),
                title.getFadeOut()
        );

        sendPacket(player, outTitle);
        sendPacket(player, outSubTitle);

    }

    @Override
    public void sendActionbar(Player player, ActionBar actionBar) {
        IChatBaseComponent actionBarComponent = new IChatBaseComponent.ChatSerializer().a(
                "{\"text\":\"" + actionBar.getActionBar() + "\"}"
        );

        PacketPlayOutChat chat = new PacketPlayOutChat(actionBarComponent, (byte)2);

        sendPacket(player, chat);

    }

    @Override
    public void sendPacket(Player player, Object packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet) packet);
    }
}
