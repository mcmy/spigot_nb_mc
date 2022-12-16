package com.nfcat.nbmc.lintener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PublicListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onLogin(PlayerJoinEvent event) {

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onQuit(PlayerQuitEvent event) {

    }

}
