package com.jpmorgan.supersimplestock.stockservice;

import com.jpmorgan.supersimplestock.stockservice.dao.Stock;
import com.jpmorgan.supersimplestock.stockservice.dao.StockRepository;
import com.jpmorgan.supersimplestock.stockservice.utils.CalcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StockService {
    /**
     * @Author Stephen Burns
     * Business application layer to perform the specified stock calculations
     */
    @Autowired
    private StockRepository stockRepository;

    private Stock findValidStock(String stockSymbol) throws Exception {
        Optional<Stock> findStock = stockRepository.findById(stockSymbol);
        if (findStock.isEmpty()) throw new Exception("Stock " + stockSymbol + " is not maintained");
        Stock stock = findStock.get();

        // overly defensive providing all data persistence is done through the service
        try {
            StockType.valueOf(stock.getStockType());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new Exception("Stock " + stockSymbol + " has unknown stock type");
        }
        return stock;
    }

    public float calculateDividendYield(String stockSymbol, long price) throws Exception {
        Stock stock = findValidStock(stockSymbol);
        CalcUtils.checkValidCalculation(price);
        switch (StockType.valueOf(stock.getStockType())) {
            case COMMON:
                return stock.getLastDividend() / price;
            case PREFERRED:
                return stock.getFixedDividend() * stock.getParValue() / price;
            default:
                // code smell, mutation testing would likely argue this can never be reached
                throw new Exception("Stock service failure");
        }
    }

    public float calculatePERatio(String stockSymbol, long price) throws Exception {
        Stock stock = findValidStock(stockSymbol);
        CalcUtils.checkValidCalculation(stock.getLastDividend());
        return price/stock.getLastDividend();
    }
}
