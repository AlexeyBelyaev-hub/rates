package com.alexeybelyaev.rates.cbr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class RatesConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.alexeybelyaev.rates.cbr.wsdl");
        return marshaller;
    }

    @Bean
    public RatesClientGatewaySupport ratesClientGatewaySupport(Jaxb2Marshaller marshaller) {
        RatesClientGatewaySupport client = new RatesClientGatewaySupport();
        client.setDefaultUri("http://web.cbr.ru/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
