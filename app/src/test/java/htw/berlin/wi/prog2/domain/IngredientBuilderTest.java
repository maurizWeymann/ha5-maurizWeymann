package htw.berlin.wi.prog2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientBuilderTest {

    @Test
    @DisplayName("Builder should create instances of different classes")
    void testDifferentClasses() {
        IngredientBuilder builder = new IngredientBuilder();
        Ingredient a = builder.setName("Feldsalat").setPrice("0.123").setCals(42).build(Ingredient.Category.SALAD);
        Ingredient b = builder.setName("Ketchup").setPrice("0.321").setCals(24).build(Ingredient.Category.SAUCE);
        assertNotEquals(a.getClass(), b.getClass());
    }

    @Test
    @DisplayName("Ingredients with different properties should be treated unequal")
    void testDifferent() {
        IngredientBuilder builder = new IngredientBuilder();
        Ingredient a = builder.setName("Feldsalat").setPrice("0.123").setCals(42).build(Ingredient.Category.SALAD);
        Ingredient b = builder.setName("Ketchup").setPrice("0.321").setCals(24).build(Ingredient.Category.SAUCE);
        assertEquals(a.equals(b), false);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("Ingredients with the same properties should be treated equal")
    void testSameProps() {
        IngredientBuilder builder = new IngredientBuilder();
        Ingredient a = builder.setName("Feldsalat").setPrice("0.01").setCals(900).build(Ingredient.Category.SALAD);
        Ingredient b = builder.setName("Feldsalat").setPrice("0.01").setCals(900).build(Ingredient.Category.SALAD);
        assertEquals(a.equals(b), true);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("Ingredients with properties only differing in spaces should be treated equal")
    void testSimilarName() {
        IngredientBuilder builder = new IngredientBuilder();
        Ingredient a = builder.setName("Feldsalat ").setPrice("0.01").setCals(900).build(Ingredient.Category.SALAD);
        Ingredient b = builder.setName(" Feldsalat").setPrice("0.01").setCals(900).build(Ingredient.Category.SALAD);
        assertEquals(a.equals(b), true);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("Ingredients with properties only differing in decimal places should be treated equal")
    void testSimilarPrice() {
        IngredientBuilder builder = new IngredientBuilder();
        Ingredient a = builder.setName("Feldsalat ").setPrice("0.0100").setCals(900).build(Ingredient.Category.SALAD);
        Ingredient b = builder.setName(" Feldsalat").setPrice("0.01").setCals(900).build(Ingredient.Category.SALAD);
        assertEquals(a.equals(b), true);
        assertEquals(a.hashCode(), b.hashCode());
    }
}