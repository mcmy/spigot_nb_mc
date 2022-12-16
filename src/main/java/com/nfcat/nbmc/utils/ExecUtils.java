package com.nfcat.nbmc.utils;

import com.nfcat.nbmc.NbMcPlugin;
import com.nfcat.nbmc.data.ExecCallback;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class ExecUtils {

    public static boolean execMcHttpCommand(String command) {
        return false;
    }

    public static boolean execMcShellCommand(String command) {
        ExecCallback exec = exec(Charset.defaultCharset().name(), NbMcPlugin.getPropConfig("default_command.execute.command"), command);
        return exec.getExitValue() == 0;
    }

    public static @NotNull ExecCallback exec(String charset, String command, Object... format) {
        ExecCallback callback = new ExecCallback();
        try {
            Process process = Runtime.getRuntime().exec(String.format(command, format));
            if (charset == null) charset = "UTF-8";
            callback.setCallback(new String(new BufferedInputStream(process.getInputStream()).readAllBytes(), charset));
            callback.setCallBackError(new String(new BufferedInputStream(process.getErrorStream()).readAllBytes(), charset));
            callback.setExitValue(process.exitValue());
        } catch (IOException e) {
            callback.setException(e);
        }
        return callback;
    }

}
