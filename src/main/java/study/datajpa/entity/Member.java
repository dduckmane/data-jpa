package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
//team은 toString 하면 그 안으로 계속 가다가 무한루프에 빠질 수 있다.
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username=username;
        this.age=age;
        if(team!=null){
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username=username;
        this.age=age;
    }

    public void changeTeam(Team team){
        this.team=team;
        team.getMembers().add(this);
    }
}