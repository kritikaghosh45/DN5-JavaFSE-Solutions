package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @SuppressWarnings("unchecked")
    public Country getCountry(String code) {
        LOGGER.info("START getCountry()");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList", List.class);

        Country result = null;
        for (Country country : countryList) {
            if (country.getCode().equalsIgnoreCase(code)) {
                result = country;
                break;
            }
        }

        LOGGER.debug("getCountry() result={}", result);
        LOGGER.info("END getCountry()");
        return result;
    }
}