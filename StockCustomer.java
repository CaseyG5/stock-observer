package stockexchange;

public class StockCustomer {
    final String custName;
    int currentPrice;
    int steps;                          // to keep track of the # of price  
                                        // changes until price reaches his limit
    StockCustomer(String name) {
        custName = name;
        steps = 0;
    }
}
class StockMonitor extends StockCustomer implements StockObserver {
    final int buyPrice;
    int priceLimit;
    
    StockMonitor(StockExchange se, String name, int limit) {
        super(name);                        // steps initialized to 0 by super
        priceLimit = limit;
        currentPrice = buyPrice = se.price;
        se.AddObserver(this);                   // subscribe immediately
    }
    public void priceChanged(PriceChangedEvent evt) {
        if(evt.priceWentUp) currentPrice++;     // update price
        else currentPrice--;
        steps++;                                
        
        // if the current price has reached his limit
        if(Math.abs(currentPrice - buyPrice) == priceLimit) {
            evt.se.removeWatcher = true;                // please unsubscribe me
            System.out.println("Customer \"" + custName + 
                    "\" has sold their shares.");
        }
    }
}
class StockPricePrinter extends StockCustomer implements StockObserver {
    
    StockPricePrinter(StockExchange se, String name) {
        super(name);
        // steps = 0
        se.AddObserver(this);       // subscribe immediately
        currentPrice = se.price;
    }
    public void priceChanged(PriceChangedEvent evt) {
        if(evt.priceWentUp) currentPrice++;     // update price
        else currentPrice--;
        steps++;
        
        for(int i=0; i<currentPrice; i++) System.out.print(" ");
        System.out.println("*");                // Plot current stock price
        
        if(evt.se.watchers.size() == 1) {       // if only the printer remains
            evt.se.removeWatcher = true;        // end the stock exchange
            System.out.println("Printer \"" + custName +
                    "\" has stopped plotting prices.");
        }
    }
}





