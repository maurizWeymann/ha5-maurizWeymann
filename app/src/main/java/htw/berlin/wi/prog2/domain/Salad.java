package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class Salad extends AbstractIngredient {
    public Salad(String name, BigDecimal price, int calories) {
        super(name, price, calories);
    }

    @Override
    public String toString() {
        return this.getName() + " als Salatbeilage";
    }

    @Override
    public Category getCategory() {
        return Category.SALAD;
    }
}
