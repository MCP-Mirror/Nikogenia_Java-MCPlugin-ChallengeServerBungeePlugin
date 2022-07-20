//PACKAGE
package de.nikocraft.challengeserverbungee;


//IMPORTS
import de.nikocraft.challengeserverbungee.listeners.ConnectionListeners;
import net.md_5.bungee.api.plugin.Plugin;


//MAIN CLASS
public final class Main extends Plugin {

    //VARIABLES

    //The instance of the main
    private static Main instance;


    //OVERRIDE METHODS

    //Called, if the plugin is loading
    @Override
    public void onLoad() {

        //Set the instance to this
        instance = this;

    }

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


    //GETTERS

    //The instance of the main
    public static Main getInstance() { return instance; }

}
