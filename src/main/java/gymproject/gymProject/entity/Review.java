package gymproject.gymProject.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String content;

    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    public Review(String content, int rating, Member member, Gym gym) {
        this.content = content;
        this.rating = rating;
        setMember(member);
        setGym(gym);
    }

    public Review(String content, int rating, Member member) {
        this.content = content;
        this.rating = rating;
        this.member = member;
    }

    public void setMember(Member member){
        this.member = member;
        member.getRivewlist().add(this);
    }

    public void setGym(Gym gym){
        this.gym = gym;
        gym.getRivewlist().add(this);
    }






}
