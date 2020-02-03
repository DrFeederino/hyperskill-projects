package com.hyperskill.projects.coffee_machine;

class Coffee {
    int waterToMake;
    int milkToMake;
    int coffeeBeansToMake;
    int moneyToBuy;
    int cup;

    public Coffee(int water, int milk, int coffee, int money, int cups) {
        waterToMake = water;
        milkToMake = milk;
        coffeeBeansToMake = coffee;
        moneyToBuy = money;
        cup = cups;
    }
}
