package entity;

import java.util.Set;
import java.util.UUID;

public class Course {
    private final UUID id;
    private final String title;
    private final String platform;
    private final String description;
    private final Set<String> skillsGained;

    public Course(UUID id, String title, String platform, String description, Set<String> skillsGained) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.description = description;
        this.skillsGained = skillsGained;
    }
}