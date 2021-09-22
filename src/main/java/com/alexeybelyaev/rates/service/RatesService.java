package com.alexeybelyaev.rates.service;

import com.alexeybelyaev.rates.service.dto.Curs;

import java.time.LocalDateTime;
import java.util.List;

public interface RatesService {
    List<Curs> getRates(LocalDateTime localDateTime, String [] codes);
}
