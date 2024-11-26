package entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class EventFactory {

    public Event createEvent(String name, LocalDateTime startTime, String description, Set<String> tags, String source) {
        final UUID newId = UUID.randomUUID();
        return new Event(newId, name, startTime, description, tags, source);
    }
}
