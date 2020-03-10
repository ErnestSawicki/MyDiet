package pl.com.MyDiet.MyDiet.config.converters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.nio.file.AccessDeniedException;
import java.util.Properties;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean(name = {"simpleMappingExceptionResolver"})
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties mapping = new Properties();
        mapping.setProperty(AccessDeniedException.class.getName(), "errorsMapping/403-code-forbidden");
        mapping.setProperty(AccessDeniedException.class.getName(), "errorsMapping/404-code-not-found");
        mapping.setProperty(AccessDeniedException.class.getName(), "errorsMapping/500-internal-server-error");


        resolver.setExceptionMappings(mapping);
        resolver.addStatusCode("errorsMapping/403-code-forbidden", 403);
        resolver.addStatusCode("errorsMapping/404-code-not-found", 404);
        resolver.addStatusCode("errorsMapping/500-internal-server-error", 500);
        resolver.setDefaultErrorView("errorsMapping/default-error");
        resolver.setExceptionAttribute("exc");
        resolver.setWarnLogCategory("MVC:Logger");
        return resolver;
    }


}
