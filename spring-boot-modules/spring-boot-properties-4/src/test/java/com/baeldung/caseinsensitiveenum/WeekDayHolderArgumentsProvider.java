package com.baeldung.caseinsensitiveenum;


import static com.baeldung.caseinsensitiveenum.WeekDays.FRIDAY;
import static com.baeldung.caseinsensitiveenum.WeekDays.MONDAY;
import static com.baeldung.caseinsensitiveenum.WeekDays.SATURDAY;
import static com.baeldung.caseinsensitiveenum.WeekDays.SUNDAY;
import static com.baeldung.caseinsensitiveenum.WeekDays.THURSDAY;
import static com.baeldung.caseinsensitiveenum.WeekDays.TUESDAY;
import static com.baeldung.caseinsensitiveenum.WeekDays.WEDNESDAY;

import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class WeekDayHolderArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(final ExtensionContext extensionContext) {
        return Stream.of(
            Arguments.of(((Function<WeekDaysHolder, WeekDays>) WeekDaysHolder::getMonday), MONDAY),
            Arguments.of(((Function<WeekDaysHolder, WeekDays>) WeekDaysHolder::getTuesday), TUESDAY),
            Arguments.of(((Function<WeekDaysHolder, WeekDays>) WeekDaysHolder::getWednesday), WEDNESDAY),
            Arguments.of(((Function<WeekDaysHolder, WeekDays>) WeekDaysHolder::getThursday), THURSDAY),
            Arguments.of(((Function<WeekDaysHolder, WeekDays>) WeekDaysHolder::getFriday), FRIDAY),
            Arguments.of(((Function<WeekDaysHolder, WeekDays>) WeekDaysHolder::getSaturday), SATURDAY),
            Arguments.of(((Function<WeekDaysHolder, WeekDays>) WeekDaysHolder::getSunday), SUNDAY)
        );
    }
}
