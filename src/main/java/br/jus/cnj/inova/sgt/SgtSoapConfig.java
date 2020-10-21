package br.jus.cnj.inova.sgt;

import br.jus.tjpb.libs.sgtsoapcient.SgtSoapClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SgtSoapConfig {

    @Bean
    public SgtSoapClient getSgtSoapClient(@Value("${sgt-client.url}") String url,
                                          @Value("${sgt-client.enable-logging}") boolean enableLogging,
                                          @Value("${sgt-client.timeout}") int timeout) {
        return new SgtSoapClient(url, enableLogging, timeout);
    }
}
