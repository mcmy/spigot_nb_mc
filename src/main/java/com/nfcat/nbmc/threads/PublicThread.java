package com.nfcat.nbmc.threads;

import com.nfcat.nbmc.NbMcPlugin;

public class PublicThread {
//    static {
//        Bukkit.getScheduler().callSyncMethod(NbMcPlugin.plugin, () -> true);
//    }

    public static void startThread() {
        if (NbMcPlugin.getPropConfig("live_room.start", "").equals("true")) {
            int port = Integer.parseInt(NbMcPlugin.getPropConfig("live_room.port"));
            new Thread(new MsgWebSocketServer(port)).start();
        }
    }

    public static void shutDownThread() {

    }

}
