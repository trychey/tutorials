package com.baeldung.instanceofalternative.enumallt;

public class Euraptor extends Dinosaur {
    // polymorphism
    @Override
    public String move() {
        return "flying";
    }

    // non-polymorphism
    public String flies() {
        return "flying";
    }

}
