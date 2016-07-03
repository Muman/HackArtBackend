package com.hack.art.configuration;

import com.hack.art.handler.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Roman on 25.08.2015.
 */
@Configuration
@EnableWebMvc
@PropertySource({"classpath:application.properties", "classpath:log4j.properties"})
@ComponentScan(basePackages = "com.hack.art.")
@EnableTransactionManagement
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("startup");
        registry.addResourceHandler("/resources*//**").addResourceLocations("/resources/");
        registry.addResourceHandler("/app/img/**").addResourceLocations("/app/img/");//.setCachePeriod(31556926);
        registry.addResourceHandler("/app/libs/**").addResourceLocations("/app/libs/");
        registry.addResourceHandler("/app/css/**").addResourceLocations("/app/css/");
        registry.addResourceHandler("/app/fonts/**").addResourceLocations("/app/fonts/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html");//.setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer appProperties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        String[] basenames = {
                "stApp"
        };

        messageSource.setBasenames(basenames);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Bean
    public static CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(100000);
        return commonsMultipartResolver;
    }

    @Bean
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(getJsonMessageConverter());
        adapter.setMessageConverters(converters);
        return adapter;
    }

    /**
     * getJsonMessageConverter
     *
     * @return
     */
    @Bean
    public HttpMessageConverter<Object> getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return converter;
    }

  /*  @Bean
    public void initLog4j() throws FileNotFoundException {
        Log4jConfigurer.initLogging("classpath:log4j.properties");
    }*/

    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }

}
