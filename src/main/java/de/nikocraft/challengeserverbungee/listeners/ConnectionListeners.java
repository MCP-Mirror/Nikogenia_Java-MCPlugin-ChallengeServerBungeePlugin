//PACKAGE
package de.nikocraft.challengeserverbungee.listeners;


//IMPORTS
import de.nikocraft.challengeserverbungee.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.function.Supplier;


//CONNECTION LISTENER CLASS
public class ConnectionListeners implements Listener {

    //EVENT HANDLER METHODS

    //Called, if a player joined
    @EventHandler
    public void onJoin(PostLoginEvent event) {

        //Send join message
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            player.sendMessage(ChatMessageType.CHAT, new TextComponent(ChatColor.GRAY + ">> " + ChatColor.DARK_GREEN + ChatColor.BOLD + event.getPlayer().getName() +
                    ChatColor.RESET + ChatColor.GRAY + " joined the global server!"));
        }
        Main.getInstance().getLogger().info(ChatColor.GRAY + ">> " + ChatColor.DARK_GREEN + ChatColor.BOLD + event.getPlayer().getName() +
                ChatColor.RESET + ChatColor.GRAY + " joined the global server!");

    }



    //Called, if a player quited
    @EventHandler
    public void onQuit(PlayerDisconnectEvent event) {

        //Send quit message
        if (!event.getPlayer().getServer().getInfo().getName().equals("redstone")) {
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                player.sendMessage(ChatMessageType.CHAT, new TextComponent(ChatColor.GRAY + "<< " + ChatColor.DARK_RED + ChatColor.BOLD + event.getPlayer().getName() +
                        ChatColor.RESET + ChatColor.GRAY + " left the global server!"));
            }
        }
        Main.getInstance().getLogger().info(ChatColor.GRAY + "<< " + ChatColor.DARK_RED + ChatColor.BOLD + event.getPlayer().getName() +
                ChatColor.RESET + ChatColor.GRAY + " left the global server!");

        //Set the next connection server to main
        if (!event.getPlayer().getServer().getInfo().getName().equals("redstone")) {
            event.getPlayer().setReconnectServer(ProxyServer.getInstance().getServerInfo("main"));
        } else {
            event.getPlayer().setReconnectServer(ProxyServer.getInstance().getServerInfo("redstone"));
        }

    }

    //Called, on ping
    @EventHandler
    public void onPing(ProxyPingEvent event) {

        ServerPing ping = event.getResponse();

        ping.setDescriptionComponent(new TextComponent(ChatColor.RED.toString() + ChatColor.BOLD + "         Challenge Network" + ChatColor.GOLD + " [1.8 - 1.19]\n" +
                ChatColor.GREEN + ChatColor.ITALIC + "            Thanks to the hole build team! :)"));

        int amount = 0;
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (!player.getServer().getInfo().getName().equals("redstone")) amount++;
        }
        ServerPing.Players players = ping.getPlayers();
        players.setOnline(amount);
        ping.setPlayers(players);

        event.setResponse(ping);

    }

}
