package com.jpmorgan.supersimplestock.stockservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface TradeRepository extends JpaRepository<Trade, Long> {
    @Query("select t from Trade t where t.timestamp >= ?1 and stockSymbol = ?2")
    List<Trade> findByTimestampGreaterThan(LocalDateTime fiveMinutesPrior, String stockSymbol);
}
