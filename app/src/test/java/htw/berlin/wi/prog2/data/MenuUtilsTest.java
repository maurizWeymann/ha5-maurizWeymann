package htw.berlin.wi.prog2.data;

import htw.berlin.wi.prog2.domain.Ingredient;
import htw.berlin.wi.prog2.domain.IngredientBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MenuUtilsTest {

    private final IngredientBuilder builder = new IngredientBuilder();
    private final Ingredient mayo = builder.setName("Mayo").setPrice("0.01").setCals(1000).build(Ingredient.Category.SAUCE);
    private final Ingredient brot = builder.setName("Brot").setPrice("0.02").setCals(2000).build(Ingredient.Category.BUN);
    private final Ingredient fleisch = builder.setName("Fleisch").setPrice("0.03").setCals(3000).build(Ingredient.Category.MEAT);

    private final Map<Long, Ingredient> testMenu = Map.of(
            42L, mayo,
            66L, brot,
            17L, fleisch);

    @Test
    @DisplayName("should extract only the ingredient names from the Menu")
    void focusOnNames() {
        List<String> expected = List.of("Mayo", "Brot", "Fleisch");
        List<String> actual = MenuUtils.focusOnNames(testMenu);

        List<String> expectedSorted = expected.stream().sorted().collect(Collectors.toList());
        List<String> actualSorted = actual.stream().sorted().collect(Collectors.toList());

        assertEquals(expectedSorted, actualSorted);
    }

    @Test
    @DisplayName("should select the ingredient name and invert the passed map, i.e. keys become values and values become keys")
    void focusOnNameAndInvert() {
        Map<String, Long> actual = MenuUtils.focusOnNameAndInvert(testMenu);
        Map<String, Long> expected = Map.of(
                "Mayo", 42L,
                "Brot", 66L,
                "Fleisch", 17L);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should get the ingredients from the passed menu in the stated quantities of the input map")
    void ingredientsFromIdAndCount() {
        Map<Long, Integer> counts = Map.of(
                66L, 1,
                17L, 2);

        List<Ingredient> expected = List.of(brot, fleisch, fleisch);
        List<Ingredient> actual = MenuUtils.ingredientsFromIdAndCount(counts, testMenu);

        Comparator<Ingredient> byName = Comparator.comparing(Ingredient::getName);
        List<Ingredient> expectedSorted = expected.stream().sorted(byName).collect(Collectors.toList());
        List<Ingredient> actualSorted = actual.stream().sorted(byName).collect(Collectors.toList());

        assertEquals(expectedSorted, actualSorted);
    }
}