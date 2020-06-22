package com.jpmorgan.supersimplestock.stockservice.dao;

import com.jpmorgan.supersimplestock.stockservice.dao.Trade;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TradeTest {
    private Trade trade;

    @Test
    public void canCreateTrade() throws Exception {
        trade = new Trade();
        Assert.assertNotNull(trade);
    }

    @Test
    public void canCreateTradePopulated_NoTimestamp() throws Exception {
        trade = new Trade("TEA", LocalDateTime.now(), 1200L, "B", 212);
        Assert.assertNotNull(trade);
        Assert.assertNull(trade.getId());
        Assert.assertEquals("TEA", trade.getStockSymbol());
        Assert.assertEquals(1200L, trade.getQuantity(), 0);
        Assert.assertEquals("B", trade.getBuyOrSell());
        Assert.assertEquals(212, trade.getPrice(), 0);
    }
}