import java.util.*;
class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class StockTradingPlatform {  

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<String, Stock> market = new HashMap<>();
        market.put("TCS", new Stock("TCS", 3500));
        market.put("INFY", new Stock("INFY", 1500));

        HashMap<String, Integer> portfolio = new HashMap<>();
        double balance = 10000;

        while (true) {
            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. View Portfolio");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    for (Stock s : market.values()) {
                        System.out.println(s.name + " : " + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock name: ");
                    String name = sc.next();

                    if (market.containsKey(name)) {

                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();

                        double cost = market.get(name).price * qty;

                        if (cost <= balance) {
                            balance -= cost;
                            portfolio.put(name, portfolio.getOrDefault(name, 0) + qty);
                            System.out.println("Stock Purchased!");
                        } else {
                            System.out.println("Insufficient Balance!");
                        }

                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;

                case 3:
                    System.out.println("Balance: " + balance);
                    for (String stock : portfolio.keySet()) {
                        System.out.println(stock + " : " + portfolio.get(stock));
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
 }
 
