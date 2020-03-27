package com.runlw.coody.designpattern.facade;

/**
 * @Classname ShowCarFacade
 * @Description 展览车门面接口实现类
 * @Author lw
 * @Date 2019-12-28 08:59
 */
public class ShowCarFacadeImpl implements ShowCarFacade {

    private BcCar bcCar;
    private BmwCar bmwCar;
    private AudiCar audiCar;

    public ShowCarFacadeImpl() {
        bcCar = new BcCar();
        bmwCar = new BmwCar();
        audiCar = new AudiCar();
    }

    /**
     * 展示奔驰车
     */
    @Override
    public void showCarTypeA() {
        bcCar.showCar();
    }

    /**
     * 展示奔驰车和宝马车
     */
    @Override
    public void showCarTypeB() {
        bcCar.showCar();
        bmwCar.showCar();
    }

    /**
     * 展示奔驰车、宝马车和奥迪车
     */
    @Override
    public void showCarTypeC() {
        bcCar.showCar();
        bmwCar.showCar();
        audiCar.showCar();
    }
}
