package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alcides on 8/13/2015.
 */
public class Account{
    private int limit;
    private double available_credit;
    private double balance;
    private double payoff;
    private double apr;
    private double interest;
    private static final int DAYS_IN_YEAR = 365;
    private static final int DAYS_IN_MONTH = 30;
    private int days_owed = 0;
    private int day_withdrawn = 0;
    private int day_paid = 0;
    private boolean last_transaction_withdrawl;

    // Use this hashmap to keep track of balances to corresponding real days owed
    Map<Double, Integer> balance_and_days = new HashMap<>();

    public Account(double credit, double apr) {
        this.available_credit = credit;
        this.apr = apr/100;
    }

    public void withdraw(int amount, int day_withdrawn){
        if(available_credit > amount) {
            if(day_withdrawn != 0) {
                days_owed = day_withdrawn - days_owed;
                balance_and_days.put(balance, days_owed);
            }
            balance += amount;
            available_credit -= amount;
            this.day_withdrawn = day_withdrawn;
            last_transaction_withdrawl = true;
        }
        else{
            System.out.println("Your have insufficient credit to make this transaction.");
        }
    }

    public void make_payment(int amount, int day_paid){
        if(balance >= amount) {
            days_owed = day_paid - day_withdrawn;
            balance_and_days.put(balance, days_owed);
            balance -= amount;
            available_credit += amount;
            last_transaction_withdrawl = false;
            this.day_paid = day_paid;
        }
        else{
            System.out.println("Your payment exceeds the balance owed.");
        }
    }

    public double get_payoff_amount(){
        //If customer never made a payment
        if(balance_and_days.isEmpty()) {
            interest = (((balance * apr) / DAYS_IN_YEAR) * DAYS_IN_MONTH);
        }
        else{
            if( last_transaction_withdrawl) {
                balance_and_days.put(balance, DAYS_IN_MONTH - day_withdrawn);
            }
            else{
                balance_and_days.put(balance, DAYS_IN_MONTH - day_paid);
            }

            //APR Calculation based on the outstanding principal balance over real number of days
            for(Map.Entry<Double, Integer> entry: balance_and_days.entrySet()){
                interest += ((entry.getKey()*apr) / DAYS_IN_YEAR) * entry.getValue();
            }
        }
        payoff = balance + interest;
        days_owed = 0;  // reset days owed for new month
        return payoff;
    }

    // Getters below. No setters allowed due to security and data encapsulation.
    public int getLimit() {
        return limit;
    }

    public double getAvailable_credit() {
        return available_credit;
    }

    public double getBalance() {
        return balance;
    }

    public double getApr() {
        return apr;
    }

    public double getInterest() {
        return interest;
    }


}
