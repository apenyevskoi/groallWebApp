package com.noda.groall;

import java.text.DecimalFormat;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        double tokenMaxAmountDouble = 12355 / 100;
        String s = Double.toString(Math.floor(tokenMaxAmountDouble));
//        System.out.println(s.split(".")[0]);

        String ss = "one.two";
        System.out.println(ss.split("\\.")[1]);
//        int tokenMaxAmountInt =
//                Integer.parseInt();
//        System.out.println(tokenMaxAmountInt);
    }
}