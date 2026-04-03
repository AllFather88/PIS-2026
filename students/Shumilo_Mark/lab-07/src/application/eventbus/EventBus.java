package org.example.src.application.eventbus;

import java.util.List;

public interface EventBus {
    void publish(Object event);
    void publishAll(List<Object> events);
}
