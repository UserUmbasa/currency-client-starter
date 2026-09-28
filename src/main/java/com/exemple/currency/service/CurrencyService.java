package com.exemple.currency.service;


import com.exemple.currency.client.FrankfurterClient;

import java.math.BigDecimal;


public class CurrencyService {

    private final FrankfurterClient client;

    public CurrencyService(FrankfurterClient client) {
        this.client = client;
    }

    public BigDecimal getRate(String base, String quote) {
        return client.getRate(base, quote).rate();
    }
}
