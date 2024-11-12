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

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getSkillsGained() {
        return skillsGained;
    }
}