package com.example.JPA.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// ToString 필드에는 team과 같은 연관관계 필드는 들어가지 않는 것이 좋음
@ToString(of = {"id", "username", "age"})
/**
 * 실무에서 @NamedQuery 은 잘 사용하지 않음, 주로 SPRING JPA가 repository 에 query 를 바로 정의
 * NamedQuery 의 장점: 애플리케이션 로딩 시점에 파싱으로 오타를 잡아줌
 **/


@NamedQuery(
        name="Member.findByUsername",
        query="select m from Member m where m.username = :username"
)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    /**
     * 파라미터가 필요없는 default 생성자가 필요
     * access level 은 protected 까지만 허용
     * @NoArgsConstructor(access = AccessLevel.PROTECTED)
    **/

//    protected Member() {x
//    }

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }
    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    // 연관관계 설정
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
