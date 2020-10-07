package com.baeldung.jvm;

import com.sun.jna.Platform;

public class JVMBitVersion {

    public String getUsingSystemClass() {
        return System.getProperty("sun.arch.data.model")+"-bit";
    }

    public String getUsingSystemArch() {
        return System.getProperty("os.arch");
    }

    public String getUsingNativeClass() {
        if (com.sun.jna.Native.POINTER_SIZE == 8) {
            return "64-bit";
        } else if (com.sun.jna.Native.POINTER_SIZE == 4) {
            return "32-bit";
        } else
            return "unknown";
    }

    public boolean getUsingPlatformClass() {
        return (Platform.is64Bit());
    }

}
