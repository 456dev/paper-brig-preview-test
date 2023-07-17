package dev.the456gamer.paperbrigtestplugin.util;

import io.papermc.paper.plugin.configuration.PluginMeta;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.PluginLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DummyNamedPlugin implements Plugin {

  String customName;

  public DummyNamedPlugin(@NotNull String name) {
    this.customName = name;
  }

  @Override
  public @NotNull File getDataFolder() {
    throw new UnsupportedOperationException();
  }

  @Override
  public @NotNull PluginDescriptionFile getDescription() {
    return (PluginDescriptionFile) getPluginMeta();
  }

  @Override
  public @NotNull PluginMeta getPluginMeta() {
    return new PluginMeta() {
      @Override
      public @NotNull String getName() {
        return customName;
      }

      @Override
      public @NotNull String getMainClass() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull PluginLoadOrder getLoadOrder() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull String getVersion() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @Nullable String getLoggerPrefix() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull List<String> getPluginDependencies() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull List<String> getPluginSoftDependencies() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull List<String> getLoadBeforePlugins() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull List<String> getProvidedPlugins() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull List<String> getAuthors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull List<String> getContributors() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @Nullable String getDescription() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @Nullable String getWebsite() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull List<Permission> getPermissions() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @NotNull PermissionDefault getPermissionDefault() {
        throw new UnsupportedOperationException();
      }

      @Override
      public @Nullable String getAPIVersion() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public @NotNull FileConfiguration getConfig() {
    throw new UnsupportedOperationException();
  }

  @Override
  public @Nullable InputStream getResource(@NotNull String filename) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void saveConfig() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void saveDefaultConfig() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void saveResource(@NotNull String resourcePath, boolean replace) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void reloadConfig() {
    throw new UnsupportedOperationException();
  }

  @Override
  public @NotNull PluginLoader getPluginLoader() {
    throw new UnsupportedOperationException();
  }

  @Override
  public @NotNull Server getServer() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  @Override
  public void onDisable() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void onLoad() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void onEnable() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isNaggable() {
    return false;
  }

  @Override
  public void setNaggable(boolean canNag) {
    throw new UnsupportedOperationException();
  }

  @Override
  public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName,
      @Nullable String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public @Nullable BiomeProvider getDefaultBiomeProvider(@NotNull String worldName,
      @Nullable String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public @NotNull Logger getLogger() {
    throw new UnsupportedOperationException();
  }

  @Override
  public @NotNull String getName() {
    return customName;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
      @NotNull String label, @NotNull String[] args) {
    throw new UnsupportedOperationException();
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
      @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    throw new UnsupportedOperationException();
  }
}
