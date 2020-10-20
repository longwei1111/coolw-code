package com.coolw.designpattern.decorate;

/**
 * @Classname AbstractCoffeeDecorator
 * @Description 抽象装饰类, 咖啡的"装饰器"
 * @Author lw
 * @Date 2020-01-13 09:04
 */
public abstract class AbstractCoffeeDecorator implements Coffee {

    protected Coffee coffee;

    public AbstractCoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    /**
     * 价格
     *
     * @return double
     */
    @Override
    public double price() {
        return coffee.price();
    }

    /**
     * 配料
     *
     * @return String
     */
    @Override
    public String ingredients() {
        return coffee.ingredients();
    }
}
