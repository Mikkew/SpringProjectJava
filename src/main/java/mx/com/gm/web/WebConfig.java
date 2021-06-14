package mx.com.gm.web;

import java.util.Locale;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean(name="localeResolver")
    public LocaleResolver localResolver() {        
        final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("es", "MX"));
        return localeResolver;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor( localeChangeInterceptor() );
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errors/403").setViewName("/errors/403");
        
    }
}
