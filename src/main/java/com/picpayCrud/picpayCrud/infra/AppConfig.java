package com.picpayCrud.picpayCrud.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration //marcar uma classe como uma classe de configuração. Isso significa que a classe contém métodos para configurar beans gerenciados pelo Spring
public class AppConfig {
    //configuração do restTemplate
    @Bean // indicar que um método é responsável por instanciar, configurar e inicializar um objeto
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
