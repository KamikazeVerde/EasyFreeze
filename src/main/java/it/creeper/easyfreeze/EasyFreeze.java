package it.creeper.easyfreeze;

import it.creeper.easyfreeze.commands.FreezeCommand;
import org.bukkit.plugin.java.JavaPlugin;
import it.creeper.easyfreeze.commands.FreezeCommand;

public final class EasyFreeze extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Enabling EasyFreeze | by Creeper215");

        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new FreezeCommand());
        getCommand("easyfreeze").setExecutor(new FreezeCommand());
        getCommand("easyfreezereload").setExecutor(new FreezeCommand());
        getServer().getPluginManager().registerEvents(new FreezeCommand(), this);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling EasyFreeze | by Creeper215");
    }
}
