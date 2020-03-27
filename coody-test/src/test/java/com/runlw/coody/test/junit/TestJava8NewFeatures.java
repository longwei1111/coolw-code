package com.runlw.coody.test.junit;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Classname TestJava8NewFeatures
 * @Description java8新特性测试类
 * @Date 2019/10/9 10:03
 * @Created by lw
 */
public class TestJava8NewFeatures {

    /**
     * 用lambda表达式替代匿名内部类
     */
    @Test
    public void test_1() {
        // 匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("The old runable now is using!");
            }
        }).start();

        // lambda表达式
        new Thread(() -> System.out.println("It's a lambda function!")).start();
    }

    /**
     * 用lambda表达式对集合进行迭代
     */
    @Test
    public void test_2() {
        List<String> list = Arrays.asList("java", "scala", "python");
        // before java8
        for (String str : list) {
            System.out.println(str);
        }

        // after java8
        list.forEach(System.out::println);
        list.forEach(str -> System.out.println(str));
    }

    /**
     * 用lambda表达式实现map
     */
    @Test
    public void test_3() {
        List<Double> list = Arrays.asList(10.0, 20.0, 30.0);
        list.stream().map(l -> l + l * 0.05).forEach(System.out::println);
    }

    /**
     * 用lambda表达式实现map与reduce
     */
    @Test
    public void test_4() {
        List<Double> list = Arrays.asList(10.0, 20.0, 30.0);
        double totalSum = list.stream().map(l -> l + l * 0.05).reduce((sum, x) -> sum + x).get();
        System.out.println(totalSum);
    }

    /**
     * filter过滤
     */
    @Test
    public void test_5() {
        List<Double> list = Arrays.asList(10.0, 20.0, 30.0, 40.0);
        List<Double> resultList = list.stream().filter(l -> l > 25.0).collect(Collectors.toList());
        resultList.forEach(System.out::println);
    }

    public static void filterTest(List<String> languages, Predicate<String> condition) {
        languages.stream().filter(x -> condition.test(x)).forEach(x -> System.out.println(x + " "));
    }

    /**
     * 与函数式接口Predicate配合
     */
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "scala", "Python");

        System.out.println("\nLanguage starts with J：");
        filterTest(languages, x -> x.startsWith("J"));

        System.out.println("\nLanguage ends with n：");
        filterTest(languages, x -> x.endsWith("n"));

        System.out.println("\nAll languages: ");
        filterTest(languages, x -> true);

        System.out.println("\nNo languages: ");
        filterTest(languages, x -> false);

        System.out.println("\nLanguage length bigger three: ");
        filterTest(languages, x -> x.length() > 4);
    }

}
