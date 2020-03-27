package com.runlw.coody.designpattern.junit;

import com.runlw.coody.designpattern.abstractfactory.ElectricalAbstractFactory;
import com.runlw.coody.designpattern.abstractfactory.haier.HaierFactory;
import com.runlw.coody.designpattern.abstractfactory.media.MediaFactory;
import com.runlw.coody.designpattern.adapter.classadapter.AmericanMovieTarget;
import com.runlw.coody.designpattern.adapter.classadapter.ClassMovieAdapter;
import com.runlw.coody.designpattern.adapter.classadapter.KoreanDramasAdapteeImpl;
import com.runlw.coody.designpattern.adapter.objectadapter.ObjectMovieAdapter;
import com.runlw.coody.designpattern.facade.ShowCarFacadeImpl;
import com.runlw.coody.designpattern.abstractfactory.AirConditionerFactory;
import com.runlw.coody.designpattern.abstractfactory.FridgeFactory;
import com.runlw.coody.designpattern.adapter.interfaceadpter.InterfaceMovieAdapter;
import com.runlw.coody.designpattern.builder.DanceRobotBuilder;
import com.runlw.coody.designpattern.builder.Director;
import com.runlw.coody.designpattern.builder.Robot;
import com.runlw.coody.designpattern.decorate.SimpleCoffee;
import com.runlw.coody.designpattern.decorate.Coffee;
import com.runlw.coody.designpattern.decorate.WithMilk;
import com.runlw.coody.designpattern.decorate.WithSugar;
import com.runlw.coody.designpattern.facade.ShowCarFacade;
import com.runlw.coody.designpattern.factorymethod.multi.ColaFactory;
import com.runlw.coody.designpattern.factorymethod.multi.DrinksFactory;
import com.runlw.coody.designpattern.factorymethod.multi.DrinksProduct;
import com.runlw.coody.designpattern.factorymethod.multi.SpriteFactory;
import com.runlw.coody.designpattern.factorymethod.simple.MailSender;
import com.runlw.coody.designpattern.factorymethod.simple.Sender;
import com.runlw.coody.designpattern.factorymethod.simple.SmsSender;
import com.runlw.coody.designpattern.observer.LsStockholder;
import com.runlw.coody.designpattern.observer.PingAnSecurities;
import com.runlw.coody.designpattern.observer.Subject;
import com.runlw.coody.designpattern.observer.ZsStockholder;
import com.runlw.coody.designpattern.proxy.Ticket;
import com.runlw.coody.designpattern.proxy.TicketImpl;
import com.runlw.coody.designpattern.proxy.cglibdynamicproxy.CglibProxyFactory;
import com.runlw.coody.designpattern.proxy.jdkdynamicproxy.JdkProxyFactory;
import com.runlw.coody.designpattern.proxy.staticproxy.StaticProxy;
import com.runlw.coody.designpattern.singleton.HungrySingleton;
import com.runlw.coody.designpattern.singleton.LazySingleton;
import com.runlw.coody.designpattern.strategy.AudiCar;
import com.runlw.coody.designpattern.strategy.BcCar;
import com.runlw.coody.designpattern.strategy.BmwCar;
import com.runlw.coody.designpattern.strategy.CarContext;
import com.runlw.coody.designpattern.templatemethod.HouseOne;
import com.runlw.coody.designpattern.templatemethod.HouseTemplate;
import com.runlw.coody.designpattern.templatemethod.HouseTwo;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Classname DesignpatternlTest
 * @Description 设计模式测试类
 * @Author lw
 * @Date 2019-12-21 09:15
 */
public class DesignpatternlTest {

    /**
     * 单例模式-懒汉式
     */
    @Test
    public void testLazySingleton() {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        Assert.assertFalse(null == lazySingleton);
    }

    /**
     * 单例模式-饿汉式
     */
    @Test
    public void testHungrySingleton() {
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        Assert.assertFalse(null == hungrySingleton);
    }

    /**
     * 工厂设计模式-普通工厂模式
     */
    @Test
    public void testNormalFactory() {
        // 邮件发送
        Sender sender = new MailSender();
        sender.send();
        // 短信发送
        Sender sender1 = new SmsSender();
        sender1.send();
    }

    /**
     * 工厂设计模式-工厂方法模式
     */
    @Test
    public void testFactoryMethod() {
        // 生产可乐
        DrinksFactory drinksFactory = new ColaFactory();
        DrinksProduct drinksProduct = drinksFactory.product();
        drinksProduct.show();
        // 生产雪碧
        DrinksFactory drinksFactory1 = new SpriteFactory();
        DrinksProduct drinksProduct1 = drinksFactory1.product();
        drinksProduct1.show();
    }

    /**
     * 工厂设计模式-抽象工厂模式
     */
    @Test
    public void testAbstractFactory() {
        // 海尔
        ElectricalAbstractFactory haierFactory = new HaierFactory();
        FridgeFactory haierFridge = haierFactory.createFridge();
        haierFridge.fridgeInfo();
        AirConditionerFactory haierAirConditioner = haierFactory.createAirConditioner();
        haierAirConditioner.airConditionerInfo();

        System.out.println("------------------------");

        // 美的
        ElectricalAbstractFactory mediaFactory = new MediaFactory();
        FridgeFactory mediaFridge = mediaFactory.createFridge();
        mediaFridge.fridgeInfo();
        AirConditionerFactory mediaAirConditioner = mediaFactory.createAirConditioner();
        mediaAirConditioner.airConditionerInfo();
    }

    /**
     * 建造者模式
     */
    @Test
    public void testBuilder() {
        Director director = new Director();
        Robot robot = director.createRobotByDirecotr(new DanceRobotBuilder());
        System.out.println(robot.getHead());
        System.out.println(robot.getBody());
        System.out.println(robot.getHand());
        System.out.println(robot.getFoot());
        System.out.println("机器人创建成功！！！");

    }

    /**
     * 策略模式
     */
    @Test
    public void testStrategy() {
        // 宝马车
        CarContext bmwCarContext1 = new CarContext(new BmwCar());
        bmwCarContext1.buyCarStrategy();
        // 奔驰车
        CarContext bcCarContext = new CarContext(new BcCar());
        bcCarContext.buyCarStrategy();
        // 奥迪车
        CarContext audiCarContext = new CarContext(new AudiCar());
        audiCarContext.buyCarStrategy();
    }

    /**
     * 适配器模式-类适配器
     */
    @Test
    public void testClassAdapter() {
        AmericanMovieTarget americanMovie = new ClassMovieAdapter();
        americanMovie.playAmericanMovie();
    }

    /**
     * 适配器模式-对象适配器
     */
    @Test
    public void testObjectAdapter() {
        AmericanMovieTarget americanMovie = new ObjectMovieAdapter(new KoreanDramasAdapteeImpl());
        americanMovie.playAmericanMovie();
    }

    /**
     * 适配器模式-接口适配器
     */
    @Test
    public void testInterfacaAdapter() {
        InterfaceMovieAdapter koreanDramasAdapter = new InterfaceMovieAdapter();
        koreanDramasAdapter.playAmericanMovie();
        ;
        koreanDramasAdapter.playKoreanDramas();
    }

    /**
     * 代理模式-静态代理
     */
    @Test
    public void testStaticProxy() {
        // 目标对象
        Ticket ticket = new TicketImpl();
        // 代理对象
        StaticProxy cattleProxy = new StaticProxy(ticket);
        // 买票
        cattleProxy.buyTicket();
    }

    /**
     * 代理模式-JDK动态代理
     */
    @Test
    public void testJdkDynamicProxy() {
        // 目标对象
        Ticket ticket = new TicketImpl();
        // 给目标对象，创建代理对象
        Ticket proxy = (Ticket) new JdkProxyFactory(ticket).getProxyInstance();
        // 买票
        proxy.buyTicket();
    }

    /**
     * 代理模式-CGLib动态代理
     */
    @Test
    public void testCglibDynamicProxy() {
        // 目标对象
        Ticket ticket = new TicketImpl();
        // 代理对象
        Ticket proxy = (Ticket) new CglibProxyFactory(ticket).getProxyInstance();
        // 买票
        proxy.buyTicket();
    }

    /**
     * 外观模式
     */
    @Test
    public void testFacade() {
        // 创建门面对象
        ShowCarFacade showCarFacade = new ShowCarFacadeImpl();
        // 展示汽车A类
        showCarFacade.showCarTypeA();
        System.out.println("============");
        // 展示汽车B类
        showCarFacade.showCarTypeB();
        System.out.println("============");
        // 展示汽车C类
        showCarFacade.showCarTypeC();
    }

    /**
     * 观察者模式
     */
    @Test
    public void testObserver() {
        // 创建目标类(平安证券)
        Subject subject = new PingAnSecurities();
        // 添加张三和李四股民观察者
        subject.add(new ZsStockholder());
        subject.add(new LsStockholder());
        // 股票上涨
        subject.increase();
        // 股票下跌
        subject.decrease();
    }

    /**
     * 装饰者模式
     */
    @Test
    public void testDecorate() {
        // 原味咖啡
        Coffee c = new SimpleCoffee();
        System.out.println("花费了: " + c.price());
        System.out.println("配料: " + c.ingredients());
        System.out.println("============");

        // 增加牛奶的咖啡
        c = new WithMilk(c);
        System.out.println("花费了: " + c.price());
        System.out.println("配料: " + c.ingredients());
        System.out.println("============");

        // 增加糖的咖啡
        c = new WithSugar(c);
        System.out.println("花费了: " + c.price());
        System.out.println("配料: " + c.ingredients());
        System.out.println("============");
    }

    /**
     * 模板方法模式
     */
    @Test
    public void testTemplateMethod() {
        // 创建房子模板对象
        HouseTemplate houseOne = new HouseOne("房子模板one", false);
        HouseTemplate houseTwo = new HouseTwo("房子模板two");
        // 房子构建模板
        System.out.println("=== 房子模板one,构建模板 ===");
        houseOne.buildHouse();
        System.out.println("=== 房子模板two,构建模板 ===");
        houseTwo.buildHouse();
    }
}
