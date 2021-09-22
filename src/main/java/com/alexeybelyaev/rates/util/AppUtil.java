package com.alexeybelyaev.rates.util;

import com.alexeybelyaev.rates.exception.CodeNotCorrectException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class AppUtil {
    public static String [] checkIfCodesValidAndUpper(String []vCodes) throws CodeNotCorrectException {
        String[] arr = new String[vCodes.length];
        return Arrays.stream(vCodes).map(code-> {
            if (code.length()!=3){
                throw new CodeNotCorrectException("Код валюты \""+code+"\" не соответствует ограничениям. Код должен состоять из 3 букв");
            }else{
                return code.toUpperCase(Locale.ROOT);
            }
        }).collect(Collectors.toList()).toArray(arr);
    }

    public static LocalDateTime checkIfDateTimeFormatedAndGet(String dateTimeStr, String datePattern) {

        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(datePattern));
    }
}
