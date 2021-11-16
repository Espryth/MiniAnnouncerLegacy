package me.jonakls.minimessagelegacy.configuration;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class Configuration extends YamlConfiguration {

    private JavaPlugin plugin;
    private File file;
    private String fileName;

    private Configuration(JavaPlugin plugin, String fileName, String fileExtension, File folder) {
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : fileExtension);
        this.file = new File(folder, fileName);
        create();
    }

    private Configuration(JavaPlugin plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    public Configuration(JavaPlugin plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }

    private void create() {
        try {
            if (file.exists()) {
                load(file);
                return;
            }
            if (this.plugin.getResource(this.fileName) != null) {
                this.plugin.saveResource(this.fileName, false);
            } else {
                save(file);
            }
            load(file);
        } catch (InvalidConfigurationException | IOException e) {
            this.plugin.getLogger().log(
                    Level.SEVERE, "Creation of Configuration '" + this.fileName + "' failed.", e
            );
        }
    }

    public void saveFile() {
        try {
            save(file);
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Save of file '" + this.fileName + "' failed.", e);
        }
    }

    public void reloadFile() {
        try {

            load(file);
        } catch (IOException | InvalidConfigurationException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Reload of file '" + this.fileName + "' failed.", e);
        }
    }

    @Override
    public String getString(String path) {
        if(path == null) {
            return "Path: " + path + " is null, please review";
        }
        return ChatColor.translateAlternateColorCodes('&', super.getString(path));
    }

    @Override
    public List<String> getStringList(String path) {
        List<String> list = super.getStringList(path);
        if(path == null) {
            list.add("Path: " + path + " is null, please review");
            return list;
        }
        list.forEach(line -> ChatColor.translateAlternateColorCodes('&', line));
        return list;
    }
}
