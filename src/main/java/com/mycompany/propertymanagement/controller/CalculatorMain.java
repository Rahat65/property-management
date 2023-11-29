package com.mycompany.propertymanagement.controller;

public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorController cc = new CalculatorController();
        Double result = cc.add(4.5,8.5, 10.5);
        System.out.println(result);
    }
}
