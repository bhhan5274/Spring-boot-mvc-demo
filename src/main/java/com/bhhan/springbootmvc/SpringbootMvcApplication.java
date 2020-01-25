package com.bhhan.springbootmvc;

import com.samskivert.mustache.Mustache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringbootMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMvcApplication.class, args);
    }

    @Bean
    public Mustache.Compiler mustacheCompiler(Mustache.TemplateLoader mustacheTemplateLoader,
                                              Environment environment){
        final MustacheEnvironmentCollector collector = new MustacheEnvironmentCollector();
        collector.setEnvironment(environment);

        final Mustache.Compiler compiler = Mustache.compiler().defaultValue("")
                .withLoader(mustacheTemplateLoader)
                .withCollector(collector);

        return compiler;
    }
}
