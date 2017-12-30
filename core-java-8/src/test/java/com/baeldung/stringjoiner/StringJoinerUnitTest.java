package com.baeldung.stringjoiner;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.junit.Test;

public class StringJoinerUnitTest {
    private final String DELIMITER_COMMA = ",";
    private final String DELIMITER_HYPHEN = "-";
    private final String PREFIX = "[";
    private final String SUFFIX = "]";
    private final String EMPTY_JOINER = "empty";

    @Test
    public void whenEmptyValueNotSet_thenReturnDefault() {
        StringJoiner commaSeparatedJoiner = new StringJoiner(DELIMITER_COMMA);
        assertEquals(0, commaSeparatedJoiner.toString()
            .length());

        StringJoiner commaSeparatedWithPrefixSuffixJoiner = new StringJoiner(DELIMITER_COMMA, PREFIX, SUFFIX);
        assertEquals(commaSeparatedWithPrefixSuffixJoiner.toString(), PREFIX + SUFFIX);
    }

    @Test
    public void whenEmptyValueSet_thenReturnEmptyValue() {
        StringJoiner commaSeparatedJoiner = new StringJoiner(DELIMITER_COMMA);
        commaSeparatedJoiner.setEmptyValue(EMPTY_JOINER);

        assertEquals(commaSeparatedJoiner.toString(), EMPTY_JOINER);

        StringJoiner commaSeparatedWithPrefixSuffixJoiner = new StringJoiner(DELIMITER_COMMA, PREFIX, SUFFIX);
        commaSeparatedWithPrefixSuffixJoiner.setEmptyValue(EMPTY_JOINER);

        assertEquals(commaSeparatedWithPrefixSuffixJoiner.toString(), EMPTY_JOINER);
    }

    @Test
    public void whenAddElements_thenJoinElements() {
        StringJoiner rgbJoiner = new StringJoiner(DELIMITER_COMMA, PREFIX, SUFFIX);
        rgbJoiner.add("Red").add("Green").add("Blue");

        assertEquals(rgbJoiner.toString(), "[Red,Green,Blue]");
    }
    
    @Test
    public void whenAddListElements_thenJoinListElements() {
        List<String> rgbList = new ArrayList<String>();
        rgbList.add("Red");
        rgbList.add("Green");
        rgbList.add("Blue");
        
        StringJoiner rgbJoiner = new StringJoiner(DELIMITER_COMMA, PREFIX, SUFFIX);
        
        for (String color: rgbList) {
            rgbJoiner.add(color);
        }

        assertEquals(rgbJoiner.toString(), "[Red,Green,Blue]");
    }
    
    @Test
    public void whenMergeJoiners_thenReturnMerged() {
        StringJoiner rgbJoiner = new StringJoiner(DELIMITER_COMMA, PREFIX, SUFFIX);
        StringJoiner cmybJoiner = new StringJoiner(DELIMITER_HYPHEN, PREFIX, SUFFIX);

        rgbJoiner.add("Red").add("Green").add("Blue");
        cmybJoiner.add("Cyan").add("Magenta").add("Yellow").add("Black");

        rgbJoiner.merge(cmybJoiner);
        
        assertEquals(rgbJoiner.toString(), "[Red,Green,Blue,Cyan-Magenta-Yellow-Black]");
    }

    @Test
    public void whenUsedWithinCollectors_thenJoin() {
        List<String> rgbList = Arrays.asList("Red", "Green", "Blue");
        String commaSeparatedRGB = rgbList.stream()
            .map(color -> color.toString())
            .collect(Collectors.joining(","));
        
        assertEquals(commaSeparatedRGB, "Red,Green,Blue");
    }
}
