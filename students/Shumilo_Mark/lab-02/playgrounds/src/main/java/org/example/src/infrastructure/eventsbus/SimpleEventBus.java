package org.example.src.infrastructure.eventsbus;

import org.example.src.application.eventbus.EventBus;
import org.example.src.domain.model.events.BookingCreatedEvent;
import org.example.src.domain.model.events.CreateReviewEvent;
import org.example.src.infrastructure.adapter.out.entity.BookingReadEntity;
import org.example.src.infrastructure.adapter.out.entity.ReviewReadEntity;
import org.example.src.infrastructure.adapter.out.repository.BookingReadJpaRepository;
import org.example.src.infrastructure.adapter.out.repository.CourtReadJpaRepository;
import org.example.src.infrastructure.adapter.out.repository.ReviewReadJpaRepository;
import org.example.src.infrastructure.adapter.out.repository.ScheduleReadJpaRepository;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleEventBus implements EventBus {

    private final List<Object> handlers = new ArrayList<>();

    public void register(Object handler) {
        handlers.add(handler);
    }

    @Override
    public void publish(Object event) {
        for (Object handler : handlers) {
            invokeHandler(handler, event);
        }
    }

    @Override
    public void publishAll(List<Object> events) {
        for (Object event : events) publish(event);
    }

    private void invokeHandler(Object handler, Object event) {
        for (Method m : handler.getClass().getMethods()) {
            if (m.getName().equals("on") &&
                    m.getParameterCount() == 1 &&
                    m.getParameterTypes()[0].equals(event.getClass())) {
                try {
                    m.invoke(handler, event);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}

