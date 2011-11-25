package com.Acrobot.ChestShop.Modules.ShopInteraction;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.ChestShop.Modules.ShopInteraction.Events.PlayerInteract;
import com.Acrobot.ChestShop.Modules.ShopInteraction.Events.ShopInteract;

/**
 * @author Acrobot
 */
public class ShopInteraction extends BreezePlugin{
    public void onEnable() {
        Breeze br = getBreeze();
        br.registerEvents(PlayerInteract.class); //When the player touches a sign
        br.registerEvents(ShopInteract.class); //Custom event listener
    }

    public void onDisable() {
    }

    public String getName() {
        return "ShopInteraction";
    }
}