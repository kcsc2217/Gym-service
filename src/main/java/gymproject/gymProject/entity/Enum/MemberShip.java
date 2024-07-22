package gymproject.gymProject.entity.Enum;

public enum MemberShip {
    ACTIVE("등록중"), EXPIRED("만료됨"), INACTIVE("비활성화");

    private final String description;

    MemberShip(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
