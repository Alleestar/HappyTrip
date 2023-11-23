package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.model.MemberDto;

public interface MemberService {

	MemberDto login(MemberDto memberDto) throws Exception;
	MemberDto userInfo(String userId) throws Exception;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;

	MemberDto saveMember(MemberDto user) throws Exception;
	
}
