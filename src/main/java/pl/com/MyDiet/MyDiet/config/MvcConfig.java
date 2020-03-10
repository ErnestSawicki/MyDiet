package pl.com.MyDiet.MyDiet.config;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpClientErrorException;
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
        mapping.setProperty(AccessDeniedException.class.getName(), "errors/403-code-forbidden");
        mapping.setProperty(HttpClientErrorException.NotFound.class.getName(), "errors/404-code-not-found");
        mapping.setProperty(InternalError.class.getName(), "errors/500-internal-server-error");


        resolver.setExceptionMappings(mapping);
        resolver.addStatusCode("errors/403-code-forbidden", 403);
        resolver.addStatusCode("errors/404-code-not-found", 404);
        resolver.addStatusCode("errors/500-internal-server-error", 500);
        resolver.setDefaultErrorView("errors/default-error");
        resolver.setExceptionAttribute("exc");
        resolver.setWarnLogCategory("MVC:Logger");
        return resolver;
    }


}
