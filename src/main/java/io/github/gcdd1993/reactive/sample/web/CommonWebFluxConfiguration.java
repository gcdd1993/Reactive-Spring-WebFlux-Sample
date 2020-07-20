package io.github.gcdd1993.reactive.sample.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class CommonWebFluxConfiguration {

    @Bean
    public ResponseMessageWrapper responseMessageWrapper(ServerCodecConfigurer codecConfigurer,
                                                         RequestedContentTypeResolver resolver,
                                                         ReactiveAdapterRegistry registry) {
        return new ResponseMessageWrapper(codecConfigurer.getWriters(), resolver, registry);
    }
}
