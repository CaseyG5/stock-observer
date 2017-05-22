package stockexchange;

import java.util.ArrayList;

interface StockObserver {
    void priceChanged(PriceChangedEvent evt);
}

public class StockExchange implements StockObserver {

    final String stockName;
    int price;
    public ArrayList<StockObserver> watchers;
    boolean removeWatcher;                      // flag for removal of
                                                // observers
    StockExchange(String name, int p) {
        stockName = name;
        price = p;
        watchers = new ArrayList<StockObserver>();
        removeWatcher = false;                      
    }                                               
    
    public void AddObserver(StockObserver obs) {
        watchers.add(obs);
    }
    
    public void RemoveObserver(int i) {
        watchers.remove(i);                                               
    }
    
    public void priceChanged(PriceChangedEvent evt) {
        for( int i=0; i<watchers.size(); i++)       // communicate price update
            watchers.get(i).priceChanged(evt);      // to each observer
    }
    
    public void beginTrading() {
        PriceChangedEvent latest;
        
        while(watchers.size() > 0) {
            if(Math.random() < 0.5) {           // 50/50 chance of price up/down
                price--;
                latest = new PriceChangedEvent(false, this);
            }
            else {
                price++;
                latest = new PriceChangedEvent(true, this);
            }
            priceChanged(latest);               // communicate price change
            
            if(removeWatcher) {
                RemoveObserver(0);              
                System.out.println(watchers.size() + " subscriber(s) remaining");
                removeWatcher = false;
            }
            try { Thread.sleep(100); }
            catch(InterruptedException exc) { }
        }
    }
    
    public static void main(String[] args) {
        StockExchange mySE = new StockExchange("BCC", 30);
        
        StockMonitor five = new StockMonitor(mySE, "To Five", 5);
        StockMonitor ten = new StockMonitor(mySE, "To Ten", 10);
        StockMonitor fifteen = new StockMonitor(mySE, "To Fifteen", 15);
        StockMonitor twenty = new StockMonitor(mySE, "To Twenty", 20);
        StockMonitor twentyFive = new StockMonitor(mySE, "To Twenty-five", 25);
        
        StockPricePrinter Gutenberg = new StockPricePrinter(mySE, "Gutenberg");
        
        mySE.beginTrading();
        
        System.out.println("\nIt took the following number of steps for "
                + "each subscriber to remove himself.");
        System.out.println("5: " + five.steps + "\n10: " + ten.steps + 
                "\n15: " + fifteen.steps + "\n20: " + twenty.steps +
                "\n25: " + twentyFive.steps);
    }
}