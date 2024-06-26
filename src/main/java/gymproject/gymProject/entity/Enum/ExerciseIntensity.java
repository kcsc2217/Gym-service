package gymproject.gymProject.entity.Enum;

public enum ExerciseIntensity {
    FREQUENT("자주함"), // 운동을 자주 하고 적극적으로 참여
    OCCASIONAL("가끔함"), // 가끔 운동하지만 일정한 빈도는 아님
    RARE("거의 안 함"); // 거의 운동을 하지 않음

    private final String description;

    ExerciseIntensity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
