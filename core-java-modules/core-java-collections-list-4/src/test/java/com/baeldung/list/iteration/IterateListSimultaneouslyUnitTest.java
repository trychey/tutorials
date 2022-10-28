package com.baeldung.list.iteration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.StreamUtils;

public class IterateListSimultaneouslyUnitTest {

    final List<String> countryName = List.of("USA", "UK", "Germany", "India");
    final List<String> countryCode = List.of("+1", "+44", "+49", "+91");

    @Test
    public void givenTwoLists_whenProcessedByZipping_thenGetJoinedDataFromBothCollections() {
        List<String> processedList = StreamUtils.zip(countryName.stream(), countryCode.stream(),
            (name, code) -> String.format("%s: %s", name, code))
            .collect(Collectors.toList());
        assertThat(processedList).containsExactly("USA: +1", "UK: +44", "Germany: +49", "India: +91");
    }

    @Test
    public void givenTwoLists_whenIterateUsingIterator_thenGetJoinedDataFromBothCollections() {
        Iterator nameIterator = countryName.iterator();
        Iterator codeIterator = countryCode.iterator();
        List<String> processedList = new ArrayList<>();
        while (nameIterator.hasNext() && codeIterator.hasNext()) {
            String processedData = String.format("%s: %s", nameIterator.next(), codeIterator.next());
            processedList.add(processedData);
        }
        assertThat(processedList).containsExactly("USA: +1", "UK: +44", "Germany: +49", "India: +91");
    }

    @Test
    public void givenTwoLists_whenIterateUsingLoop_thenGetJoinedDataFromBothCollections() {
        List<String> processedList = new ArrayList<>();
        for (int i = 0; i < countryName.size(); i++) {
            String processedData = String.format("%s: %s", countryName.get(i), countryCode.get(i));
            processedList.add(processedData);
        }
        assertThat(processedList).containsExactly("USA: +1", "UK: +44", "Germany: +49", "India: +91");
    }

    @Test
    public void givenEncapsulatedDataList_whenIterate_thenGetJoinedDataFromCollection() {
        List<CountryCodeDetails> list = List.of(new CountryCodeDetails("USA", "+1"),
                                new CountryCodeDetails("UK", "+44"),
                                new CountryCodeDetails("Germany", "+49"),
                                new CountryCodeDetails("India", "+91"));
        List<String> processedList = list.stream().map(l -> String.format("%s: %s", l.getCountryName(), l.getCountryCode()))
          .collect(Collectors.toList());
        assertThat(processedList).containsExactly("USA: +1", "UK: +44", "Germany: +49", "India: +91");
    }

    class CountryCodeDetails {
        private String countryName;
        private String countryCode;

        public CountryCodeDetails(String countryName, String countryCode) {
            this.countryName = countryName;
            this.countryCode = countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public String getCountryCode() {
            return countryCode;
        }
    }

}
