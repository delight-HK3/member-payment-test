package com.example.point.controller.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.point.Enum.ResponseSuccessCode;
import com.example.point.model.api.ApiResponse;
import com.example.point.model.payment.PaymentRequestDTO;
import com.example.point.service.payment.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/point/v1")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/payment/{memberid}")
    public <T> ResponseEntity<ApiResponse<T>> confirmPayment(@PathVariable(value = "memberid") Long memberid, 
                                                    @Valid PaymentRequestDTO paymentRequestDTO) {

        return ApiResponse.nonBodyMessage(ResponseSuccessCode.SUCCESS_POST.getStatus()
                                        , ResponseSuccessCode.SUCCESS_POST.getCode()
                                        , paymentService.requestPayment(paymentRequestDTO, memberid));
        
    }

}
