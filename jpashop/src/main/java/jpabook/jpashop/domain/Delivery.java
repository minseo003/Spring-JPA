package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)  //양방향 연관관계
    private Order order;  //일대일 연관관계, 객체반환

    @Embedded  //값타입
    private Address address;

    @Enumerated(EnumType.STRING)  //이넘 타입, String으로 반환
    private DeliveryStatus status;
}
