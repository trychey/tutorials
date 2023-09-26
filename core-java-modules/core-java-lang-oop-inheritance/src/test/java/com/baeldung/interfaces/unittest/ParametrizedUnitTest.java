package com.baeldung.interfaces.unittest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ParametrizedUnitTest {

    @ParameterizedTest
    @MethodSource("data")
    void givenShapeInstance_thenAreaCalculationIsSuccessful(Shape shapeInstance,double expectedArea){
        double area = shapeInstance.area();
        assertEquals(expectedArea,area);

    }

    private static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
          {new Circle(2), 12.56},
          {new Rectangle(4, 5), 20},
        });
    }
}
