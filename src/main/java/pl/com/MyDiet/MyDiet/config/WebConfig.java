package pl.com.MyDiet.MyDiet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.com.MyDiet.MyDiet.config.converters.LocalDataConverter;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Bean
    public LocalDataConverter localDataConverter() {
        return new LocalDataConverter();
    }


    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(localDataConverter());
    }
}
