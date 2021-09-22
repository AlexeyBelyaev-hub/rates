package com.alexeybelyaev.rates.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Curs {
    private String code;
    private String vchCode;
    private String name;
    private BigDecimal curs;

}
