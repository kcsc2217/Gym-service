package gymproject.gymProject.entity.Enum;

import lombok.Getter;

@Getter
public enum Booking_status {
    PENDING("대기중"),
    APPROVED("승인됨"),
    REJECTED("거부됨"),
    CANCELLED("취소됨"),
    COMPLETED("완료됨");

    private String description;

    Booking_status(String description) {
        this.description = description;
    }




}
