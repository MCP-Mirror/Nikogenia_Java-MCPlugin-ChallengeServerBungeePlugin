//PACKAGE
package de.nikocraft.challengeserverbungee.listeners;


//IMPORTS
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


//CONNECTION LISTENER CLASS
public class ConnectionListeners implements Listener {

    //EVENT HANDLER METHODS

    //Called, if a player logged in the server
    @EventHandler
    public void onLogin(PreLoginEvent event) {

        //If there is no main server, disconnect
        if (ProxyServer.getInstance().getServerInfo("main") == null) event.getConnection().disconnect(new TextComponent("Connection failed! Server uncompleted! Missing main server ..."));

    }

    //Called, if a player joined
    @EventHandler
    public void onJoin(PostLoginEvent event) {

        //Send join message
        ProxyServer.getInstance().broadcast(new TextComponent(ChatColor.GRAY + ">> " + ChatColor.DARK_GREEN + ChatColor.BOLD + event.getPlayer().getName() +
                ChatColor.RESET + ChatColor.GRAY + " joined the global server!"));

    }

    //Called, if a player quited
    @EventHandler
    public void onQuit(PlayerDisconnectEvent event) {

        //Send quit message
        ProxyServer.getInstance().broadcast(new TextComponent(ChatColor.GRAY + "<< " + ChatColor.DARK_RED + ChatColor.BOLD + event.getPlayer().getName() +
                ChatColor.RESET + ChatColor.GRAY + " left the global server!"));

        //Set the next connection server to main
        event.getPlayer().setReconnectServer(ProxyServer.getInstance().getServerInfo("main"));

    }

}
