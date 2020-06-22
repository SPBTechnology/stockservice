package com.jpmorgan.supersimplestock.stockservice;

import com.jpmorgan.supersimplestock.stockservice.dao.Stock;
import com.jpmorgan.supersimplestock.stockservice.dao.StockRepository;
import com.jpmorgan.supersimplestock.stockservice.dao.TradeRepository;
import net.bytebuddy.pool.TypePool;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceExceptionsTest {

    @Mock
    StockRepository stockRepositoryMock;

    @InjectMocks
    TradeService tradeService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void runTest() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Unable to calculate");
        List<Stock> emptyList = Collections.emptyList();
        when(stockRepositoryMock.findAll()).thenReturn(emptyList);
        tradeService.calculateGeometricMean();
    }
}
