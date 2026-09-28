package com.exemple.currency.autoconfigure;

import com.exemple.currency.client.FrankfurterClient;
import com.exemple.currency.config.CurrencyProperties;
import com.exemple.currency.service.CurrencyService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(CurrencyProperties.class)
@ConditionalOnProperty(
        prefix = "currency.client",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class CurrencyAutoConfiguration {

    @Bean
    public FrankfurterClient frankfurterClient(
            CurrencyProperties properties) {

        return new FrankfurterClient(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public CurrencyService currencyService(
            FrankfurterClient frankfurterClient) {
        return new CurrencyService(frankfurterClient);
    }
}
