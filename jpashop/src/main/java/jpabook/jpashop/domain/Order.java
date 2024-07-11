package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.OrderedMapType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")  //order by 예약어때문에 orders를 씀
@Getter @Setter  //실무에서는 가급적 Setter열어두지 X
public class Order {

    @Id
    @GeneratedValue  //데이터베이스에서 직접 id를 생성해서 넘겨준다
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //연관관계의 주인
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")  //연관관계의 주인
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //== 연관관계 메서드 ==//  연관관계 편의 메서드는 한쪽에서만 해주어야함
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);  //ArrayList  //양방향 연관관계
    }

    public void addOrderItem(OrderItem orderItem) {  //양방향 연관관계
        orderItems.add(orderItem); //ArrayList
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {  //양방향 연관관계
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //생성 메서드(연관관계 초기화 메서드)
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);  //연관관계 메서드사용
        order.setDelivery(delivery);  //연관관계 메서드사용

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem); //연관관계 메서드사용
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직 ==//

    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
