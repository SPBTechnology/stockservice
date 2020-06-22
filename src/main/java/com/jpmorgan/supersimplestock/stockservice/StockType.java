package com.jpmorgan.supersimplestock.stockservice;

public enum StockType {
    COMMON ("Common"),
    PREFERRED ("Preferred");

    private final String type;
    StockType (String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
