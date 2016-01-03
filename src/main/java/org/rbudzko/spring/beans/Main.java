package org.rbudzko.spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.UUID;

/**
 * Example application to present how implement glue between properties and spring context, so dynamic beans are created.
 */
@SpringBootApplication
public class Main {
    /**
     * Standard Spring Boot run - nothing special.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * Bean of post processor which will generate dynamic bean definitions.
     */
    @Bean
    public BeanDefinitionRegistryPostProcessor factory(final Environment environment) {
        return new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry registry) throws BeansException {
                int index = 0;
                while (environment.containsProperty(playerProperty(index))) {
                    registry.registerBeanDefinition(
                            "dynamic-player-" + UUID.randomUUID(),
                            BeanDefinitionBuilder
                                    /**
                                     * Note that it is possible to use constructors as well as methods.
                                     * Rules of Spring applies normally.
                                     */
                                    .rootBeanDefinition(DefaultPlayer.class, "forName")
                                    /**
                                     * Note that environment has been wired by use of signature and @Bean, so we can access
                                     * other elements which reside in context. Processor os one of first beans created, so
                                     * normal beans are not available yet!
                                     */
                                    .addConstructorArgValue(environment.getProperty(playerProperty(index++)))
                                    /**
                                     * Build definition and pass it to registry. It will be initialized, wired into other
                                     * beans, but using nasty proxy utils might be needed to imitate @Scope proxyMode behavior.
                                     */
                                    .getBeanDefinition());
                }
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                // Nothing.
            }

            private String playerProperty(final int index) {
                return "players[" + index + "]";
            }
        };
    }
}
