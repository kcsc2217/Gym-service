package gymproject.gymProject.entity.Enum;

public enum Gender {
    Male("남성"),
    FeMale("여성");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
