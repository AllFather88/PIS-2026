@Component
@RequiredArgsConstructor
public class RabbitMqEventBus implements EventBus {

    private final RabbitTemplate rabbitTemplate;

    // Имя exchange, куда отправляем события
    private static final String EXCHANGE = "domain.events";

    @Override
    public void publish(Object event) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", event);
    }

    @Override
    public void publishAll(List<Object> events) {
        for (Object event : events) {
            publish(event);
        }
    }
}
