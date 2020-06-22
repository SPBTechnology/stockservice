package com.jpmorgan.supersimplestock.stockservice;

import org.h2.server.web.WebApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceControllerTest {

    private MockMvc stockServiceControllerMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        final StockServiceController controller = new StockServiceController();
        this.stockServiceControllerMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testCalculateDividendYield() throws Exception {
        stockServiceControllerMvc.perform(get("/calculateDividendYield/stock/{stock}/price/{price}", "POP", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json("8.0"));
    }

    @Test
    public void testCalculatePERatio() throws Exception {
        stockServiceControllerMvc.perform(get("/calculatePERatio/stock/{stock}/price/{price}", "POP", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json("0.125"));
    }

    @Test
    public void testRecordTrade() throws Exception {
        stockServiceControllerMvc.perform(post("/recordTrade/stock/{stock}/quantity/{quantity}/buyOrSell/{buyOrSell}/price/{price}", "POP", 20000, "S", 18))
                .andExpect(status().isOk());
    }
}
