package htw.berlin.wi.prog2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SandwichBuilderTest {

    private final SandwichBuilder builder = new SandwichBuilder();
    private final IngredientBuilder ingBuilder = new IngredientBuilder();

    private final Ingredient mayo = ingBuilder.setName("Mayo").setPrice("0.01").setCals(2000).build(Ingredient.Category.SAUCE);
    private final Ingredient brot = ingBuilder.setName("Brot").setPrice("0.02").setCals(1000).build(Ingredient.Category.BUN);

    @Test
    @DisplayName("can build a precomputed sandwich with two ingredients")
    void buildASandwich() {
        builder.setCreationStyle(SandwichBuilder.CreationStyle.PRECOMPUTED);
        Sandwich sandwich = builder.add(brot).add(mayo).build();

        assertEquals(List.of("Brot", "Mayo"), sandwich.getIngredientNames());
        assertEquals(new BigDecimal("0.03"), sandwich.calculatePrice());
        assertEquals(3000, sandwich.calculateCalories());
    }

    @Test
    @DisplayName("can build two precomputed sandwiches after another without mixing things up")
    void buildTwoSandwiches() {
        builder.setCreationStyle(SandwichBuilder.CreationStyle.PRECOMPUTED);
        Sandwich sandwich1 = builder.add(brot).add(mayo).build();
        Sandwich sandwich2 = builder.add(brot).add(mayo).add(mayo).build();

        assertEquals(List.of("Brot", "Mayo"), sandwich1.getIngredientNames());
        assertEquals(List.of("Brot", "Mayo", "Mayo"), sandwich2.getIngredientNames());
    }

    @Test
    @DisplayName("can build a dynamically computed sandwich with two ingredients")
    void buildASandwichDynamically() {
        builder.setCreationStyle(SandwichBuilder.CreationStyle.DYNAMICALLY_COMPUTED);
        Sandwich sandwich = builder.add(brot).add(mayo).build();

        assertEquals(List.of("Brot", "Mayo"), sandwich.getIngredientNames());
        assertEquals(new BigDecimal("0.03"), sandwich.calculatePrice());
        assertEquals(3000, sandwich.calculateCalories());
    }

    @Test
    @DisplayName("can build two dynamically computed sandwiches after another without mixing things up")
    void buildTwoSandwichesDynamically() {
        builder.setCreationStyle(SandwichBuilder.CreationStyle.DYNAMICALLY_COMPUTED);
        Sandwich sandwich1 = builder.add(brot).add(mayo).build();
        Sandwich sandwich2 = builder.add(brot).add(mayo).add(mayo).build();

        assertEquals(List.of("Brot", "Mayo"), sandwich1.getIngredientNames());
        assertEquals(List.of("Brot", "Mayo", "Mayo"), sandwich2.getIngredientNames());
    }

    @Test
    @DisplayName("a sandwich should have at least two ingredients")
    void checkNumberOfIngredients() {
        assertThrows(IllegalSandwichException.class, builder::build);
        assertThrows(IllegalSandwichException.class, () -> builder.add(brot).build());
    }
}