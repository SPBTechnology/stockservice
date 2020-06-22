package com.jpmorgan.supersimplestock.stockservice.dao;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name="stock_symbol", nullable = false)
    private String stockSymbol;
    @Column(name="timestamp", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;
    @Column(name="quantity", nullable = false)
    private Long quantity;
    @Column(name="buy_or_sell", nullable = false)
    private String buyOrSell;
    @Column(name="price", nullable = false)
    private float price;

    public Trade() {
    }


    public Trade(String stockSymbol, Long quantity, String buyOrSell, float price) {
        this.stockSymbol = stockSymbol;
        this.timestamp = LocalDateTime.now();
        this.quantity = quantity;
        this.buyOrSell = buyOrSell;
        this.price = price;
    }

    public Trade(String stockSymbol, LocalDateTime timestamp, Long quantity, String buyOrSell, float price) {
        this.stockSymbol = stockSymbol;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.buyOrSell = buyOrSell;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public float getPrice() {
        return price;
    }
}
