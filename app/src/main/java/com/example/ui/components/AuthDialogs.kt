package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.LoginFormState
import com.example.model.SignUpFormState
import com.example.ui.theme.AugustaGold
import com.example.ui.theme.AugustaGoldDark
import com.example.ui.theme.AugustaGoldLight
import com.example.ui.theme.BorderLight
import com.example.ui.theme.SurfaceBackground
import com.example.ui.theme.MastersGreenDark
import com.example.ui.theme.MastersGreenLight
import com.example.ui.theme.MastersGreenPrimary
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMedium
import com.example.ui.theme.TextMuted

/**
 * 1. 로그인 다이얼로그
 */
@Composable
fun LoginDialog(
  onDismiss: () -> Unit,
  onLogin: (email: String, pw: String) -> Boolean,
  onNavigateToSignUp: () -> Unit,
  onOpenPrivacyPolicy: () -> Unit,
) {
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var isPasswordVisible by remember { mutableStateOf(false) }
  var errorMessage by remember { mutableStateOf<String?>(null) }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.92f)
        .testTag("login_dialog"),
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(8.dp),
    ) {
      Column(
        modifier = Modifier
          .padding(24.dp)
          .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        // Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .background(MastersGreenLight, CircleShape),
              contentAlignment = Alignment.Center,
            ) {
              Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = MastersGreenPrimary,
                modifier = Modifier.size(20.dp),
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "로그인",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MastersGreenDark,
              )
              Text(
                text = "88 WELLNESS 회원 서비스",
                fontSize = 12.sp,
                color = TextMuted,
              )
            }
          }

          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "닫기", tint = TextMuted)
          }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Email Field
        OutlinedTextField(
          value = email,
          onValueChange = {
            email = it
            errorMessage = null
          },
          label = { Text("이메일 아이디") },
          placeholder = { Text("example@gmail.com") },
          leadingIcon = {
            Icon(Icons.Default.Email, contentDescription = null, tint = MastersGreenPrimary)
          },
          singleLine = true,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MastersGreenPrimary,
            focusedLabelColor = MastersGreenPrimary,
          ),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("login_email_input"),
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Password Field
        OutlinedTextField(
          value = password,
          onValueChange = {
            password = it
            errorMessage = null
          },
          label = { Text("비밀번호") },
          placeholder = { Text("비밀번호를 입력하세요") },
          leadingIcon = {
            Icon(Icons.Default.Lock, contentDescription = null, tint = MastersGreenPrimary)
          },
          trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
              Icon(
                imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = if (isPasswordVisible) "비밀번호 숨기기" else "비밀번호 보기",
                tint = TextMuted,
              )
            }
          },
          visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
          singleLine = true,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MastersGreenPrimary,
            focusedLabelColor = MastersGreenPrimary,
          ),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("login_password_input"),
        )

        if (errorMessage != null) {
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
          )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Login Button
        Button(
          onClick = {
            if (email.isBlank()) {
              errorMessage = "이메일을 입력해 주세요."
              return@Button
            }
            if (password.isBlank()) {
              errorMessage = "비밀번호를 입력해 주세요."
              return@Button
            }
            val success = onLogin(email.trim(), password)
            if (!success) {
              errorMessage = "이메일 또는 비밀번호가 일치하지 않습니다."
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag("login_submit_button"),
          colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
          shape = RoundedCornerShape(12.dp),
        ) {
          Text("로그인", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Sign Up Link & Quick Test Login
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          TextButton(onClick = onOpenPrivacyPolicy) {
            Text(
              text = "개인정보처리방침",
              fontSize = 12.sp,
              color = TextMuted,
            )
          }

          Row(verticalAlignment = Alignment.CenterVertically) {
            Text("아직 회원이 아니신가요?", fontSize = 12.sp, color = TextMedium)
            TextButton(
              onClick = {
                onDismiss()
                onNavigateToSignUp()
              },
              modifier = Modifier.testTag("go_to_signup_button"),
            ) {
              Text("회원가입", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MastersGreenDark)
            }
          }
        }
      }
    }
  }
}

/**
 * 2. 회원가입 다이얼로그 (구글 플레이 정책 표준 개인정보 수집 동의 포함)
 */
@Composable
fun SignUpDialog(
  onDismiss: () -> Unit,
  onSignUp: (name: String, email: String, pw: String, phone: String) -> Boolean,
  onNavigateToLogin: () -> Unit,
  onOpenPrivacyPolicy: () -> Unit,
) {
  var name by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var passwordConfirm by remember { mutableStateOf("") }
  var phone by remember { mutableStateOf("") }
  var agreedPrivacy by remember { mutableStateOf(false) }
  var agreedTerms by remember { mutableStateOf(false) }
  var errorMessage by remember { mutableStateOf<String?>(null) }
  var isPasswordVisible by remember { mutableStateOf(false) }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.94f)
        .testTag("signup_dialog"),
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(8.dp),
    ) {
      Column(
        modifier = Modifier
          .padding(24.dp)
          .verticalScroll(rememberScrollState()),
      ) {
        // Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .background(AugustaGoldLight, CircleShape),
              contentAlignment = Alignment.Center,
            ) {
              Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = AugustaGoldDark,
                modifier = Modifier.size(20.dp),
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "88 WELLNESS 회원가입",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MastersGreenDark,
              )
              Text(
                text = "맞춤 웰니스 리포트 & 플랜 보관",
                fontSize = 11.sp,
                color = TextMuted,
              )
            }
          }

          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "닫기", tint = TextMuted)
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        OutlinedTextField(
          value = name,
          onValueChange = { name = it; errorMessage = null },
          label = { Text("이름 (실명)") },
          placeholder = { Text("홍길동") },
          leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = MastersGreenPrimary) },
          singleLine = true,
          modifier = Modifier.fillMaxWidth().testTag("signup_name_input"),
          colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MastersGreenPrimary),
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Email
        OutlinedTextField(
          value = email,
          onValueChange = { email = it; errorMessage = null },
          label = { Text("이메일 (아이디)") },
          placeholder = { Text("name@example.com") },
          leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = MastersGreenPrimary) },
          singleLine = true,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
          modifier = Modifier.fillMaxWidth().testTag("signup_email_input"),
          colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MastersGreenPrimary),
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Phone
        OutlinedTextField(
          value = phone,
          onValueChange = { phone = it; errorMessage = null },
          label = { Text("연락처 (휴대폰)") },
          placeholder = { Text("010-0000-0000") },
          leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = MastersGreenPrimary) },
          singleLine = true,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
          modifier = Modifier.fillMaxWidth().testTag("signup_phone_input"),
          colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MastersGreenPrimary),
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Password
        OutlinedTextField(
          value = password,
          onValueChange = { password = it; errorMessage = null },
          label = { Text("비밀번호 (6자 이상)") },
          leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MastersGreenPrimary) },
          trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
              Icon(
                imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = null,
                tint = TextMuted,
              )
            }
          },
          visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
          singleLine = true,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          modifier = Modifier.fillMaxWidth().testTag("signup_password_input"),
          colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MastersGreenPrimary),
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Password Confirm
        OutlinedTextField(
          value = passwordConfirm,
          onValueChange = { passwordConfirm = it; errorMessage = null },
          label = { Text("비밀번호 확인") },
          leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MastersGreenPrimary) },
          visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
          singleLine = true,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          modifier = Modifier.fillMaxWidth().testTag("signup_password_confirm_input"),
          colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MastersGreenPrimary),
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Terms & Privacy Policy Checkboxes (Google Play Requirement)
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = SurfaceBackground),
          border = BorderStroke(1.dp, BorderLight),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Column(modifier = Modifier.padding(10.dp)) {
            // Privacy policy agreement
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.fillMaxWidth(),
            ) {
              Checkbox(
                checked = agreedPrivacy,
                onCheckedChange = { agreedPrivacy = it; errorMessage = null },
                colors = CheckboxDefaults.colors(checkedColor = MastersGreenPrimary),
                modifier = Modifier.size(28.dp).testTag("agree_privacy_checkbox"),
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "[필수] 개인정보 수집 및 이용 동의",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = TextDark,
                modifier = Modifier.weight(1f),
              )
              TextButton(
                onClick = onOpenPrivacyPolicy,
                modifier = Modifier.testTag("view_privacy_policy_button"),
              ) {
                Text("전문보기", fontSize = 11.sp, color = MastersGreenPrimary, fontWeight = FontWeight.Bold)
              }
            }

            // Terms agreement
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.fillMaxWidth(),
            ) {
              Checkbox(
                checked = agreedTerms,
                onCheckedChange = { agreedTerms = it; errorMessage = null },
                colors = CheckboxDefaults.colors(checkedColor = MastersGreenPrimary),
                modifier = Modifier.size(28.dp).testTag("agree_terms_checkbox"),
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "[필수] 88 WELLNESS 서비스 이용약관 동의",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = TextDark,
                modifier = Modifier.weight(1f),
              )
            }
          }
        }

        if (errorMessage != null) {
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button
        Button(
          onClick = {
            when {
              name.isBlank() -> errorMessage = "이름을 입력해 주세요."
              email.isBlank() || !email.contains("@") -> errorMessage = "올바른 이메일 주소를 입력해 주세요."
              phone.isBlank() -> errorMessage = "연락처를 입력해 주세요."
              password.length < 6 -> errorMessage = "비밀번호는 6자리 이상이어야 합니다."
              password != passwordConfirm -> errorMessage = "비밀번호가 일치하지 않습니다."
              !agreedPrivacy -> errorMessage = "개인정보 수집 및 이용에 동의해 주세요."
              !agreedTerms -> errorMessage = "서비스 이용약관에 동의해 주세요."
              else -> {
                val ok = onSignUp(name.trim(), email.trim(), password, phone.trim())
                if (!ok) {
                  errorMessage = "이미 등록된 이메일 계정입니다."
                }
              }
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag("signup_submit_button"),
          colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
          shape = RoundedCornerShape(12.dp),
        ) {
          Text("회원가입 완료", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text("이미 계정이 있으신가요?", fontSize = 12.sp, color = TextMedium)
          TextButton(
            onClick = {
              onDismiss()
              onNavigateToLogin()
            },
            modifier = Modifier.testTag("go_to_login_button"),
          ) {
            Text("로그인하기", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MastersGreenDark)
          }
        }
      }
    }
  }
}

/**
 * 3. 개인정보처리방침 다이얼로그 (Google Play Store Developer Policy 표준 전문)
 */
@Composable
fun PrivacyPolicyDialog(
  onDismiss: () -> Unit,
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.95f)
        .heightIn(max = 680.dp)
        .testTag("privacy_policy_dialog"),
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(8.dp),
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(24.dp),
      ) {
        // Top Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .background(MastersGreenLight, CircleShape),
              contentAlignment = Alignment.Center,
            ) {
              Icon(
                imageVector = Icons.Default.Shield,
                contentDescription = null,
                tint = MastersGreenPrimary,
                modifier = Modifier.size(20.dp),
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "개인정보처리방침",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MastersGreenDark,
              )
              Text(
                text = "(주)넥스트헬스케어 · 88 WELLNESS",
                fontSize = 11.sp,
                color = TextMuted,
              )
            }
          }

          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "닫기", tint = TextMuted)
          }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = BorderLight)

        // Scrollable Policy Content
        Column(
          modifier = Modifier
            .weight(1f)
            .verticalScroll(rememberScrollState()),
        ) {
          Text(
            text = "주식회사 넥스트헬스케어(이하 '회사')는 이용자의 개인정보를 매우 소중하게 생각하며, 「개인정보 보호법」 및 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」, Google Play 개발자 정책을 엄격히 준수하고 있습니다. 본 방침을 통하여 이용자께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며, 개인정보 보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다.",
            fontSize = 12.sp,
            color = TextDark,
            lineHeight = 18.sp,
          )

          Spacer(modifier = Modifier.height(14.dp))
          PolicySection(
            number = "1",
            title = "수집하는 개인정보의 항목 및 수집 방법",
            content = "1) 필수 수집 항목: 성명, 이메일 주소(계정 ID), 비밀번호(암호화 저장), 휴대폰 번호\n" +
              "2) 서비스 이용 시 생성·수집 항목: MY 88 CHECK 자가진단 항목 답변 내역, 88 활력 지표 점수, 데일리 실천 기록, 복합문화공간 체험 및 상담 예약 신청 내역\n" +
              "3) 수집 방법: 회원가입, 자가진단 응답, 프로그램 예약 신청 시 이용자의 직접 입력 및 동의",
          )

          PolicySection(
            number = "2",
            title = "개인정보의 수집 및 이용 목적",
            content = "회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다:\n" +
              "- 회원 식별, 가입 의사 확인, 본인 인증 및 부정이용 방지\n" +
              "- MY 88 CHECK 자가진단 결과 산출 및 맞춤형 88 라이프 플랜 리포트 저장·열람 제공\n" +
              "- 복합문화공간 시설 및 프라이빗 웰니스 프로그램 예약 접수 및 1:1 컨시어지 상담 안내\n" +
              "- 서비스 개선, 통계 분석, 고객 문의 응대 및 주요 공지사항 안내",
          )

          PolicySection(
            number = "3",
            title = "개인정보의 보유 및 이용 기간",
            content = "이용자의 개인정보는 원칙적으로 개인정보의 수집 및 이용 목적이 달성되면 지체 없이 파기합니다.\n" +
              "- 회원 정보: 회원 탈퇴 시까지 보유하며, 회원 탈퇴 시 즉시 영구 파기\n" +
              "- 전자상거래 등에서의 소비자보호에 관한 법률 등 관계 법령의 규정에 의하여 보존할 필요가 있는 경우 해당 법령이 정한 기간 동안 안전하게 보관합니다.",
          )

          PolicySection(
            number = "4",
            title = "개인정보의 제3자 제공 및 위탁",
            content = "회사는 이용자의 사전 동의 없이 개인정보를 외부에 제공하거나 위탁하지 않습니다. 단, 법령의 규정에 의거하거나 수사 목적으로 법령에 정해진 절차와 방법에 따라 수사기관의 요구가 있는 경우는 예외로 합니다.",
          )

          PolicySection(
            number = "5",
            title = "이용자의 권리와 행사 방법 (회원탈퇴 및 데이터 삭제)",
            content = "1) 이용자는 언제든지 앱 내 사이드 메뉴 또는 설정을 통해 자신의 개인정보를 열람, 수정하거나 회원탈퇴(계정 및 저장된 진단 데이터 완전 삭제)를 요청하실 수 있습니다.\n" +
              "2) Google Play 정책 준수: 앱 내에서 언제든 '회원탈퇴(계정 삭제)' 버튼을 통해 즉시 모든 개인정보와 검사 기록을 영구 파기할 수 있습니다.\n" +
              "3) 고객센터 유선(010-8254-8883) 또는 이메일(nexthealthcare8@gmail.com)로 요청 시에도 지체 없이 처리해 드립니다.",
          )

          PolicySection(
            number = "6",
            title = "개인정보 보호책임자 및 담당 부서",
            content = "회사는 고객의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다:\n\n" +
              "• 회사명: (주)넥스트헬스케어\n" +
              "• 대표이사 및 개인정보 보호책임자: 김재원\n" +
              "• E-MAIL: nexthealthcare8@gmail.com\n" +
              "• 연락처: 010-8254-8883\n" +
              "• 시행일자: 2026년 9월 30일 (개정)",
          )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
          onClick = onDismiss,
          modifier = Modifier.fillMaxWidth().height(48.dp),
          colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
          shape = RoundedCornerShape(12.dp),
        ) {
          Text("확인 및 닫기", fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}

@Composable
private fun PolicySection(
  number: String,
  title: String,
  content: String,
) {
  Column(modifier = Modifier.padding(bottom = 12.dp)) {
    Text(
      text = "제${number}조 ($title)",
      fontSize = 13.sp,
      fontWeight = FontWeight.Bold,
      color = MastersGreenDark,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = content,
      fontSize = 11.5.sp,
      color = TextDark,
      lineHeight = 17.sp,
    )
  }
}

/**
 * 4. 회사소개 다이얼로그 ((주)넥스트헬스케어 & 88 WELLNESS)
 */
@Composable
fun CompanyIntroDialog(
  onDismiss: () -> Unit,
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.94f)
        .heightIn(max = 680.dp)
        .testTag("company_intro_dialog"),
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(8.dp),
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(24.dp)
          .verticalScroll(rememberScrollState()),
      ) {
        // Top Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .background(MastersGreenLight, CircleShape),
              contentAlignment = Alignment.Center,
            ) {
              Icon(
                imageVector = Icons.Default.Business,
                contentDescription = null,
                tint = MastersGreenPrimary,
                modifier = Modifier.size(20.dp),
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "회사소개",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MastersGreenDark,
              )
              Text(
                text = "(주)넥스트헬스케어 · NEXT HEALTHCARE",
                fontSize = 11.sp,
                color = TextMuted,
              )
            }
          }

          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "닫기", tint = TextMuted)
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Vision Card
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MastersGreenDark),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Column(modifier = Modifier.padding(18.dp)) {
            Text(
              text = "OUR VISION",
              color = AugustaGold,
              fontSize = 11.sp,
              fontWeight = FontWeight.Black,
              letterSpacing = 1.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "좋아하는 삶을, 오래도록 팔팔하게",
              color = Color.White,
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "운동과 문화, 사회적 교류가 어우러진 차세대 프라이빗 웰니스 라이프센터 솔루션을 개척합니다.",
              color = Color.White.copy(alpha = 0.85f),
              fontSize = 12.sp,
              lineHeight = 17.sp,
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Core Values
        Text(
          text = "주요 사업 및 서비스 영역",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = MastersGreenDark,
        )

        Spacer(modifier = Modifier.height(8.dp))

        CompanyValueItem(
          title = "88 Longevity System",
          desc = "8가지 신체조건과 8가지 생활실천을 과학적으로 융합한 자가진단 및 맞춤 루틴 처방 시스템",
        )
        CompanyValueItem(
          title = "88 WELLNESS 복합문화공간",
          desc = "스마트 바이오 짐, 88 웰니스 라운지, 원적외선 편백 스파, 소셜 살롱이 집약된 고품격 웰니스 환경",
        )
        CompanyValueItem(
          title = "전문가 맞춤 케어",
          desc = "바이오메카닉 운동 코치, 물리치료사, 임상영양사 등 다학제 전문가의 1:1 라이프 솔루션",
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Company Details Table
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = AugustaGoldLight),
          border = BorderStroke(1.dp, AugustaGold.copy(alpha = 0.5f)),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            CompanyInfoRow(label = "상호명", value = "(주)넥스트헬스케어 (NEXT HEALTHCARE Inc.)")
            CompanyInfoRow(label = "대표이사", value = "김재원")
            CompanyInfoRow(label = "대표전화", value = "010-8254-8883")
            CompanyInfoRow(label = "대표메일", value = "nexthealthcare8@gmail.com")
            CompanyInfoRow(label = "핵심 브랜드", value = "88 WELLNESS / 터틀88")
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Button(
          onClick = onDismiss,
          modifier = Modifier.fillMaxWidth().height(48.dp),
          colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
          shape = RoundedCornerShape(12.dp),
        ) {
          Text("확인", fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}

@Composable
private fun CompanyValueItem(title: String, desc: String) {
  Row(modifier = Modifier.padding(vertical = 4.dp)) {
    Icon(
      Icons.Default.CheckCircle,
      contentDescription = null,
      tint = MastersGreenPrimary,
      modifier = Modifier.size(16.dp).padding(top = 2.dp),
    )
    Spacer(modifier = Modifier.width(6.dp))
    Column {
      Text(text = title, fontSize = 12.5.sp, fontWeight = FontWeight.Bold, color = TextDark)
      Text(text = desc, fontSize = 11.5.sp, color = TextMedium, lineHeight = 16.sp)
    }
  }
}

@Composable
private fun CompanyInfoRow(label: String, value: String) {
  Row(
    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
  ) {
    Text(
      text = label,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      color = AugustaGoldDark,
      modifier = Modifier.width(72.dp),
    )
    Text(
      text = value,
      fontSize = 12.sp,
      color = TextDark,
      fontWeight = FontWeight.Medium,
    )
  }
}

/**
 * 5. 결과 열람 게이트키퍼 다이얼로그 (MY 88 CHECK 완료 후 비로그인 사용자에게 노출)
 */
@Composable
fun AuthGateDialog(
  onDismiss: () -> Unit,
  onNavigateToSignUp: () -> Unit,
  onNavigateToLogin: () -> Unit,
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.92f)
        .testTag("auth_gate_dialog"),
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(10.dp),
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        // Icon Badge
        Box(
          modifier = Modifier
            .size(56.dp)
            .background(AugustaGoldLight, CircleShape),
          contentAlignment = Alignment.Center,
        ) {
          Icon(
            imageVector = Icons.Default.VerifiedUser,
            contentDescription = null,
            tint = AugustaGoldDark,
            modifier = Modifier.size(32.dp),
          )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
          text = "88 CHECK 자가진단 완료! 🎉",
          style = MaterialTheme.typography.titleLarge,
          fontWeight = FontWeight.Bold,
          color = MastersGreenDark,
          textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "회원님의 88 활력 지수 분석 리포트와 1:1 맞춤 추천 플랜을 확인하시려면 간편 회원가입 또는 로그인이 필요합니다.",
          fontSize = 13.5.sp,
          color = TextMedium,
          textAlign = TextAlign.Center,
          lineHeight = 19.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
          shape = RoundedCornerShape(10.dp),
          colors = CardDefaults.cardColors(containerColor = SurfaceBackground),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Text(
            text = "🌿 방금 진단하신 소중한 답변 데이터는 안전하게 보존되어 있으니, 가입 즉시 결과를 확인하실 수 있습니다.",
            fontSize = 11.5.sp,
            color = MastersGreenDark,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
          )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Sign Up Button
        Button(
          onClick = {
            onDismiss()
            onNavigateToSignUp()
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("gate_signup_button"),
          colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
          shape = RoundedCornerShape(12.dp),
        ) {
          Text("간편 회원가입하고 결과 보기", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Login Button
        OutlinedButton(
          onClick = {
            onDismiss()
            onNavigateToLogin()
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("gate_login_button"),
          shape = RoundedCornerShape(12.dp),
          border = BorderStroke(1.2.dp, MastersGreenPrimary),
        ) {
          Text("기존 계정으로 로그인", color = MastersGreenPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))

        TextButton(onClick = onDismiss) {
          Text("나중에 확인하기", color = TextMuted, fontSize = 12.sp)
        }
      }
    }
  }
}
