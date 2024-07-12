package gymproject.gymProject.entity.Enum;

public enum ExerciseExperience {

    BEGINNER("초보자"), // 운동을 처음 시작하거나 초보자
    INTERMEDIATE("중급자"), // 어느 정도 운동 경험이 있는 중급자
    ADVANCED("고급자"), // 오랜 기간 운동을 해 온 고급자
    EXPERT("전문가"); // 전문가 수준의 경험자

    private final String description;

    ExerciseExperience(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
