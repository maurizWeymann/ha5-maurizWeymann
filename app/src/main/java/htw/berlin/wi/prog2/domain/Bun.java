package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class Bun extends AbstractIngredient {
    public Bun(String name, BigDecimal price, int calories) {
        super(name, price, calories);
    }

    @Override
    public String toString() {
        return this.getName() + "-Brötchen";
    }

    @Override
    public Category getCategory() {
        return Category.BUN;
    }
}
