package com.alexeybelyaev.rates.service;

import com.alexeybelyaev.rates.cbr.RatesClient;
import com.alexeybelyaev.rates.cbr.dto.ValuteData;
import com.alexeybelyaev.rates.service.dto.Curs;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RatesServiceImplTest {

    @Mock
    private RatesClient ratesClient;

    @InjectMocks
    private RatesService ratesService = new RatesServiceImpl(ratesClient);



    @SneakyThrows
    @Test
    void getRates() {
        //given
        String[] codes = new String[2];
        codes[0]="AUD";
        codes[1]="BYN";

        ValuteData data = getTestData();


        //when
        when(ratesClient.getCursOnDate(ArgumentMatchers.any(LocalDateTime.class))).thenReturn(data);
        List<Curs> rates = ratesService.getRates(LocalDateTime.now(), codes);
        //then
        verify(ratesClient,times(1)).getCursOnDate(any(LocalDateTime.class));
        Assertions.assertEquals(2,rates.size());

    }



    private ValuteData getTestData(){
        ValuteData valuteData = new ValuteData();
        List<ValuteData.ValuteCursOnDate> curses =valuteData.getValuteCursOnDate();

        ValuteData.ValuteCursOnDate val1 = new ValuteData.ValuteCursOnDate();
        val1.setVcode("36");
        val1.setName("Австралийский доллар                                                                                                               ");
        val1.setVcurs(new BigDecimal(53.0553));
        val1.setVchCode("AUD");
        curses.add(val1);

        ValuteData.ValuteCursOnDate val2 = new ValuteData.ValuteCursOnDate();
        val2.setVcode("944");
        val2.setName(">Азербайджанский манат                        ");
        val2.setVcurs(new BigDecimal(43.1616));
        val2.setVchCode("AZN");
        curses.add(val2);

        ValuteData.ValuteCursOnDate val3 = new ValuteData.ValuteCursOnDate();
        val3.setVcode("826");
        val3.setName("Фунт стерлингов Соединенного королевства             ");
        val3.setVcurs(new BigDecimal(100.2222));
        val3.setVchCode("GBP");
        curses.add(val3);

        ValuteData.ValuteCursOnDate val4 = new ValuteData.ValuteCursOnDate();
        val4.setVcode("933");
        val4.setName("Белорусский рубль                                                               ");
        val4.setVcurs(new BigDecimal(29.5382));
        val4.setVchCode("BYN");
        curses.add(val4);
        return valuteData;

    }
}