package com.exemple.currency.client;

import com.exemple.currency.config.CurrencyProperties;
import com.exemple.currency.exception.CurrencyClientException;
import com.exemple.currency.model.ExchangeRateResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;


public class FrankfurterClient {

    private final RestClient restClient;


    public FrankfurterClient(CurrencyProperties properties) {

        SimpleClientHttpRequestFactory requestFactory =
                new SimpleClientHttpRequestFactory();

        requestFactory.setConnectTimeout(properties.getConnectTimeout());
        requestFactory.setReadTimeout(properties.getReadTimeout());

        this.restClient = RestClient.builder()
                .baseUrl(properties.getBaseUrl())
                .requestFactory(requestFactory)
                .build();
    }

    public ExchangeRateResponse getRate(String base, String quote) {
        try {
            return restClient.get()
                    .uri("/rate/{base}/{quote}", base, quote)
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            (request, response) -> {
                                throw new CurrencyClientException(
                                        "Failed to get exchange rate: "
                                                + base + "/" + quote
                                );
                            }
                    )
                    .body(ExchangeRateResponse.class);
        } catch (CurrencyClientException e) {
            throw e;
        } catch (Exception e) {
            throw new CurrencyClientException(
                    "Failed to get exchange rate: " + base + "/" + quote,
                    e
            );
        }
    }
}
