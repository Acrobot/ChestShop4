package com.Acrobot.Breeze.Plugins;

import com.Acrobot.Breeze.Breeze;
import com.Acrobot.Breeze.Plugins.BreezePlugin.BreezePlugin;
import com.Acrobot.Breeze.Plugins.Loader.JavaPluginLoader;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Manager loaded Breeze plugins
 *
 * @author Acrobot
 */
public class PluginManager {
    private Breeze breeze;

    protected JavaPluginLoader loader;
    protected Set<BreezePlugin> plugins = new HashSet<BreezePlugin>();

    public PluginManager(Breeze breeze) {
        this.breeze = breeze;
        loader = new JavaPluginLoader(breeze.getPlugin().getClass());
    }

    /**
     * Turns on all the plugins
     */
    public void initializePlugins() {
        File pluginFolder = breeze.getPluginFolder();

        plugins = loader.load(pluginFolder);

        for (BreezePlugin plugin : plugins) {
            initializePlugin(plugin);
        }
    }

    /**
     * Initializes one plugin
     *
     * @param plugin Plugin to initialize
     */
    public void initializePlugin(BreezePlugin plugin) {
        plugins.add(plugin);
        plugin.initialize(new File(breeze.getPluginFolder(), plugin.getName()), breeze);

        breeze.getLogger().info("Plugin \'" + plugin.getName() + "\' was turned on");
    }

    /**
     * Returns a BreezePlugin named like the parameter
     *
     * @param name plugin to find
     * @return BreezePlugin found
     */
    public BreezePlugin getPlugin(String name) {
        for (BreezePlugin plugin : plugins) {
            if (plugin.getName().equals(name)) {
                return plugin;
            }
        }
        return null;
    }

    /**
     * Disables all plugins
     */
    public void disablePlugins() {
        for (BreezePlugin plugin : plugins) {
            plugin.onDisable();
            unregisterPlugin(plugin, false);
        }

        plugins = null;
    }

    /**
     * Disables one plugin
     *
     * @param plugin plugin to disable
     */
    public void disablePlugin(BreezePlugin plugin) {
        plugin.onDisable();

        unregisterPlugin(plugin);
    }

    /**
     * Unregisters a plugin without calling onDisable()
     * DON'T USE IT if you want to disable plugin
     *
     * @param plugin plugin to unregister
     */
    public void unregisterPlugin(BreezePlugin plugin) {
        unregisterPlugin(plugin, true);
    }

    /**
     * Unregister a plugin without calling onDisable()
     *
     * @param plugin        plugin to unregister
     * @param removeFromSet Should the plugin be removed from the "plugins" set?
     */
    public void unregisterPlugin(BreezePlugin plugin, boolean removeFromSet) {
        breeze.eventManager.unregisterEvents(plugin);
        breeze.cmdManager.unregisterCommands(plugin);

        if (removeFromSet) {
            plugins.remove(plugin);
        }

        breeze.getLogger().info("Plugin \'" + plugin.getName() + "\' was turned off");
    }


    /**
     * Merges two sets
     *
     * @param set1 First set
     * @param set2 Second set
     * @return Merged set
     */
    private static <E> Set<E> mergeSets(Set<E> set1, Set<E> set2) {
        Set<E> newSet = new HashSet<E>(set1);
        newSet.addAll(set2);

        return newSet;
    }
}
