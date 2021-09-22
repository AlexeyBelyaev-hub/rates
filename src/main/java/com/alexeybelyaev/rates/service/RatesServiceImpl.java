package com.alexeybelyaev.rates.service;


//Разработать Web сервис на  SpringBoot с POST методом getCurs, который возвращает курс по отношению к рублю
//входными данными метода является массив курсов (например [USD] или [USD, EUR] и тд)
//и время с датой за которую необходимо получить значения курсов по запрашиваемым валютам.
//После получения запроса Web сервис должен сделать один или несколько запросов в cbr.ru для получения курсов по каждой валюте.

import com.alexeybelyaev.rates.cbr.RatesClient;
import com.alexeybelyaev.rates.cbr.dto.ValuteData;
import com.alexeybelyaev.rates.service.dto.Curs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RatesServiceImpl implements RatesService {

    RatesClient ratesClient;

    @Autowired
    RatesServiceImpl(RatesClient ratesClient) {
        this.ratesClient = ratesClient;
    }

    public List<Curs> getRates(LocalDateTime localDateTime, String[] codes) {

        ValuteData valuteData = null;
        try {
            valuteData = ratesClient.getCursOnDate(localDateTime);
        } catch (MalformedURLException | DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }

        List<String> codeList = (codes != null)?Arrays.stream(codes).collect(Collectors.toList()):Collections.EMPTY_LIST;
        Predicate<ValuteData.ValuteCursOnDate> predicate;

        predicate = (codes != null) ? v -> codeList.contains(v.getVchCode()) : v -> true;

        List<Curs> curses = valuteData.getValuteCursOnDate().stream()
                .filter(predicate)
                .map(v -> new Curs(v.getVcode(),v.getVchCode(), v.getName().trim(), v.getVcurs()))
                .collect(Collectors.toList());

        return curses;
    }


}
