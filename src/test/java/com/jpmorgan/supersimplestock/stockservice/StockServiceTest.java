package com.jpmorgan.supersimplestock.stockservice;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StockServiceTest {
    @Autowired
    private StockService stockService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void calculateDividendYield_findValidStock_StockNotFound() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("is not maintained");
        stockService.calculateDividendYield("NO_STOCK", 1);
    }

    @Test
    public void calculateDividendYield_findValidStock_UnknownStockType() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("has unknown stock type");
        stockService.calculateDividendYield("LPA", 1);
    }

    @Test
    public void calculateDividendYieldCommon_checkCalculation_Exception() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Unable to calculate");
        stockService.calculateDividendYield("TEA", 0);
    }

    @Test
    public void calculateDividendYieldPreferred_checkCalculation_Exception() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Unable to calculate");
        stockService.calculateDividendYield("GIN", 0);
    }

    @Test
    public void calculatePERatio_checkCalculation_Exception() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Unable to calculate");
        stockService.calculatePERatio("TEA", 2);
    }

    @Test
    public void calculateDividendYieldCommonTest() throws Exception {
        assertEquals(0.0, stockService.calculateDividendYield("TEA", 1), 0);
    }

    @Test
    public void calculateDividendYieldPreferredTest() throws Exception {
        assertEquals(200.0, stockService.calculateDividendYield("GIN", 1), 0);
    }

    @Test
    public void calculatePERatioTest() throws Exception {
        assertEquals(0.5, stockService.calculatePERatio("POP", 4), 0);
    }
}
