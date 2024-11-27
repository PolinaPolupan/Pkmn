package ru.mirea.pkmn.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.pkmn.PkmnApplication;
import ru.mirea.pkmn.utils.ResourceFileLoader;

import java.util.logging.Logger;

@Configuration
public class AppConfig {

    @Bean
    public Logger logger() {

        return Logger.getLogger(PkmnApplication.class.getName());
    }

    @Bean
    public ResourceFileLoader loader(Logger logger) {

        return new ResourceFileLoader(logger);
    }
}
