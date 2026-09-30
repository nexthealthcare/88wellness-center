package com.example.model

/**
 * 회원 정보 모델
 */
data class UserProfile(
  val id: String = "user_1",
  val name: String,
  val email: String,
  val phone: String,
  val joinDate: String,
)

/**
 * 로그인 폼 상태
 */
data class LoginFormState(
  val email: String = "",
  val password: String = "",
  val errorMessage: String? = null,
)

/**
 * 회원가입 폼 상태
 */
data class SignUpFormState(
  val name: String = "",
  val email: String = "",
  val password: String = "",
  val passwordConfirm: String = "",
  val phone: String = "",
  val agreedToPrivacy: Boolean = false,
  val agreedToTerms: Boolean = false,
  val errorMessage: String? = null,
)
