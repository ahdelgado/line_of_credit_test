package com.company;

public class Main {

    public static void main(String[] args) {
	    Account bob = new Account(1000, 35);
        Account tom = new Account(1000,35);

        bob.withdraw(500, 0);
        System.out.println("Bob's payoff amount is $" + Math.round(bob.get_payoff_amount() * 100.0) / 100.0);
        System.out.println("Bob's interest is $" + Math.round(bob.getInterest() * 100.0) / 100.0);
        System.out.println();

        tom.withdraw(500, 0);
        tom.make_payment(200, 15);
        tom.withdraw(100, 25);
        System.out.println("Tom's payoff amount is $" + Math.round(tom.get_payoff_amount() * 100.0) / 100.0);
        System.out.println("Tom's interest is $" + Math.round(tom.getInterest() * 100.0) / 100.0);

    }
}
