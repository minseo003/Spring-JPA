package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {  //다대다 연관관계 사이에서 일대다 다대일로 풀어주는 객체

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //단방향 연관관계
    @JoinColumn(name = "item_id")   //데이터베이스에있는 외래키와 엔티티에있는 Item 객체를 매핑
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")  //데이터베이스에있는 외래키와 엔티티에있는 Order 객체를 매핑
    private Order order;

    private int orderPrice;  //주문가격
    private int count;  //주문수량

    //==생성메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }

    //비즈니스 로직
    /**주문 취소 */
    public void cancel() {
        getItem().addStock(count);
    }

    //==조회 로직==//
    /**
     * 주문상품 전체 가격조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
