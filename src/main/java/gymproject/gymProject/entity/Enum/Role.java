package gymproject.gymProject.entity.Enum;

public enum Role {
    user("회원"),
    admin("관리자");


    private final String description;

    Role(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
