package com.example.point.service.payment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.point.Enum.ResponseFailCode;
import com.example.point.Enum.ResponseSuccessCode;
import com.example.point.domain.Member;
import com.example.point.exception.NoSearchException;
import com.example.point.model.payment.PaymentRequestDTO;
import com.example.point.model.payment.PaymentResultDTO;
import com.example.point.repository.member.MemberRepository;
import com.example.point.service.payment.paytype.PaymentTypeService;

/**
 * 결제관련 Service
 */
@Service
public class PaymentService {
    
    private final List<PaymentTypeService> typeservice;

    private final MemberRepository memberRepository;

    public PaymentService(List<PaymentTypeService> typeservice, MemberRepository memberRepository){
        this.typeservice = typeservice;
        this.memberRepository = memberRepository;
    }
    
    // 결제요청
    @Transactional
    public String requestPayment(PaymentRequestDTO paymentRequestDTO, Long memberid){
        
        // 회원 존재여부 확인
        Member member = memberRepository.findById(memberid)
                            .orElseThrow(() -> new NoSearchException(ResponseFailCode.NO_SEARCH_MEMBER));

        // 결제 플랫폼에 맞는 Service 조회
        PaymentTypeService paymentTypeService = this.findPayType(paymentRequestDTO.getPayment());
        
        try{
            PaymentResultDTO result = paymentTypeService.getPaymentRedirectURL(paymentRequestDTO);
            paymentTypeService.savePayment(member, paymentRequestDTO, result);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        return ResponseSuccessCode.SUCCESS_POST.getMessage();
    }

    // 결제 타입 확인
    private PaymentTypeService findPayType(String paymentValue){
        return typeservice.stream()
                            .filter(x -> x.getPaymentValue().getType().equals(paymentValue.toLowerCase()))
                            .findFirst()
                            .orElseThrow(() -> new NoSearchException(ResponseFailCode.NO_SEARCH_PAYMENT));
    }
}
