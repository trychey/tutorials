package com.baeldung.subflows.publishsubscribechannel;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;

import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

@EnableIntegration
@IntegrationComponentScan
public class PublishSubscibeChannelExample {
    @MessagingGateway
    public interface NumbersClassifier {
        @Gateway(requestChannel = "flow.input")
        void flow(Collection<Integer> numbers);
    }

    @Bean
    QueueChannel multipleof3Channel() {
        return new QueueChannel();
    }

    @Bean
    QueueChannel remainderIs1Channel() {
        return new QueueChannel();
    }

    @Bean
    QueueChannel remainderIs2Channel() {
        return new QueueChannel();
    }

    @Bean
    public IntegrationFlow flow() {
        return flow -> flow.split()
            .publishSubscribeChannel(s -> s.subscribe(f -> f.<Integer> filter(p -> p % 3 == 0)
                .channel("multipleof3Channel"))
                .subscribe(f -> f.<Integer> filter(p -> p % 3 == 1)
                    .channel("remainderIs1Channel"))
                .subscribe(f -> f.<Integer> filter(p -> p % 3 == 2)
                    .channel("remainderIs2Channel")));
    }

}
