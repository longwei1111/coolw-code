package com.coolw.code.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/20 10:23
 * @since 1.0
 */
public class Demo1 {
    
    @Test
    public void test1() {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(3, 0.45));
        apples.add(new Apple(1, 0.35));
        apples.add(new Apple(2, 0.25));
        apples.add(new Apple(4, 0.55));
        
        // 按苹果重量排序，默认升序
        // 普通代码比较
        /*Collections.sort(apples, new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });*/
        
        // 使用java8，排序 
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(apples);
    }
    
    @Test
    public void test2() {
        List<String> title = Arrays.asList("Java8", "In", "Action", "redis", "RabbitMQ");
        //title.stream().skip(2).forEach(System.out::println);
        title.stream().map(String::length).forEach(System.out::println);
        
        String[] words = {"abc", "efg"};
        List<String> list = Arrays.stream(words).map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct().collect(Collectors.toList());
        System.out.println(list);
    }
    
    @Test
    public void test3() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        nums.stream().map(n -> n * n).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("-------------------------------");
        
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);
        nums1.stream()
                .flatMap(n -> nums2.stream().map(m -> new int[]{n, m}))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
    
    @Test
    public void test4() {
        List<String> title = Arrays.asList("Java8", "In", "Action", "redis", "RabbitMQ", "In");
        // anyMatch返回的是一个boolean，因此是终端操作
        boolean match = title.stream().anyMatch(t -> Objects.equals("In", t));
        System.out.println(match);
        
        // allMatch返回的是一个boolean，因此是终端操作
        boolean match1 = title.stream().allMatch(t -> Objects.equals("In", t));
        System.out.println(match1);

        boolean match2 = title.stream().noneMatch(t -> Objects.equals("MySQL", t));
        System.out.println(match2);
    }

    @Test
    public void test5() {
        List<String> title = Arrays.asList("Java8", "In", "Action", "redis", "RabbitMQ");
        Optional<String> any = title.stream().findAny();
        if (any.isPresent()) {
            System.out.println(any.get());
        }

        Optional<String> first = title.stream().findFirst();
        if (first.isPresent()) {
            System.out.println(any.get());
        }
    }

    @Test
    public void test6() {
        // 元素求和
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = nums.stream().reduce(0, Integer::sum);
        System.out.println("求和:" + sum);

        Optional<Integer> opt = nums.stream().reduce(Integer::sum);
        if (opt.isPresent()) {
            System.out.println("求和:" + opt.get());
        }

        Integer sum2 = nums.stream().reduce(1, (n1, n2) -> n1 * n2);
        System.out.println("乘积:" + sum2);
        
        // 最大值，最小值
        Optional<Integer> maxOpt = nums.stream().reduce(Integer::max);
        if (maxOpt.isPresent()) {
            System.out.println("最小值:" + maxOpt.get());
        }
        Optional<Integer> minOpt = nums.stream().reduce(Integer::min);
        if (minOpt.isPresent()) {
            System.out.println("最大值:" + minOpt.get());
        }
    }
    
    @Test
    public void test7() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = nums.stream().mapToInt(r -> r).sum();
        System.out.println("sum:" + sum);

        Long count = nums.stream().collect(Collectors.counting());
        long count1 = nums.stream().count();
        System.out.println("count=" + count + ",count1=" + count1);

        long sum1 = nums.stream().collect(Collectors.summarizingInt(r -> r)).getSum();
        System.out.println("sum1=" + sum1);
    }
    
}
