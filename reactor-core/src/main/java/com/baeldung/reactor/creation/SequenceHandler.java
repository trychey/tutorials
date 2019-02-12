package com.baeldung.reactor.creation;

import reactor.core.publisher.Flux;

public class SequenceHandler {
    public Flux<Integer> handleIntegerSequence(Flux<Integer> sequence) {
        return sequence.handle((number, sink) -> {
            if (number > 0) {
                sink.next(2 * number);
            } else if (number == 0) {
                sink.complete();
            }
        });
    }
}
