package com.hyperskill.projects.coffee_machine;

import java.util.Scanner;

public class CoffeeMachine {
    static int waterInMachine = 400;
    static int milkInMachine = 540;
    static int coffeeInMachine = 120;
    static int cupsInMachine = 9;
    static int moneyInMachine = 550;
    static Scanner sc = new Scanner(System.in);

    public static void PrintState() {
        System.out.println("The coffee machine has:");
        System.out.println(waterInMachine + " of water");
        System.out.println(milkInMachine + " of milk"); 
        System.out.println(coffeeInMachine + " of coffee beans");
        System.out.println(cupsInMachine + " of disposable cups");
        System.out.println("$"+ moneyInMachine + " of money");
        System.out.println();
    }

    public static void RequestAction() {
        System.out.print("Write action (buy, fill, take, remaining, exit): ");
        String action = sc.next();
        System.out.println();
        switch(action) {
            case "buy":
                BuyCoffee();
                break;
            case "fill":
                FillMachine();
                break;
            case "take": 
                TakeMoney();
                break;
            case "remaining":
                PrintState();
                break;
            case "exit":
                return;
            default:
                System.out.println();
                break;
        }
        RequestAction();        
    }

    public static void updateState(Coffee cupToMake) {
        waterInMachine += cupToMake.waterToMake;
        milkInMachine += cupToMake.milkToMake;
        coffeeInMachine += cupToMake.coffeeBeansToMake;
        cupsInMachine += cupToMake.cup;
        moneyInMachine += cupToMake.moneyToBuy;
    }

    public static void BuyCoffee() {
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String coffee = sc.next();
        switch(coffee) {
            case "1":
                Coffee espresso = new Coffee(-250, 0, -16, 4, -1);
                checkMachine(espresso);
                break;
            case "2":
                Coffee latte = new Coffee(-350, -75, -20, 7, -1);
                checkMachine(latte);
                break;
            case "3":
                Coffee cappuccino = new Coffee(-200, -100, -12, 6, -1);
                checkMachine(cappuccino);
                break;
            case "back":
                System.out.println();
                return;
            default:
                System.out.println("Coffee not recognized");
                break;
        }
    }

    public static void checkMachine(Coffee cup) {
        if (waterInMachine + cup.waterToMake < 0) {
            System.out.println("Sorry, not enough water!");
            System.out.println();
            return;
        }
        if (milkInMachine + cup.milkToMake < 0) {
            System.out.println("Sorry, not enough milk!");
            System.out.println();
            return;
        }
        if (coffeeInMachine + cup.coffeeBeansToMake < 0) {
            System.out.println("Sorry, not enough coffee beans!");
            System.out.println();
            return;
        }
        if (cupsInMachine - cup.cup < 0) {
            System.out.println("Sorry, not enough cups!");
            System.out.println();
            return;
        }
        System.out.println("I have enough resources, making you a coffee!");
        System.out.println();
        updateState(cup);
    }
    
    public static void FillMachine() {
        System.out.println("Write how many ml of water do you want to add:");
        int water = sc.nextInt();
        System.out.println("Write how many ml of mulk do you want to add:");
        int milk = sc.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int coffee = sc.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cups = sc.nextInt();
        Coffee hackinCoffee = new Coffee(water, milk, coffee, 0, cups);
        updateState(hackinCoffee);
        System.out.println();
    }
    
    public static void TakeMoney() {
        System.out.println("I gave you $" + moneyInMachine);
        System.out.println();
        Coffee giffMeMoney = new Coffee(0, 0, 0, -moneyInMachine, 0);
        updateState(giffMeMoney);
    }

    public static void main(String[] args) {
        RequestAction();
    }
}

