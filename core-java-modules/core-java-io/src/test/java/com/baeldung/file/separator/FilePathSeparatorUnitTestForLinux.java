package com.baeldung.file.separator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class FilePathSeparatorUnitTestForLinux {

    @Test
    @EnabledOnOs({ OS.LINUX })
    public void whenBuildPathUsingString_thenResultIsAsExpected() throws IOException {
        String[] pathNames = { "path1", "path2", "path3" };
        assertEquals("path1:path2:path3", FilePathSeparator.buildPathUsingString(pathNames));
    }

    @Test
    @EnabledOnOs({ OS.LINUX })
    public void whenbuildPathUsingStringJoiner_thenResultIsAsExpected() throws IOException {
        assertEquals("path4:path5", FilePathSeparator.buildPathUsingStringJoiner("path4", "path5"));
    }

}
