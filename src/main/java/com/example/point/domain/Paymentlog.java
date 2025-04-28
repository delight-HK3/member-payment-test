package com.example.point.domain;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

/**
 * payment_log(결제내역) Entity 
 */
@Entity
@Getter
@Table(name="payment_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Paymentlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment(value = "결제 일련번호 ID (고유 키)")
    private Long id; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    @Comment(value = "회원 ID (외래 키)")
    private Member member;

    @Column(name = "amount")
    @Comment(value = "결제 금액")
    private int amount;


}
