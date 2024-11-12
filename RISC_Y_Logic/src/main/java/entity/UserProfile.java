package entity;

import java.util.List;
import java.util.UUID;

public class UserProfile {
    private final UUID id;
    private final String name;
    private final String email;
    private final String academicProgram;
    private final List<String> careerGoals;

    public UserProfile(UUID id, String name, String email, String academicProgram, List<String> careerGoals) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.academicProgram = academicProgram;
        this.careerGoals = careerGoals;
    }

}
