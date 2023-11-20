package com.ssafy.enjoytrip.login.controller;

import com.ssafy.enjoytrip.login.model.MemberRequestDto;
import com.ssafy.enjoytrip.login.model.MemberResponseDto;
import com.ssafy.enjoytrip.login.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping("/login.do")
    public String openLogin() {
        return "member/login";
    }

    // 회원 정보 저장 (회원가입)
    @PostMapping("/members")
    @ResponseBody
    public Long saveMember(@RequestBody final MemberRequestDto params) {
        return memberService.saveMember(params);
    }

    // 회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberResponseDto findMemberByLoginId(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }

    // 회원 정보 수정
    @PatchMapping("/members/{id}")
    @ResponseBody
    public Long updateMember(@PathVariable final Long id, @RequestBody final MemberRequestDto params) {
        return memberService.updateMember(params);
    }

    // 회원 정보 삭제 (회원 탈퇴)
    @DeleteMapping("/members/{id}")
    @ResponseBody
    public Long deleteMemberById(final Long id) {
        return memberService.deleteMemberById(id);
    }

    // 회원 수 카운팅 (ID 중복 체크)
    @GetMapping("/member-count")
    @ResponseBody
    public int countMemberByLoginId(@RequestParam final String loginId) {
        return memberService.countMemberByLoginId(loginId);
    }

}