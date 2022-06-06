package com.baeldung.staticmethods;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarUnitTest {

    @Test
    void givenCarInstance_whenGettingCarId_thenCorrectIdIsReturned() {
        Car car1 = new Car(1, "Karoq");
        assertThat(car1.getId()).isEqualTo(1);
    }

    @Test
    void givenCarInstance_whenGettingCarModel_thenCorrectIdModelReturned() {
        Car car1 = new Car(1, "Karoq");
        assertThat(car1.getModel()).isEqualTo("Karoq");
    }

    @Test
    void givenCarInstance_whenGettingCarMake_thenCorrectIdMakeReturned() {
        Car car1 = new Car(1, "Karoq");
        assertThat(car1.getMake()).isEqualTo("Skoda");
    }

}
