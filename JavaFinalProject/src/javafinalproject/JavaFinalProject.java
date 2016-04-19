
package javafinalproject;

/**
 *
 * @author Justin
 */
public class JavaFinalProject {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        StockBuySellKTransactions sbt = new StockBuySellKTransactions();
        int prices[] = {1, 3, 4, 6, 2, 10};
//        int prices[] = {2, 5, 7, 1, 4, 3, 1, 3};
        System.out.println("\n1 Trade:\n");
        System.out.println("Max profit fast solution " + sbt.maxProfit(prices, 1));
        System.out.println("\n2 Trades:\n");
        System.out.println("Max profit fast solution " + sbt.maxProfit(prices, 2));
        System.out.println("\n3 Trades:\n");
        System.out.println("Max profit fast solution " + sbt.maxProfit(prices, 3));
        System.out.println("\n\nSlow Solution:\n\n");
        System.out.println("\n1 Trade:\n");
        System.out.println("Max profit slow solution " + sbt.maxProfitSlowSolution(prices, 1));
        System.out.println("\n2 Trades:\n");
        System.out.println("Max profit slow solution " + sbt.maxProfitSlowSolution(prices, 2));
        System.out.println("\n3 Trades:\n");
        System.out.println("Max profit slow solution " + sbt.maxProfitSlowSolution(prices, 3));
//        System.out.println("Max profit slow solution " + sbt.maxProfitSlowSolution(prices, 3));
    }
    
}
