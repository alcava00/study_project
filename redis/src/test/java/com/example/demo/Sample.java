package com.example.demo;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Sample {
    public static void main(String[] args) {
        String[] aa = {"daa", "cbbddddd", "fcdc"};
        Collector d;
        Map<String, Integer> map = Arrays.asList(aa).stream().collect(Collectors.toMap(key -> key, value -> value.length()));

        System.out.println("Map : " + map);
        Arrays.asList(aa).stream().sorted(
                (a, b) -> {
                    return a.compareTo(b);
                }
        ).forEach(s -> System.out.println("V:" + s));

        Optional<String> maxValue = Arrays.asList(aa).stream().reduce((a, b) -> a.compareTo(b) > 0 ? a : b);

        System.out.println("MaxValue: " + maxValue.get());

        String s = "foobar";
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                System.out.println(val);
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        System.out.println("'" + s + "' to binary: " + binary);

        int k = 4;

        System.out.println(Integer.toBinaryString(k));
        System.out.println(Integer.parseInt("100", 2));

        System.out.println(Long.parseLong("01100110 01101111 01101111 01100010 01100001 01110010".replaceAll(" ", ""), 2));
        System.out.println(k << 3);

        System.out.println(Integer.toBinaryString(12));
        System.out.println( Integer.toBinaryString(Integer.reverse(Integer.parseInt("100", 2))));

    }
}
