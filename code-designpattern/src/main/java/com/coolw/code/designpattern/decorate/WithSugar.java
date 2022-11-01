package com.coolw.code.designpattern.decorate;

/**
 * @Classname WithSugar
 * @Description 具体装饰类, 糖
 * @Author lw
 * @Date 2020-01-13 09:10
 */
public class WithSugar extends AbstractCoffeeDecorator {

    public WithSugar(Coffee house) {
        super(house);
    }

    /**
     * 价格
     *
     * @return double
     */
    @Override
    public double price() {
        double addPrice = 0.5;
        return super.price() + addPrice;
    }

    /**
     * 配料
     *
     * @return String
     */
    @Override
    public String ingredients() {
        String addIngredients = "sugar";
        return super.ingredients() + "," + addIngredients;
    }
}
