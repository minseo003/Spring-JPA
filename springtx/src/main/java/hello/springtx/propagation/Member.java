package hello.springtx.propagation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;  //데이터베이스에서 관리

    private String username;

    public Member() {  //JPA 기본생성자 필수
    }

    public Member(String username) {
        this.username = username;
    }
}
