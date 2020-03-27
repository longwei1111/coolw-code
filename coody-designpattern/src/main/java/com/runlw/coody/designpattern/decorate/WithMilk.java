package com.runlw.coody.designpattern.decorate;

/**
 * @Classname WithMilk
 * @Description 具体装饰类, 牛奶
 * @Author lw
 * @Date 2020-01-13 09:10
 */
public class WithMilk extends AbstractCoffeeDecorator {

    public WithMilk(Coffee house) {
        super(house);
    }

    /**
     * 价格
     *
     * @return double
     */
    @Override
    public double price() {
        double addPrice = 1;
        return super.price() + addPrice;
    }

    /**
     * 配料
     *
     * @return String
     */
    @Override
    public String ingredients() {
        String addIngredients = "milk";
        return super.ingredients() + "," + addIngredients;
    }
}
