package com.example.point.controller.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.point.Enum.ResponseSuccessCode;
import com.example.point.domain.Member;
import com.example.point.model.api.ApiResponse;
import com.example.point.model.member.MemberRequestDTO;
import com.example.point.model.member.MemberResponseDTO;
import com.example.point.service.member.MemberService;

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
     * @return ResponseEntity<ApiResponse<List<MemberResponseDTO>>>
     */
    @GetMapping("/member")
    public ResponseEntity<ApiResponse<List<MemberResponseDTO>>> memberList(MemberRequestDTO memberRequestDTO){

        int pageno = (memberRequestDTO.getPageno() <= 0) ? 0 : memberRequestDTO.getPageno()-1;

        // 페이지 기본설정
        Pageable pageable = PageRequest.of(pageno,5);
        
        Page<MemberResponseDTO> result = memberService.getMemberList(memberRequestDTO, pageable);
        
        List<MemberResponseDTO> resultList = result.getContent().stream()
                .map(member -> MemberResponseDTO.builder()
                                        .id(member.getId())
                                        .name(member.getName())
                                        .viewCount(member.getViewCount())
                                        .createDt(member.getCreateDt())
                                    .build())        
                .collect(Collectors.toList());

        return ApiResponse.successList(ResponseSuccessCode.SUCCESS_GET.getStatus()
                                    , ResponseSuccessCode.SUCCESS_GET.getCode()
                                    , ResponseSuccessCode.SUCCESS_GET.getMessage()
                                    , resultList, pageable.getPageNumber()+1);
    }

    /**
     * 회원 1건 상세vv조회
     * 
     * @param memberid
     * @return ResponseEntity<ApiResponse<MemberResponseDTO>>
     */
    @GetMapping("/member/{memberid}")
    public ResponseEntity<ApiResponse<MemberResponseDTO>> memberDetail(@PathVariable(value = "memberid") Long memberid){

        Member result = memberService.getMemberDetail(memberid);

        return ApiResponse.successDetail(ResponseSuccessCode.SUCCESS_GET.getStatus()
                                        , ResponseSuccessCode.SUCCESS_GET.getCode()
                                        , ResponseSuccessCode.SUCCESS_GET.getMessage()
                                        , result.memberEntityToDTO());
    }

}
