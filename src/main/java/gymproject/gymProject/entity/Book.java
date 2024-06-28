package gymproject.gymProject.entity;

import gymproject.gymProject.entity.Enum.Booking_status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Booking_status booking_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="gym_id" )
    private Gym gym;

    public Book(Booking_status booking_status, Member member, Gym gym) {
        this.booking_status = booking_status;
        this.member = member;
        this.gym = gym;
    }
}
