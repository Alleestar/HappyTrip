package com.ssafy.enjoytrip.member.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "MemberDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class MemberDto {

	@ApiModelProperty(value = "아이디")
	private String userId;
	@ApiModelProperty(value = "이름")
	private String userName;
	@ApiModelProperty(value = "비밀번호")
	private String userPwd;
	@ApiModelProperty(value = "이메일 아이디")
	private String emailId;
	@ApiModelProperty(value = "이메일 도메인")
	private String emailDomain;
	@ApiModelProperty(value = "가입일")
	private String joinDate;
	@ApiModelProperty(value = "refreshToken")
	private String refreshToken;
	
}
