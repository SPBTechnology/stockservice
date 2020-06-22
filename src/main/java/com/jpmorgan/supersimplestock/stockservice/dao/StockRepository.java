package com.jpmorgan.supersimplestock.stockservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StockRepository extends JpaRepository<Stock, String> {
}
