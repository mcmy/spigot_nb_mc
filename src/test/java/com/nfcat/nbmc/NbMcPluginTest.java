package com.nfcat.nbmc;

import com.nfcat.nbmc.utils.ExecUtils;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

public class NbMcPluginTest {


    Properties configProp = new Properties();

    @Test
    public void propTest() {
        try (InputStream ci = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            configProp.load(ci);
            System.out.printf("ip:%s,port:%s%n", configProp.getProperty("live_room.ip"), configProp.getProperty("websocket.port"));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void execTest() {
        System.out.println(ExecUtils.exec("GBK", "ping %s", "127.0.0.1"));
    }
}
