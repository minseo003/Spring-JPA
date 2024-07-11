package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")  //데이터베이스 칼럼이름
    private Long id;

    private String name;

    @Embedded  //값타입
    private Address address;

    @OneToMany(mappedBy = "member")  //Order 엔티티에있는 member객체로 매핑된다. 연관관계의 주인이 아님
    private List<Order> orders = new ArrayList<>();  //일대다 관계이기때문에 리스트 반환
}
