package com.jpmorgan.supersimplestock.stockservice;

import com.jpmorgan.supersimplestock.stockservice.dao.Trade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TradeServiceTest {
    @Autowired
    private TradeService tradeService;

    @Test
    public void recordTradeTest() throws Exception {
        LocalDateTime timestamp = LocalDateTime.now();
        Long tradeId = tradeService.recordTrade("TEA", timestamp, 1000, "B", 120);
        assertNotNull(tradeId);

        // overkill - but belts and braces to confirm the trade persisted
        Trade tradeRead = tradeService.findTrade(tradeId);
        assertEquals("TEA", tradeRead.getStockSymbol());
        assertEquals(timestamp, tradeRead.getTimestamp());
        assertEquals(1000L, tradeRead.getQuantity(), 0);
        assertEquals("B", tradeRead.getBuyOrSell());
        assertEquals(120, tradeRead.getPrice(), 0);
    }

    @Test
    public void calculateVolumeWeightedStockPriceTest() throws Exception {
        assertEquals(142, tradeService.calculateVolumeWeightedStockPrice("TEA"), 1);
    }

    @Test
    public void calculateVolumeWeightedStockPriceCalcTest() throws Exception {
        assertEquals(142, tradeService.calculateVolumeWeightedStockPrice("TEA"), 1);
    }

    @Test
    public void calculateGeometricMeanTest() throws Exception {
        assertEquals(173, tradeService.calculateGeometricMean(), 1);
    }
}

