package com.alexeybelyaev.rates.cbr;

import com.alexeybelyaev.rates.cbr.dto.ValuteData;
import com.alexeybelyaev.rates.cbr.wsdl.GetCursOnDateXML;
import com.alexeybelyaev.rates.cbr.wsdl.GetCursOnDateXMLResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
public class RatesClientGatewaySupport extends WebServiceGatewaySupport implements RatesClient {

    public ValuteData getCursOnDate(LocalDateTime onDateTime) throws DatatypeConfigurationException {

        GetCursOnDateXML getCursOnDate = new GetCursOnDateXML();
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(onDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        getCursOnDate.setOnDate(xmlGregorianCalendar);
        GetCursOnDateXMLResponse response = (GetCursOnDateXMLResponse)getWebServiceTemplate()
                .marshalSendAndReceive("https://cbr.ru/DailyInfoWebServ/DailyInfo.asmx",
                getCursOnDate
                        ,new SoapActionCallback("http://web.cbr.ru/GetCursOnDateXML")
                );

        GetCursOnDateXMLResponse.GetCursOnDateXMLResult result = response.getGetCursOnDateXMLResult();
        
        return (ValuteData)result.getContent().get(0);
    }

}
