package com.nfcat.nbmc.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExecCallback {
    public String callback;
    public String callBackError;
    public int exitValue;
    public Exception exception;
}