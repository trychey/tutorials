package com.baeldung.fastexcel;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.junit.Before;
import org.junit.Test;


public class FastexcelDetectEmptyRowUnitTest {

    private FastexcelHelper fastexcelHelper;
    private static final String FILE_PATH = "src/main/resources/consumer_info_with_empty_row.xlsx";
    private static final String EMPTY_FILE_PATH = "src/main/resources/empty_excel_file.xlsx";

    @Before
    public void loadExcelFile() throws IOException {
        fastexcelHelper = new FastexcelHelper();
    }

    @Test
    public void givenXLSXFile_whenParsingFastExcelFile_thenDetectEmptyRow() throws IOException {
        try (FileInputStream file = new FileInputStream(FILE_PATH); ReadableWorkbook wb = new ReadableWorkbook(file)) {
            Sheet sheet = wb.getFirstSheet();
            try (Stream<Row> rows = sheet.openStream()) {
                Optional<Row> last = rows.reduce((first, second) -> second);
                boolean isLastRowEmpty = last.map(fastexcelHelper::isRowEmpty).orElseThrow();
                assertTrue(isLastRowEmpty);
            }
        }
    }
    
    @Test
    public void givenXLSXFile_whenParsingEmptyFastExcelFile_thenDetectAllRowsAreEmpty() throws IOException {
        try (FileInputStream file = new FileInputStream(EMPTY_FILE_PATH); ReadableWorkbook wb = new ReadableWorkbook(file)) {
            Sheet sheet = wb.getFirstSheet();
            try (Stream<Row> rows = sheet.openStream()) {
                boolean isEmpty = rows.allMatch(fastexcelHelper::isRowEmpty);
                assertTrue(isEmpty);
            }
        }
    }
}
