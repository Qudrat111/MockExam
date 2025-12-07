package org.example.mockexam.configs.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("uz"));
        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource errorMessageSource() {
        ResourceBundleMessageSource errorMessageSource = new ResourceBundleMessageSource();
        errorMessageSource.setBasename("error");
        errorMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        errorMessageSource.setDefaultLocale(new Locale("uz"));
        return errorMessageSource;
    }
}
