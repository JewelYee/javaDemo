package com.yee.demo.javajdk.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020/9/10 3:24 PM
 */
public class ListDemo {

    public static void main(String[] args) {

        List<Student> appleList = new ArrayList<>();

        Student apple1 =  new Student(1,"苹果1",new BigDecimal("3.25"),10);
        Student apple2 = new Student(1,"苹果1",new BigDecimal("1.35"),20);
        Student apple3 =  new Student(2,"香蕉",new BigDecimal("2.89"),30);
        appleList.add(apple1);
        appleList.add(apple2);
        appleList.add(apple3);
        // list -> Map<key,List<Object>>
//        Map<Integer, List<Student>> groupBy = appleList.stream().collect(Collectors.groupingBy(Student::getId));

        //list -> map
//        Map<Integer, Student> appleMap = appleList.stream().collect(Collectors.toMap(Student::getId, a -> a,(k1,k2)->k1));

        //去重
//        List<Student> unique1 = appleList.stream().collect(
//                collectingAndThen(
//                        toCollection(() -> new TreeSet<>(comparingLong(Student::getId))), ArrayList::new));

        List<Student> unique2 = appleList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparing(Student::getName))), ArrayList::new));

        System.out.println(unique2);
    }


}
