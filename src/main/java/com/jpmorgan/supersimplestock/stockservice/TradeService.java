package com.jpmorgan.supersimplestock.stockservice;

import com.jpmorgan.supersimplestock.stockservice.dao.Stock;
import com.jpmorgan.supersimplestock.stockservice.dao.StockRepository;
import com.jpmorgan.supersimplestock.stockservice.dao.Trade;
import com.jpmorgan.supersimplestock.stockservice.dao.TradeRepository;
import com.jpmorgan.supersimplestock.stockservice.utils.CalcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TradeService {
    /**
     * @Author Stephen Burns
     * Business application layer to perform the specified trade calculations
     */
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private StockRepository stockRepository;

    public Long recordTrade(String stockSymbol, LocalDateTime timestamp, long quantity, String buyOrSell, float price) {
        Trade trade = new Trade(stockSymbol, timestamp, quantity, buyOrSell, price);
        Trade savedTrade = tradeRepository.save(trade);
        return savedTrade.getId();
    }

    public Trade findTrade(Long tradeId) {
        Optional<Trade> trade = tradeRepository.findById(tradeId);
        if (trade.isEmpty()) return null;
        return trade.get();
    }

    private List<Trade> findRecentTrades(String stockSymbol) {
        LocalDateTime timestamp = LocalDateTime.now();
        timestamp = timestamp.minusMinutes(5);
        List<Trade> foundTrades = tradeRepository.findByTimestampGreaterThan(timestamp, stockSymbol);
        return foundTrades;
    }

    public float calculateVolumeWeightedStockPrice(String stockSymbol) throws Exception {
        float tradedPriceByQuantity = 0;
        float quantitySum = 0;
        for (Trade trade: findRecentTrades(stockSymbol)) {
            tradedPriceByQuantity+=trade.getQuantity()*trade.getPrice();
            quantitySum+=trade.getQuantity();
        }
        try {

            CalcUtils.checkValidCalculation(quantitySum);
            return tradedPriceByQuantity/quantitySum;
        } catch (Exception divByZero) {
            // do not error on zero quantities for calculating the weighted stock price
            return 0.0F;
        }
    }

    public float calculateGeometricMean() throws Exception {
        float total = 0.0F;
        long count = 0L;
        List<Stock> allStocks = stockRepository.findAll();
        for (Stock stock: allStocks) {
            total += calculateVolumeWeightedStockPrice(stock.getStockSymbol());
            count += 1;
        }
        CalcUtils.checkValidCalculation(count);

        return total/count;
    }
}
