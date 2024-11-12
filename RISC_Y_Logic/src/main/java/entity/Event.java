
package entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class Event {
    private final UUID id;
    private final String title;
    private final String description;
    private final LocalDateTime startTime;
    private final Set<String> tags;
    private final String source;

    public Event(UUID id, String title, String description, LocalDateTime startTime, Set<String> tags, String source) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.tags = tags;
        this.source = source;
    }
}