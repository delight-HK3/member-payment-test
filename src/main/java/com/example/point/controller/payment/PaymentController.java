package com.example.point.controller.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.point.Enum.ResponseSuccessCode;
import com.example.point.model.api.ApiResponse;
import com.example.point.model.payment.PaymentRequestDTO;
import com.example.point.service.payment.PaymentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/point/v1")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    /**
     * 결제페이지 출력
     * 
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/main")
    public ModelAndView main(ModelAndView mav) {
        mav.setViewName("checkout");
        return mav;
    }

    /**
     * 인증성공처리
     * 
     * @param mav
     * @return
     */
    @GetMapping("/success")
    public ModelAndView paymentRequest(ModelAndView mav) {
        mav.setViewName("success");
        return mav;
    }

    /**
     * 인증실패처리
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/fail")
    public ModelAndView failPayment(HttpServletRequest request, ModelAndView mav) {
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        mav.addObject("code", failCode);
        mav.addObject("message", failMessage);
        mav.setViewName("fail");

        return mav;
    }

    /**
     * 주문하기
     * 
     * @param <T>
     * @param memberid
     * @param paymentRequestDTO
     * @return
     */
    @PostMapping("/payment/{memberid}")
    public <T> ResponseEntity<ApiResponse<T>> confirmPayment(@PathVariable(value = "memberid") Long memberid, 
                                                            @Valid @RequestBody PaymentRequestDTO paymentRequestDTO) {

        return ApiResponse.nonBodyMessage(ResponseSuccessCode.SUCCESS_POST.getStatus()
                                        , ResponseSuccessCode.SUCCESS_POST.getCode()
                                        , paymentService.requestPayment(paymentRequestDTO, memberid));   
    }

}
