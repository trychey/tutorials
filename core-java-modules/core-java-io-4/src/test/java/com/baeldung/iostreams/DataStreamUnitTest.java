package com.baeldung.iostreams;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataStreamUnitTest {

    private static final String dataProcessingTextFile = "src/test/resources/iostreams/TestFile.txt";
    private static final String dataProcessingImageFile = "src/test/resources/iostreams/image.png";
    private static final String dataProcessingByteStreamFile = "src/test/resources/iostreams/ps.png";
    private static final String dataProcessingCharStreamFile = "src/test/resources/iostreams/pw.png";

    private static final String textFileContent = "Hello, world!";

    @Test
    public void whenUsingByteStream_thenWriteTextToFile() throws IOException {
        DataStream dataStream = new DataStream();
        dataStream.textDataProcessingByteStream(dataProcessingTextFile, textFileContent);

        File file = new File(dataProcessingTextFile);
        assertTrue(file.exists());
        assertEquals(FileUtils.readFileToString(file, "utf-8"), textFileContent);

        Files.delete(Paths.get(dataProcessingTextFile));
    }

    @Test
    public void whenUsingCharStream_thenWriteTextToFile() throws IOException {
        DataStream dataStream = new DataStream();
        dataStream.textDataProcessingCharStream(dataProcessingTextFile, textFileContent);

        File file = new File(dataProcessingTextFile);
        assertTrue(file.exists());
        assertEquals(FileUtils.readFileToString(file, "utf-8"), textFileContent);

        Files.delete(Paths.get(dataProcessingTextFile));
    }

    @Test
    public void whenUsingStreams_thenWriteNonTextData() throws IOException {
        DataStream dataStream = new DataStream();
        dataStream.nonTextDataProcessing(dataProcessingImageFile, dataProcessingByteStreamFile, dataProcessingCharStreamFile);

        File file = new File(dataProcessingImageFile);
        File byteStreamOutputFile = new File(dataProcessingByteStreamFile);
        File charStreamOutputFile = new File(dataProcessingCharStreamFile);
        assertTrue(file.exists());
        assertTrue(byteStreamOutputFile.exists());
        assertTrue(charStreamOutputFile.exists());

        assertTrue(FileUtils.contentEquals(file, byteStreamOutputFile));
        assertFalse(FileUtils.contentEquals(file, charStreamOutputFile));

        Files.delete(Paths.get(dataProcessingByteStreamFile));
        Files.delete(Paths.get(dataProcessingCharStreamFile));
    }
}
