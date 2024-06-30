package gymproject.gymProject.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Likes {

    @Id
    @GeneratedValue
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public Likes(Member member, Review review) {
        this.member = member;
        this.review = review;
    }
}
