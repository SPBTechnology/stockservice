package com.jpmorgan.supersimplestock.stockservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {
    @Id
    @Column(name="stock_symbol")
    private String stockSymbol;
    @Column(name="stock_type")
    private String stockType;
    @Column(name="last_dividend")
    private Float lastDividend;
    @Column(name="fixed_dividend")
    private Float fixedDividend;
    @Column(name="par_value")
    private Float parValue;

    public Stock() {
    }

    public Stock(String stockSymbol, String stockType, Float lastDividend, Float fixedDividend, Float parValue) {
        this.stockSymbol = stockSymbol;
        this.stockType = stockType;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getStockType() {
        return stockType;
    }

    public Float getLastDividend() {
        return lastDividend;
    }

    public Float getFixedDividend() {
        return fixedDividend;
    }

    public Float getParValue() {
        return parValue.floatValue();
    }
}
