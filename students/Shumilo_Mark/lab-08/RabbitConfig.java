@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange domainEventsExchange() {
        return new TopicExchange("domain.events");
    }
}
