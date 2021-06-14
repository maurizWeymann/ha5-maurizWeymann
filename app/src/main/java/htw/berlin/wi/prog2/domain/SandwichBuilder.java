package htw.berlin.wi.prog2.domain;

import htw.berlin.wi.prog2.data.Menu; // TODO Diesen Import sollten Sie nach Teilaufgabe 4 entfernen können
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static htw.berlin.wi.prog2.domain.Ingredient.Category.*;

public class SandwichBuilder {

    private List<Ingredient> ingredients = new ArrayList<>();

    public enum CreationStyle {
        PRECOMPUTED,
        DYNAMICALLY_COMPUTED
    }

    private CreationStyle creationStyle;

    public void setCreationStyle(CreationStyle creationStyle) {
        this.creationStyle = creationStyle;
    }

    public SandwichBuilder add(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public Sandwich build() {
        checkRules();
        return creationStyle == CreationStyle.PRECOMPUTED ? buildPrecomputed() : buildDynamicallyComputed();
    }

    private void checkRules() {
        if(ingredients.size() < 2) throw new IllegalSandwichException("Nicht genügend Zutaten");

        if(ingredients.stream().filter((ing) -> ing.getCategory().equals(BUN)).collect(Collectors.toList()).size() > 1)
            throw new IllegalSandwichException("Zwei Brote in einem Sandwich geht nicht");

        if(ingredients.stream().filter((ing) -> ing.getCategory().equals(SAUCE)).collect(Collectors.toList()).size() < 1)
            throw new IllegalSandwichException("Ein Sandwich braucht mindestens eine Sauce");
    }

    private Sandwich buildPrecomputed() {
        BigDecimal price = BigDecimal.ZERO;
        int calories = 0;
        List<String> ingredientNames = new ArrayList<>();

        for (Ingredient ing : ingredients) {
            price = price.add(ing.getPrice());
            calories += ing.getCalories();
            ingredientNames.add(ing.getName());
        }
        ingredients.clear();

        return new PrecomputedSandwich(price, calories, ingredientNames);
    }

    private Sandwich buildDynamicallyComputed() {
        List<Ingredient> ingsToPass = List.copyOf(ingredients);
        ingredients.clear();
        return new DynamicallyComputedSandwich(ingsToPass);
    }

    public SandwichBuilder(CreationStyle creationStyle) {
        this.creationStyle = creationStyle;
    }

    public SandwichBuilder() {
        this(CreationStyle.PRECOMPUTED);
    }
}
