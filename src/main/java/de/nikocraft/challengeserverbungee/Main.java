//PACKAGE
package de.nikocraft.challengeserverbungee;


//IMPORTS
import de.nikocraft.challengeserverbungee.listeners.ConnectionListeners;
import net.md_5.bungee.api.plugin.Plugin;


//MAIN CLASS
public final class Main extends Plugin {

    //OVERRIDE METHODS

    //Called, if the plugin is enabling
    @Override
    public void onEnable() {

        //Register listeners
        getLogger().info("Register listeners ...");
        getProxy().getPluginManager().registerListener(this, new ConnectionListeners());

        //Send info
        getLogger().info("Plugin enabled.");

    }

    //Called, if the plugin is disabling
    @Override
    public void onDisable() {

        //Send info
        getLogger().info("Plugin disabled.");

    }

}
