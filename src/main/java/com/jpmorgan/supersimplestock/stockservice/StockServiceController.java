package com.jpmorgan.supersimplestock.stockservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class StockServiceController {
    @Autowired
    private Environment environment;

    @Autowired
    private StockService stockService;

    @Autowired
    private TradeService tradeService;

    /**
     * Calculate dividend yield for a given stock and price
     * @param stock
     * @param price
     * @return
     * @throws Exception
     */
    @GetMapping("/calculateDividendYield/stock/{stock}/price/{price}")
    public float calculateDividendYield (@PathVariable String stock, @PathVariable Long price) throws Exception {
        return stockService.calculateDividendYield(stock, price);
    }

    /**
     * CalculatePERatio for a given stock and price
     * @param stock
     * @param price
     * @return
     * @throws Exception
     */
    @GetMapping("/calculatePERatio/stock/{stock}/price/{price}")
    public float calculatePERatio (@PathVariable String stock, @PathVariable Long price) throws Exception {
        return stockService.calculatePERatio(stock, price);
    }

    /**
     * Record a new trade
     * @param stock
     * @param quantity
     * @param buyOrSell
     * @param price
     * @return
     * @throws Exception
     */
    @PostMapping("/recordTrade/stock/{stock}/quantity/{quantity}/buyOrSell/{buyOrSell}/price/{price}")
    public float recordTrade (@PathVariable String stock, @PathVariable Long quantity, @PathVariable String buyOrSell, @PathVariable Long price) throws Exception {
        return tradeService.recordTrade(stock, LocalDateTime.now(), quantity, buyOrSell, price);
    }

    /**
     * Calculate volume weighted stock price for a given stock
     * @param stock
     * @return
     * @throws Exception
     */
    @GetMapping("/calculateVolumeWeightedStockPrice/stock/{stock}")
    public float calculateVolumeWeightedStockPrice(@PathVariable String stock) throws Exception {
        return tradeService.calculateVolumeWeightedStockPrice(stock);
    }

    /**
     * Calculate GBCE all share index across share portfolio
     * @return
     * @throws Exception
     */
    @GetMapping("/calculateGBCEAllShareIndex")
    public float calculateGBCEAllShareIndex() throws Exception {
        return tradeService.calculateGeometricMean();
    }
}
