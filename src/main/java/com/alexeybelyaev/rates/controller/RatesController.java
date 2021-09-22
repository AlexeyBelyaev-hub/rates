package com.alexeybelyaev.rates.controller;

import com.alexeybelyaev.rates.exception.CodeNotCorrectException;
import com.alexeybelyaev.rates.service.RatesService;
import com.alexeybelyaev.rates.service.dto.Curs;
import com.alexeybelyaev.rates.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Validated
public class RatesController {

    @Value("${datetime.pattern}")
    private String DATE_PATTERN;

    RatesService ratesService;

    @Autowired
    public RatesController(RatesService ratesService) {
        this.ratesService = ratesService;
    }


    //Как передавать массив кодов,
    //?values=firstValue,secondValue,thirdValue
    //?values=firstValue&values=secondValue&values=thirdValue

    //формат даты
    //"yyyy-MM-dd HH:mm:ss"

    @PostMapping("/getcurs")
    public CollectionModel<EntityModel<Curs>> getRate(@RequestParam("datetime") String dateTimeStr,
                                             @RequestParam(value = "vcode", required = false)  String[] vCodes ){

        LocalDateTime dateTime = AppUtil.checkIfDateTimeFormatedAndGet(dateTimeStr,DATE_PATTERN);
        String [] vCodesChecked =null;
        if (vCodes!=null&&vCodes.length!=0){
            vCodesChecked = AppUtil.checkIfCodesValidAndUpper(vCodes);
        }
        List<EntityModel<Curs>> curses = ratesService.getRates(dateTime, vCodesChecked).stream()
                .map(curs -> EntityModel.of(curs,
                        linkTo(methodOn(RatesController.class).getRate(dateTimeStr,new String[]{curs.getVchCode()})).withSelfRel(),
                        linkTo(methodOn(RatesController.class).getRate(dateTimeStr,new String[0])).withRel("curses")
                        )).collect(Collectors.toList());

        return CollectionModel.of(curses,
                linkTo(methodOn(RatesController.class).getRate(dateTimeStr,vCodes)).withSelfRel()
        );

    }


}
