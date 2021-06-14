package htw.berlin.wi.prog2.data;

import htw.berlin.wi.prog2.domain.Ingredient;
import htw.berlin.wi.prog2.domain.IngredientBuilder;

import java.util.*;

// Eine statische Datenbank-Klasse
public class Menu {
    private Menu() {}
    private static Menu theInstance = null;

    public static Menu getInstance() {
        if(theInstance == null) theInstance = new Menu();
        return theInstance;
    }
    private IngredientBuilder builder = new IngredientBuilder();

    private Map<Long, Ingredient> articles = Map.of(
            1L, builder.setName("Vollkorn").setPrice("0.60").setCals(120).build(Ingredient.Category.BUN),
            2L, builder.setName("Ciabatta").setPrice("0.70").setCals(100).build(Ingredient.Category.BUN),
            3L, builder.setName("Salami").setPrice("0.90").setCals(90).build(Ingredient.Category.MEAT),
            4L, builder.setName("Schinken").setPrice("0.90").setCals(80).build(Ingredient.Category.MEAT),
            5L, builder.setName("Eisbergsalat").setPrice("0.40").setCals(20).build(Ingredient.Category.SALAD),
            6L, builder.setName("Rucolasalat").setPrice("0.60").setCals(20).build(Ingredient.Category.SALAD),
            7L, builder.setName("Tomate").setPrice("0.60").setCals(40).build(Ingredient.Category.VEGGIE),
            8L, builder.setName("Gurke").setPrice("0.30").setCals(20).build(Ingredient.Category.VEGGIE),
            9L, builder.setName("Mayo").setPrice("0.30").setCals(20).build(Ingredient.Category.SAUCE));

    public Map<Long, Ingredient> getAllArticles() { return articles; }
}
