package com.example.payment.controller.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.Enum.ResponseSuccessCode;
import com.example.payment.model.api.ApiResponse;
import com.example.payment.model.member.MemberRequestDTO;
import com.example.payment.model.member.MemberResponseDTO;
import com.example.payment.service.member.MemberService;

/**
 * 멤버 관련기능 Controller
 */
@RestController
@RequestMapping("/payment/v1")
public class MemberController {
    
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    /**
     * 회원목록 조회
     * 
     * @param memberRequestDTO
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<MemberResponseDTO>>> memberList(MemberRequestDTO memberRequestDTO){

        int pageno = (memberRequestDTO.getPageno() <= 0) ? 0 : memberRequestDTO.getPageno()-1;

        // 페이지 기본설정
        Pageable pageable = PageRequest.of(pageno,5);
        
        Page<MemberResponseDTO> result = memberService.getMemberList(memberRequestDTO, pageable);
        
        List<MemberResponseDTO> resultList = result.getContent().stream()
                .map(member -> new MemberResponseDTO(member.getId(), member.getName()
                                , member.getViewCount(), member.getCreateDt()))        
                .collect(Collectors.toList());

        return ApiResponse.success(ResponseSuccessCode.SUCCESS_GET.getStatus()
                                    , ResponseSuccessCode.SUCCESS_GET.getCode()
                                    , ResponseSuccessCode.SUCCESS_GET.getMessage()
                                    , resultList, pageable.getPageNumber()+1);
    }

}
