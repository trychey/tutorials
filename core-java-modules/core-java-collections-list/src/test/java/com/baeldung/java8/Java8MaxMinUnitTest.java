package com.baeldung.java8;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import com.baeldung.java_8_features.Person;

public class Java8MaxMinUnitTest {

    @Test
    public void whenListIsOfIntegerThenMaxCanBeDoneUsingIntegerComparator() {
        //given
        final List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 56, 7, 89, 10);
        final Integer expectedResult = 89;

        //then
        final Integer max = listOfIntegers
          .stream()
          .mapToInt(v -> v)
          .max().orElseThrow(NoSuchElementException::new);

        assertEquals("Should be 89", expectedResult, max);
    }

    @Test
    public void whenListIsOfPersonObjectThenMinCanBeDoneUsingCustomComparatorThroughLambda() {
        //given
        final Person alex = new Person("Alex", 23);
        final Person john = new Person("John", 40);
        final Person peter = new Person("Peter", 32);
        final List<Person> people = Arrays.asList(alex, john, peter);

        //then
        final Person minByAge = people
          .stream()
          .min(Comparator.comparing(Person::getAge))
          .orElseThrow(NoSuchElementException::new);

        assertEquals("Should be Alex", alex, minByAge);
    }

    @Test
    public void whenArrayIsOfIntegerThenMinUsesIntegerComparator() {
        int[] integers = new int[] { 20, 98, 12, 7, 35 };

        int min = Arrays.stream(integers)
            .min()
            .getAsInt();

        assertEquals(7, min);
    }
}
