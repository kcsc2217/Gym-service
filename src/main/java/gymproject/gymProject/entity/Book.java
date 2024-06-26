package gymproject.gymProject.entity;

import gymproject.gymProject.entity.Enum.Booking_status;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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


}
