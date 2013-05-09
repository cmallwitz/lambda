package com.snuffbumble.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Slide_1 {

    interface Predicate<T> {
        boolean test(T t);
    }

    interface Block<T> {
        void apply(T t);
    }

    public static void main(String... ignore) {

        List<String> list = Arrays.asList("Augustus", "Tiberius", "Caligula", "Claudius", "Nero", "Galba", "Otho");

        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).startsWith("C"))
                System.out.println(list.get(i));
        }

        System.out.println();

        for(String s : list) {
            if (s.startsWith("C"))
                System.out.println(s);
        }

        System.out.println();

        for(String s : list) {
            if (s.length() > 4)
                System.out.println(s);
        }

        System.out.println();

        print(findAllLongerThanFour(list));
    }

    public static List<String> findAll(List<String> list, Predicate filter) {
        List<String> found = new ArrayList<>();
        for(String s : list) { if (filter.test(s)) found.add(s); }
        return found;
    }

    public static List<String> findAllStartingWithC(List<String> list) {
        return findAll(list, new Predicate<String>() {
            public boolean test(String s) { return s.startsWith("C"); }
        });
    }

    public static List<String> findAllLongerThanFour(List<String> list) {
        return findAll(list, new Predicate<String>() {
            public boolean test(String s) { return s.length() > 4; }
        });
    }

    public static void print(List<String> list) {
        for(String s : list) { System.out.println(s); }
    }
}
