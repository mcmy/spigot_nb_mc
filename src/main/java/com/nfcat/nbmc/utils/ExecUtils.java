package com.nfcat.nbmc.utils;

import com.nfcat.nbmc.data.ExecCallback;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;

public class ExecUtils {

    public static @NotNull ExecCallback exec(String charset, String command, Object... format) {
        ExecCallback callback = new ExecCallback();
        try {
            Process process = Runtime.getRuntime().exec(String.format(command, format));
            if (charset == null) charset = "gbk";
            callback.setCallback(new String(new BufferedInputStream(process.getInputStream()).readAllBytes(), charset));
            callback.setCallBackError(new String(new BufferedInputStream(process.getErrorStream()).readAllBytes(), charset));
            callback.setExitValue(process.exitValue());
        } catch (IOException e) {
            callback.setException(e);
        }
        return callback;
    }

}
