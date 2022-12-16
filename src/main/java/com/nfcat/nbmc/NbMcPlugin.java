package com.nfcat.nbmc;

import com.nfcat.nbmc.commands.menu.Menu;
import com.nfcat.nbmc.commands.nf.Nf;
import com.nfcat.nbmc.threads.PublicThread;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Properties;

public final class NbMcPlugin extends JavaPlugin {

    public static Properties configProp = new Properties();
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info("start init nfcat plugin,init plugin");
        PluginManager pluginManager = getServer().getPluginManager();

        File propFile = initResourcesFile("config.properties");
        if (propFile == null) {
            pluginManager.disablePlugin(this);
            throw new RuntimeException("config.properties init error");
        }

        try (InputStream ci = new FileInputStream(propFile)) {
            configProp.load(ci);
        } catch (Exception ex) {
            pluginManager.disablePlugin(this);
            throw new RuntimeException(ex);
        }

        reg("menu", new Menu());
        reg("nfcat", new Nf());
        PublicThread.startThread();
    }

    @Override
    public void onDisable() {
        PublicThread.shutDownThread();
    }

    private void reg(String command, CommandExecutor executor) {
        PluginCommand help = getCommand(command);
        if (help == null) getLogger().info("no " + command);
        if (help != null) help.setExecutor(executor);
    }

    public static String getPropConfig(String name) {
        return configProp.getProperty(name, "");
    }

    public static String getPropConfig(String name, String def) {
        return configProp.getProperty(name, def);
    }

    public @Nullable File initResourcesFile(String fileName) {
        File file = new File(getDataFolder(), fileName);
        if (!file.exists()) {
            try (InputStream ci = getClassLoader().getResourceAsStream(fileName)) {
                getDataFolder().mkdirs();
                file.createNewFile();
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                    try (BufferedInputStream inputStream = new BufferedInputStream(ci)) {
                        outputStream.write(inputStream.readAllBytes());
                    }
                }
            } catch (Exception ex) {
                return null;
            }
        }
        return file;
    }

}
