package com.alexeybelyaev.rates.controller;


import com.alexeybelyaev.rates.service.dto.Curs;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<Curs> curses;
}
