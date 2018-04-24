package com.valentyn.familytime.task.config;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HandleBarsConfig {
    /**
     * Loader for temlates.
     *
     * @return ClassPathTemplateLoader
     */
    @Bean
    ClassPathTemplateLoader templateLoader() {
        ClassPathTemplateLoader templateLoader = new ClassPathTemplateLoader();
        templateLoader.setPrefix("/notifications/templates");
        templateLoader.setSuffix(".hbs");

        return templateLoader;
    }

    /**
     * Manager of templates.
     *
     * @return Handlebars
     */
    @Bean
    Handlebars templateManager() {
        Handlebars templateManager = new Handlebars(templateLoader());
        return templateManager;
    }
}
