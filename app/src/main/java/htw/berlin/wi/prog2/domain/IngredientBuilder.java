package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class IngredientBuilder {

    private String name;
    private BigDecimal price;
    private int calories;

    public IngredientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public IngredientBuilder setPrice(String price) {
        this.price = new BigDecimal(price);
        return this;
    }

    public IngredientBuilder setCals(int calories) {
        this.calories = calories;
        return this;
    }

    public Ingredient build(Ingredient.Category cat) {
        return switch (cat) {
            case BUN -> new Bun(name, price, calories);
            case MEAT -> new Meat(name, price, calories);
            case SALAD -> new Salad(name, price, calories);
            case SAUCE -> new Sauce(name, price, calories);
            case VEGGIE -> new Veggie(name, price, calories);
        };
    }
}
