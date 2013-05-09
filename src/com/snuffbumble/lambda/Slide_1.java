package com.snuffbumble.lambda;

import java.util.Arrays;
import java.util.List;

public class Slide_1 {

    interface Predicate<T> {
        boolean evaluate(T t);
    }



    public static void main(String... ignore) {

        List<String> list = Arrays.asList("Augustus", "Tiberius", "Caligula", "Claudius", "Nero");

        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).startsWith("C"))
                System.out.println(list.get(i));
        }

        for(String s : list) { // loop control
            if (s.startsWith("C")) // filter
                System.out.println(s); // action
        }

        for(String s : list) {
            if (s.startsWith("C"))
                System.out.println(s);
        }

    }
}
