package com.example.outbox;

import com.example.outbox.handler.OrderHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public class Router {
        @Bean
        public RouterFunction<ServerResponse> stockRouters(OrderHandler handler) {
            return RouterFunctions.route(POST("/orders").and(accept(APPLICATION_JSON)), handler::createOrder)
                    .andRoute(PATCH("/orders/{orderNo}/{status}").and(accept(APPLICATION_JSON)), handler::updateOrder)
                    .andRoute(GET("/orders/{orderNo}").and(accept(APPLICATION_JSON)), handler::getOrder);
        }
    }
}
