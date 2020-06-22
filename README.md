# JP Morgan Simple Stock Service

### 1. Assignment Description & Comments
Create a simple stocks application from the specification provided to:

    a.	For a given stock:
    
        i.    Given any price as input, calculate the dividend yield
        ii.   Given any price as input, calculate the P/E Ratio
        iii.  Record a trade, with timestamp, quantity, buy or sell indicator and price
        iv.   Calculate Volume Weighted Stock Price based on trades in past 5 minute
        
    b.	Calculate the GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price for all stocks

Note: For point b. above an assumption was made that the same period of 5 minutes should dictate the inclusion of the trade.

### 2. Architecture and design considerations
Architecture:
 - I chose to implement a simple RESTful java microservice based on Springboot framework.
 - Implementation was TDD specific.
 - Although the requirements stated that a persistence layer was not required I chose to implement an im-memory H2 datastore.  The data entities are therefore more in-line with the likely production solution and the in-memory db would likely be retained as the development equivalent.  Thus providing faster dev/test/prod cycles and ensuring consistency across all.

Design considerations:
 - Questioned whether to implement individual services for stock and trade?  Arguments as to whether they would be better suited as individual external microservices.  Justification for individual services would be driven by business requirements based on the criticality of each.  As this is not defined in the requirements the decision was taken to implement in a single microservice controller.
 - Security?  Again based on the lack of requirements there was no indication that security should be placed on the service endpoints.  Therefore, this has been left open.  If required the standard spring security could be easily implemented or okta Oauth2 with minimal upheaval.
 - Date and time operations? There is no specification regarding cross timezone trade operations.  This would require further analysis and design with respect to recording and evaluating trades.
 - Minimal testing of out-the-box JPA layer.
 - Swagger to define the external interfaces? Not included however would definately be utilised in a commercial environment.

### 3. Other considertaions:
If commercial grade would expect to potentially include some or all of the following: 
- Eureka service for service discovery.
- An API-gateway should the service be exposed externally.
- Hystrix for better exception handling / circuit breaking.

### 3. Application
#### Tests
All tests run at time of commit.
Differing test models implemented including pre-populating the H2 database and running service or controller tests through SpringRunner.  Also more localised testing through injecting mocks.
Run tests through IDE or via maven

#### Running
Simplest mechanism is to run within the IDE by running the StockserviceApplication.class.  Once running the following can be attempted:

NOTE that all parameters are request parameters, no reason other than simplicity for a test exercise.

##### Calculate Dividend Yield
http://localhost:8080/calculateDividendYield/stock/{stockSymbol}/price/{price}
e.g.
http://localhost:8080/calculateDividendYield/stock/POP/price/1

##### Calculate PE Ratio
http://localhost:8080//calculatePERatio/stock/{stockSymbol}/price/{price}
e.g.
http://localhost:8080/calculatePERatio/stock/POP/price/1

##### Record Trade
NOTE THIS IS A POST
http://localhost:8080/recordTrade/stock/{stock}/quantity/{quantity}/buyOrSell/{buyOrSell}/price/{price}
e.g.
http://localhost:8080/recordTrade/stock/POP/quantity/100/buyOrSell/B/price/300

##### Calculate Volume Weighted Stock Price
http://localhost:8080/calculateVolumeWeightedStockPrice/stock/{stock}
e.g.
http://localhost:8080/calculateVolumeWeightedStockPrice/stock/{POP}

##### Calculate GBCE
http://localhost:8080/calculateGBCEAllShareIndex
