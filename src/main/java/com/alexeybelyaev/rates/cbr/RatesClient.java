package com.alexeybelyaev.rates.cbr;

import com.alexeybelyaev.rates.cbr.dto.ValuteData;

import javax.xml.datatype.DatatypeConfigurationException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;

public interface RatesClient {
    ValuteData getCursOnDate(LocalDateTime onDateTime) throws MalformedURLException, DatatypeConfigurationException;
}
