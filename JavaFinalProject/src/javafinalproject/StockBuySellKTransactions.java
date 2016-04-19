/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafinalproject;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Date 12/22/2015
 * @author Tushar Roy
 *
 * Given stockc prices for certain days and at most k transactions how to buy and sell
 * to maximize profit.
 *
 * Time complexity - O(number of transactions * number of days)
 * Space complexity - O(number of transcations * number of days)
 *
 * https://leetcode.com/discuss/15153/a-clean-dp-solution-which-generalizes-to-k-transactions
 */
public class StockBuySellKTransactions {

    /**
     * This is faster method which does optimization on slower method
     * Time complexity here is O(K * number of days)
     *
     * Formula is
     * T[i][j] = max(T[i][j-1], prices[j] + maxDiff)
     * maxDiff = max(maxDiff, T[i-1][j] - prices[j])
     */
    public int maxProfit(int prices[], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K+1][prices.length];
        int optimalSolution = 0;

        for (int i = 1; i < T.length; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < T[0].length; j++) {
                T[i][j] = Math.max(T[i][j-1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, T[i-1][j] - prices[j]);
            }
        }
        
        if(T[0][prices.length-1] == T[K][prices.length-1]){
            optimalSolution = 0;
        }
        else if(T[1][prices.length-1] == T[K][prices.length-1]){
            optimalSolution = 1;
        }
        else if(T[2][prices.length-1] == T[K][prices.length-1]){
            optimalSolution = 2;
        }
        else if(T[3][prices.length-1] == T[K][prices.length-1]){
            optimalSolution = 3;
        }
        System.out.println("\nOptimal number of trades to do: " + optimalSolution);
        printActualSolution(T, prices, optimalSolution);
//        printActualSolution(T, prices);
        return T[K][prices.length-1];
    }

    /**
     * This is slow method but easier to understand.
     * Time complexity is O(k * number of days ^ 2)
     * T[i][j] = max(T[i][j-1], max(prices[j] - prices[m] + T[i-1][m])) where m is 0...j-1
     */
    public int maxProfitSlowSolution(int prices[], int trades) {
        if (trades == 0 || prices.length == 0) {
            return 0;
        }
        int transactions[][] = new int[trades+1][prices.length];
        int optimalSolution = 0;
        int ithTrade;

//        for (ithTrade = 1; ithTrade < transactions.length; ithTrade++) {
//            for (int j = 1; j < transactions[0].length; j++) {
//                int maxVal = 0;
//                for (int prevDay = 0; prevDay < j; prevDay++) {
//                    if(prevDay-1 >= 0){
//                        maxVal = Math.max(maxVal, prices[j] - prices[prevDay] + transactions[ithTrade-1][prevDay-1]);
//                    }
//                    else{
//                        maxVal = Math.max(maxVal, prices[j] - prices[prevDay] + transactions[ithTrade-1][prevDay]);
//                    }
//                }
//                transactions[ithTrade][j] = Math.max(transactions[ithTrade][j-1], maxVal);
//            }
//        }
//        System.out.println("Printing content of 2d array: " + Arrays.deepToString(transactions));
        for (ithTrade = 1; ithTrade < transactions.length; ithTrade++) {
            for (int j = 1; j < transactions[0].length; j++) {
                int maxVal = 0;
                for (int prevDay = 0; prevDay < j; prevDay++) {
                    maxVal = Math.max(maxVal, prices[j] - prices[prevDay] + transactions[ithTrade-1][prevDay]);
                }
                transactions[ithTrade][j] = Math.max(transactions[ithTrade][j-1], maxVal);
            }
        }
        System.out.println("Printing content of 2d array: " + Arrays.deepToString(transactions));
//        for(i = 0; i < K; i++){
//            if(T[i][prices.length-1] == T[K][prices.length-1]){
//                optimalSolution = i;
//            }
//        }
        if(transactions[0][prices.length-1] == transactions[trades][prices.length-1]){
            optimalSolution = 0;
        }
        else if(transactions[1][prices.length-1] == transactions[trades][prices.length-1]){
            optimalSolution = 1;
        }
        else if(transactions[2][prices.length-1] == transactions[trades][prices.length-1]){
            optimalSolution = 2;
        }
        else if(transactions[3][prices.length-1] == transactions[trades][prices.length-1]){
            optimalSolution = 3;
        }
        System.out.println("\nOptimal number of trades to do: " + optimalSolution);
        printActualSolution(transactions, prices, optimalSolution);
//        printActualSolution(T, prices);
        
        
        return transactions[trades][prices.length - 1];
        
    }

    public void printActualSolution(int T[][], int prices[], int optimalSolution) {
//        int i = T.length - 1;     //uncomment this and comment out next line to see Overlapping Buy/Sell error for assignment input
        int i = optimalSolution;    //THIS DOESN'T FIX OVERLAPPING BUY/SELL ON SAME DAY PROBLEM FOR R = 3!!!!!!!!!!!
        int j = T[0].length - 1;

        Deque<Integer> stack = new LinkedList<>();
        while(true) {
            if(i == 0 || j == 0) {
                break;
            }
            if (T[i][j] == T[i][j-1]) {
                j = j - 1;
            } else {
                stack.addFirst(j);
                int maxDiff = T[i][j] - prices[j];
                for (int k = j-1; k >= 0; k--) {
                    if (T[i-1][k] - prices[k] == maxDiff) {
                        i = i - 1;
                        j = k;
                        stack.addFirst(j);
                        break;
                    }
                }
            }
        }

        while(optimalSolution > 0 && !stack.isEmpty()) {
//        while(!stack.isEmpty()) {
            System.out.println("Buy at day " + (stack.pollFirst()+1));  //start at day 1
//            System.out.println("Buy at day " + (stack.pollFirst()));  //start at day 0
//            System.out.println(stack.pollLast());
            
//            System.out.println(stack.pollFirst()+1);
//            System.out.println("Buy at price " + prices[stack.pollFirst()]);
//            System.out.println("Sell at price " + prices[stack.pollFirst()]);
            System.out.println("Sell at day " + (stack.pollFirst()+1)); //start at day 1
//            System.out.println("Sell at day " + (stack.pollFirst())); //start at day 0
//            System.out.println(optimalSolution);
            optimalSolution--;
        }
    }
}
