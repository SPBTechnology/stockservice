insert into stock(stock_symbol, stock_type, last_dividend, fixed_dividend, par_value)
values ('TEA', 'COMMON', 0, null, 100);
insert into stock(stock_symbol, stock_type, last_dividend, fixed_dividend, par_value)
values ('POP', 'COMMON', 8, null, 100);
insert into stock(stock_symbol, stock_type, last_dividend, fixed_dividend, par_value)
values ('ALE', 'COMMON', 23, null, 60);
insert into stock(stock_symbol, stock_type, last_dividend, fixed_dividend, par_value)
values ('GIN', 'PREFERRED', 8, 2, 100);
insert into stock(stock_symbol, stock_type, last_dividend, fixed_dividend, par_value)
values ('JOE', 'COMMON', 13, null, 250);
insert into stock(stock_symbol, stock_type, last_dividend, fixed_dividend, par_value)
values ('LPA', 'JUNK', 13, null, 250);
insert into trade(id, stock_symbol, quantity, buy_or_sell, price)
values (10000, 'TEA', 1000, 'B', 200);
insert into trade(id, stock_symbol, quantity, buy_or_sell, price)
values (10001, 'POP', 4000, 'B', 900);
insert into trade(id, stock_symbol, quantity, buy_or_sell, price)
values (10002, 'TEA', 25000, 'S', 140);