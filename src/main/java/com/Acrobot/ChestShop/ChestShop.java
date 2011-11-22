package com.Acrobot.ChestShop;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.ChestShop.Modules.TestModule.TestModule;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Acrobot
 */
public class ChestShop extends JavaPlugin {

    private static Breeze breeze;

    /**
     * Called on plugin enable
     */
    public void onEnable() {
        breeze = new Breeze(this);

        registerDefaultModules();
    }

    /**
     * Called on plugin disable
     */
    public void onDisable() {
    }

    /**
     * Gets a Breeze instance
     * @return breeze instance
     */
    public static Breeze getBreeze() {
        return breeze;
    }

    /**
     * Registers the default modules
     */
    private void registerDefaultModules(){
        breeze.registerModule(new TestModule());

        breeze.loadPlugins();
    }
}