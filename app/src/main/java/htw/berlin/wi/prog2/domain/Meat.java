package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class Meat extends AbstractIngredient {
    public Meat(String name, BigDecimal price, int calories) {
        super(name, price, calories);
    }

    @Override
    public Category getCategory() {
        return Category.MEAT;
    }


}
