package com.will.util;

import com.will.models.Stock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class RestHelper {
    RestTemplate restTemplate = new RestTemplate();

    public Stock getStock(){
        String fakeApi = "https://mocki.io/v1/a8434bde-4ce3-4359-94a6-151b281542d7";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(fakeApi, Stock.class);
    }

    @Async
    public Future<Stock> getStockAsync() throws InterruptedException {
        String fakeApi = "https://mocki.io/v1/cc5a03f3-72d5-4acc-8854-5ecd6df714ad";
        Stock stock = restTemplate.getForObject(fakeApi, Stock.class);
        return new AsyncResult<Stock>(stock);
    }
}
