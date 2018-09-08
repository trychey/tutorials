package com.baeldung.protonpack;

import com.codepoetics.protonpack.Indexed;
import com.codepoetics.protonpack.StreamUtils;
import com.codepoetics.protonpack.collectors.CollectorUtils;
import com.codepoetics.protonpack.collectors.NonUniqueValueException;
import com.codepoetics.protonpack.selectors.Selector;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SuppressWarnings("unchecked")
public class ProtonpackUnitTest {
    @Test
    public void whenTakeWhile_thenTakenWhile() {
        Stream<Integer> streamOfInt = Stream.iterate(1, i -> i + 1);
        List<Integer> result = StreamUtils.takeWhile(streamOfInt, i -> i < 5).collect(Collectors.toList());
        assertThat(result).containsExactly(1, 2, 3, 4);
    }

    @Test
    public void whenTakeUntil_thenTakenUntil() {
        Stream<Integer> streamOfInt = Stream.iterate(1, i -> i + 1);
        List<Integer> result = StreamUtils.takeUntil(streamOfInt, i -> i >= 5).collect(Collectors.toList());
        assertThat(result).containsExactly(1, 2, 3, 4);
    }

    @Test
    public void givenMultipleStream_whenZipped_thenZipped() {
        String[] clubs = { "Juventus", "Barcelona", "Liverpool", "PSG" };
        String[] players = { "Ronaldo", "Messi", "Salah" };
        Set<String> zippedFrom2Sources = StreamUtils.zip(stream(clubs), stream(players), (club, player) -> club + " " + player)
            .collect(Collectors.toSet());
        assertThat(zippedFrom2Sources).contains("Juventus Ronaldo", "Barcelona Messi", "Liverpool Salah");

        String[] leagues = { "Serie A", "La Liga", "Premier League" };
        Set<String> zippedFrom3Sources = StreamUtils.zip(stream(clubs), stream(players), stream(leagues),
            (club, player, league) -> club + " " + player + " " + league).collect(Collectors.toSet());
        assertThat(zippedFrom3Sources).contains("Juventus Ronaldo Serie A", "Barcelona Messi La Liga",
            "Liverpool Salah Premier League");
    }

    @Test
    public void whenZippedWithIndex_thenZippedWithIndex() {
        Stream<String> streamOfClubs = Stream.of("Juventus", "Barcelona", "Liverpool");
        Set<Indexed<String>> zipsWithIndex = StreamUtils.zipWithIndex(streamOfClubs).collect(Collectors.toSet());
        assertThat(zipsWithIndex).contains(Indexed.index(0, "Juventus"), Indexed.index(1, "Barcelona"),
            Indexed.index(2, "Liverpool"));
    }

    @Test
    public void givenMultipleStream_whenMerged_thenMerged() {
        Stream<String> streamOfClubs = Stream.of("Juventus", "Barcelona", "Liverpool", "PSG");
        Stream<String> streamOfPlayers = Stream.of("Ronaldo", "Messi", "Salah");
        Stream<String> streamOfLeagues = Stream.of("Serie A", "La Liga", "Premier League");

        Set<String> merged = StreamUtils.merge(() -> {
            return "";
        }, (valOne, valTwo) -> valOne + " " + valTwo, streamOfClubs, streamOfPlayers, streamOfLeagues)
            .collect(Collectors.toSet());
        assertThat(merged).contains(" Juventus Ronaldo Serie A", " Barcelona Messi La Liga", " Liverpool Salah Premier League",
            " PSG");
    }

    @Test
    public void givenMultipleStream_whenMergedToList_thenMergedToList() {
        Stream<String> streamOfClubs = Stream.of("Juventus", "Barcelona", "PSG");
        Stream<String> streamOfPlayers = Stream.of("Ronaldo", "Messi");

        Stream<List<String>> mergedStreamOfList = StreamUtils.mergeToList(streamOfClubs, streamOfPlayers);
        List<List<String>> mergedListOfList = mergedStreamOfList.collect(Collectors.toList());
        assertThat(mergedListOfList.get(0)).isInstanceOf(List.class);
        assertThat(mergedListOfList.get(0)).containsExactly("Juventus", "Ronaldo");
        assertThat(mergedListOfList.get(1)).containsExactly("Barcelona", "Messi");
        assertThat(mergedListOfList.get(2)).containsExactly("PSG");
    }

    @Test
    public void givenMultipleStream_whenInterleaved_thenInterleaved() {
        Stream<String> streamOfClubs = Stream.of("Juventus", "Barcelona", "Liverpool");
        Stream<String> streamOfPlayers = Stream.of("Ronaldo", "Messi");
        Stream<String> streamOfLeagues = Stream.of("Serie A", "La Liga");

        AtomicInteger counter = new AtomicInteger(0);
        Selector roundRobinSelector = (o) -> {
            Object[] vals = (Object[]) o;
            while (counter.get() >= vals.length || vals[counter.get()] == null) {
                if (counter.incrementAndGet() >= vals.length)
                    counter.set(0);
            }
            return counter.getAndIncrement();
        };
        Stream<String> interleavedStream = StreamUtils.interleave(roundRobinSelector, streamOfClubs, streamOfPlayers,
            streamOfLeagues);
        List<String> interleavedList = interleavedStream.collect(Collectors.toList());
        assertThat(interleavedList).containsExactly("Juventus", "Ronaldo", "Serie A", "Barcelona", "Messi", "La Liga",
            "Liverpool");
    }

    @Test
    public void whenSkippedUntil_thenSkippedUntil() {
        Integer[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> skippedUntilGreaterThan5 = StreamUtils.skipUntil(stream(numbers), i -> i > 5).collect(Collectors.toList());
        assertThat(skippedUntilGreaterThan5).containsExactly(6, 7, 8, 9, 10);
    }

    @Test
    public void whenSkippedWhile_thenSkippedWhile() {
        Integer[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> skippedWhileLessThanEquals5 = StreamUtils.skipWhile(stream(numbers), i -> i <= 5)
            .collect(Collectors.toList());
        assertThat(skippedWhileLessThanEquals5).containsExactly(6, 7, 8, 9, 10);

        List<Integer> skippedWhileGreaterThan5 = StreamUtils.skipWhile(stream(numbers), i -> i > 5).collect(Collectors.toList());
        assertThat(skippedWhileGreaterThan5).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void givenFibonacciGenerator_whenUnfolded_thenUnfolded() {
        AtomicInteger lastValue = new AtomicInteger(0);
        Function<Integer, Optional<Integer>> fibonacciGenerator = (i) -> (i < 10) ?
            Optional.of(i + lastValue.getAndSet(i)) :
            Optional.empty();

        List<Integer> fib = StreamUtils.unfold(1, fibonacciGenerator).collect(Collectors.toList());
        assertThat(fib).containsExactly(1, 1, 2, 3, 5, 8, 13);
    }

    @Test
    public void whenWindowed_thenWindowed() {
        Integer[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8 };

        List<List<Integer>> windowedWithSkip1 = StreamUtils.windowed(stream(numbers), 3, 1).collect(Collectors.toList());
        assertThat(windowedWithSkip1).containsExactly(asList(1, 2, 3), asList(2, 3, 4), asList(3, 4, 5), asList(4, 5, 6),
            asList(5, 6, 7), asList(6, 7, 8));

        List<List<Integer>> windowedWithSkip2 = StreamUtils.windowed(stream(numbers), 3, 2).collect(Collectors.toList());
        assertThat(windowedWithSkip2).containsExactly(asList(1, 2, 3), asList(3, 4, 5), asList(5, 6, 7));
    }

    @Test
    public void whenAggregated_thenAggregated() {
        Integer[] numbers = { 1, 2, 2, 3, 4, 4, 4, 5 };
        List<List<Integer>> aggregated = StreamUtils.aggregate(Arrays.stream(numbers), (int1, int2) -> int1.compareTo(int2) == 0)
            .collect(Collectors.toList());
        assertThat(aggregated).containsExactly(asList(1), asList(2, 2), asList(3), asList(4, 4, 4), asList(5));

        List<List<Integer>> aggregatedFixSize = StreamUtils.aggregate(Arrays.stream(numbers), 5).collect(Collectors.toList());
        assertThat(aggregatedFixSize).containsExactly(asList(1, 2, 2, 3, 4), asList(4, 4, 5));
    }

    @Test
    public void whenGroupedRun_thenGroupedRun() {
        Integer[] numbers = { 1, 1, 2, 3, 4, 4, 5 };
        List<List<Integer>> grouped = StreamUtils.groupRuns(Arrays.stream(numbers)).collect(Collectors.toList());
        assertThat(grouped).containsExactly(asList(1, 1), asList(2), asList(3), asList(4, 4), asList(5));

        Integer[] numbers2 = { 1, 2, 3, 1 };
        List<List<Integer>> grouped2 = StreamUtils.groupRuns(Arrays.stream(numbers2)).collect(Collectors.toList());
        assertThat(grouped2).containsExactly(asList(1), asList(2), asList(3), asList(1));
    }

    @Test
    public void whenAggregatedOnListCondition_thenAggregatedOnListCondition() {
        Integer[] numbers = { 1, 1, 2, 3, 4, 4, 5 };
        Stream<List<Integer>> aggregated = StreamUtils.aggregateOnListCondition(Arrays.stream(numbers),
            (currentList, nextInt) -> currentList.stream().mapToInt(Integer::intValue).sum() + nextInt <= 5);
        assertThat(aggregated).containsExactly(asList(1, 1, 2), asList(3), asList(4), asList(4), asList(5));
    }

    @Test
    public void givenProjectionFunction_whenMaxedBy_thenMaxedBy() {
        Stream<String> clubs = Stream.of("Juventus", "Barcelona", "PSG");
        Optional<String> longestName = clubs.collect(CollectorUtils.maxBy(String::length));
        assertThat(longestName.get()).isEqualTo("Barcelona");
    }

    @Test
    public void givenStreamOfMultipleElem_whenUniqueCollector_thenValueReturned() {
        Stream<Integer> singleElement = Stream.of(1);
        Optional<Integer> unique = singleElement.collect(CollectorUtils.unique());
        assertThat(unique.get()).isEqualTo(1);
    }

    @Test
    public void givenStreamOfMultipleElem_whenUniqueCollector_thenExceptionThrown() {
        Stream<Integer> multipleElement = Stream.of(1, 2, 3);
        assertThatExceptionOfType(NonUniqueValueException.class).isThrownBy(() -> {
            multipleElement.collect(CollectorUtils.unique());
        });
    }

}
