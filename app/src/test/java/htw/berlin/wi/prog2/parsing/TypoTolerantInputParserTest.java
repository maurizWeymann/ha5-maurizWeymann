package htw.berlin.wi.prog2.parsing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypoTolerantInputParserTest {

    private final TypoTolerantInputParser sut = new TypoTolerantInputParser();

    @Test
    @DisplayName("can make use of the provided different spellings or common typos")
    void idsAndCountFromInput() {

        // Input-Daten:
        String inputLine = "Ich hätte gerne ein Bort mit salad, Fleich und nochmal Fleisch";
        Map<String, Long> keywordsToIds = Map.of(
                "Brot", 19L,
                "brot", 19L,
                "Bort", 19L,
                "Salat", 87L,
                "salad", 87L,
                "Salt", 87L,
                "Slat", 87L,
                "Fleisch", 77L,
                "Fleish", 77L,
                "Fleich", 77L);

        Map<Long, Integer> expected = Map.of(
                19L, 1,
                87L, 1,
                77L, 2);
        Map<Long, Integer> actual = sut.idsAndCountFromInput(inputLine, keywordsToIds);

        assertEquals(expected, actual);
    }
}