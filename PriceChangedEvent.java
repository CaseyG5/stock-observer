package stockexchange;

public class PriceChangedEvent {
    boolean priceWentUp;
    StockExchange se;
    
    PriceChangedEvent(boolean up, StockExchange ref) {
        priceWentUp = up;
        se = ref;
    }
}
/*
                             *
                              *
                             *
                              *
                               *
                              *
                             *
                            *
                             *
                            *
                             *
                            *
                           *
                          *
Customer "To Five" has sold their shares.
                         *
5 subscriber(s) remaining
                        *
...
                                                 *
                                                  *
                                                   *
                                                  *
                                                 *
                                                *
                                                 *
                                                  *
                                                   *
                                                    *
                                                     *
                                                      *
Customer "To Twenty-five" has sold their shares.
                                                       *
1 subscriber(s) remaining
                                                      *
Printer "Gutenberg" has stopped plotting prices.
0 subscribers remaining

It took the following number of steps for each subscriber to remove himself.
5: 21
10: 28
15: 283
20: 294
25: 557
*/