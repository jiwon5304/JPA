package com.example.JPA.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// ToString 필드에는 team과 같은 연관관계 필드는 들어가지 않는 것이 좋음
@ToString(of = {"id", "username", "age"})
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
