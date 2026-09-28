package com.exemple.currency;

import com.exemple.currency.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class CurrencyClientStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyClientStarterApplication.class, args);
    }

    @Bean
    CommandLineRunner test(CurrencyService currencyService) {
        return args -> {
            BigDecimal rate = currencyService.getRate("EUR", "USD");
            //BigDecimal rate = currencyService.getRate("EUR", "artem");
            System.out.println(rate);
        };
    }

//    @Bean
//    CommandLineRunner test(CurrencyService currencyService) {
//        return args -> {
//            try {
//                BigDecimal rate = currencyService.getRate("EUR", "artem");
//                System.out.println(rate);
//            } catch (CurrencyClientException e) {
//                System.out.println("Ошибка получения курса: " + e.getMessage());
//            }
//        };
//    }

}
