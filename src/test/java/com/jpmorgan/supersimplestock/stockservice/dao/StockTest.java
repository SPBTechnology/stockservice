package com.jpmorgan.supersimplestock.stockservice.dao;

import com.jpmorgan.supersimplestock.stockservice.dao.Stock;
import org.junit.Assert;
import org.junit.Test;

public class StockTest {
    private Stock stock;

    @Test
    public void canCreateStock() {
        stock = new Stock();
        Assert.assertNotNull(stock);
    }

    @Test
    public void canCreateStockPopulated() {
        stock = new Stock("stockSymbol", "Common", Float.valueOf(1), null, Float.valueOf(100));
        Assert.assertNotNull(stock);
        Assert.assertEquals("stockSymbol", stock.getStockSymbol());
        Assert.assertEquals("Common", stock.getStockType());
        Assert.assertEquals(1, stock.getLastDividend(),0);
        Assert.assertNull(stock.getFixedDividend());
        Assert.assertEquals(100, stock.getParValue(), 0);
    }
}
