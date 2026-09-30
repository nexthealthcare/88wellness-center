package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.ServiceCategoryType
import com.example.ui.components.ScoreDomainBar
import com.example.ui.components.SectionHeader
import com.example.ui.theme.AccentCoral
import com.example.ui.theme.AugustaGold
import com.example.ui.theme.AugustaGoldDark
import com.example.ui.theme.AugustaGoldLight
import com.example.ui.theme.BorderLight
import com.example.ui.theme.MastersGreenDark
import com.example.ui.theme.MastersGreenLight
import com.example.ui.theme.MastersGreenMedium
import com.example.ui.theme.MastersGreenPrimary
import com.example.ui.theme.MastersGreenSubtle
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMedium
import com.example.ui.theme.TextMuted
import com.example.viewmodel.WellnessUiState

@Composable
fun My88CheckScreen(
  uiState: WellnessUiState,
  onStartNewCheck: () -> Unit,
  onAnswerQuestion: (questionId: Int, score: Int) -> Unit,
  onPreviousQuestion: () -> Unit,
  onNavigateToCategory: (ServiceCategoryType) -> Unit,
  onOpenSignUp: () -> Unit,
  onOpenLogin: () -> Unit,
  modifier: Modifier = Modifier,
) {
  when {
    uiState.isEvaluating -> {
      // Evaluation Questionnaire Mode (Anyone can participate without login)
      CheckEvaluationView(
        uiState = uiState,
        onAnswer = onAnswerQuestion,
        onPrev = onPreviousQuestion,
        modifier = modifier,
      )
    }
    uiState.currentUser == null -> {
      // Not logged in
      if (uiState.hasPendingCheckResultAfterAuth) {
        // Finished evaluation, waiting for login/signup to view result
        CheckAuthPromptView(
          onSignUp = onOpenSignUp,
          onLogin = onOpenLogin,
          onRetake = onStartNewCheck,
          modifier = modifier,
        )
      } else {
        // Intro screen before test: Explains that evaluation is free without login!
        CheckIntroStartView(
          onStartCheck = onStartNewCheck,
          onOpenLogin = onOpenLogin,
          modifier = modifier,
        )
      }
    }
    else -> {
      // Logged in: Diagnostic Result & Personalized Longevity Plan Mode
      CheckResultView(
        uiState = uiState,
        onRetake = onStartNewCheck,
        onNavigateToCategory = onNavigateToCategory,
        modifier = modifier,
      )
    }
  }
}

/**
 * 평가 전 비로그인 사용자를 위한 안내 및 시작 화면
 * (회원가입 없이 누구나 무료로 자가진단 가능함을 명확히 안내)
 */
@Composable
private fun CheckIntroStartView(
  onStartCheck: () -> Unit,
  onOpenLogin: () -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    item {
      Spacer(modifier = Modifier.height(20.dp))

      // Top Badge
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(AugustaGoldLight)
          .border(1.dp, AugustaGold.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
          .padding(horizontal = 14.dp, vertical = 6.dp),
      ) {
        Text(
          text = "FREE ASSESSMENT · 누구나 무료 자가진단",
          color = AugustaGoldDark,
          fontSize = 11.5.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp,
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      Text(
        text = "MY 88 CHECK",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Black,
        color = MastersGreenDark,
        letterSpacing = 1.sp,
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "나의 8대 신체조건 & 8대 생활실천을 점검하고\n오래도록 팔팔한 활력 상태를 확인해보세요.",
        style = MaterialTheme.typography.bodyMedium,
        color = TextMedium,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp,
      )

      Spacer(modifier = Modifier.height(20.dp))

      // Information Card
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(1.dp, BorderLight),
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Text(
            text = "💡 진단 진행 안내",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MastersGreenDark,
          )

          Spacer(modifier = Modifier.height(10.dp))

          CheckIntroPoint(
            number = "1",
            title = "회원가입 없이 누구나 무료 응답",
            desc = "로그인하지 않아도 8대 핵심 신체 및 생활 문항에 자유롭게 답변하실 수 있습니다.",
          )
          CheckIntroPoint(
            number = "2",
            title = "소요 시간 약 2분 (8개 문항)",
            desc = "근력, 가동성, 밸런스, 지구력, 수면, 교류, 영양, 배움 핵심 지표 측정",
          )
          CheckIntroPoint(
            number = "3",
            title = "진단 완료 후 회원가입 시 결과 열람",
            desc = "자가진단 후 간편 회원가입 시 나만의 88 활력 지수와 1:1 맞춤 추천 플랜 리포트가 영구 보관됩니다.",
          )
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Start Button
      Button(
        onClick = onStartCheck,
        modifier = Modifier
          .fillMaxWidth()
          .height(54.dp)
          .testTag("start_free_check_button"),
        colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
        shape = RoundedCornerShape(14.dp),
        elevation = ButtonDefaults.buttonElevation(4.dp),
      ) {
        Text(
          text = "MY 88 CHECK 무료 진단 시작하기",
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
      }

      Spacer(modifier = Modifier.height(14.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text("이미 가입된 계정이 있으신가요?", fontSize = 12.5.sp, color = TextMedium)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "로그인하기",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = MastersGreenPrimary,
          modifier = Modifier
            .clickable { onOpenLogin() }
            .testTag("intro_login_link"),
        )
      }

      Spacer(modifier = Modifier.height(40.dp))
    }
  }
}

@Composable
private fun CheckIntroPoint(number: String, title: String, desc: String) {
  Row(modifier = Modifier.padding(vertical = 6.dp)) {
    Box(
      modifier = Modifier
        .size(24.dp)
        .background(MastersGreenLight, CircleShape),
      contentAlignment = Alignment.Center,
    ) {
      Text(text = number, color = MastersGreenPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
    Spacer(modifier = Modifier.width(10.dp))
    Column {
      Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextDark)
      Spacer(modifier = Modifier.height(2.dp))
      Text(text = desc, fontSize = 11.5.sp, color = TextMedium, lineHeight = 16.sp)
    }
  }
}

/**
 * 진단 완료 후 비로그인 사용자에게 회원가입을 유도하는 결과 잠금 뷰
 */
@Composable
private fun CheckAuthPromptView(
  onSignUp: () -> Unit,
  onLogin: () -> Unit,
  onRetake: () -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    item {
      Spacer(modifier = Modifier.height(30.dp))

      Box(
        modifier = Modifier
          .size(72.dp)
          .background(AugustaGoldLight, CircleShape)
          .border(2.dp, AugustaGold, CircleShape),
        contentAlignment = Alignment.Center,
      ) {
        Icon(
          Icons.Default.Verified,
          contentDescription = null,
          tint = AugustaGoldDark,
          modifier = Modifier.size(40.dp),
        )
      }

      Spacer(modifier = Modifier.height(18.dp))

      Text(
        text = "88 CHECK 진단 완료! 🎉",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = MastersGreenDark,
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "회원님의 답변이 성공적으로 기록되었습니다.\n종합 활력 지수와 1:1 맞춤 추천 플랜을 확인하시려면\n간편 회원가입 또는 로그인을 완료해 주세요.",
        fontSize = 13.5.sp,
        color = TextMedium,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp,
      )

      Spacer(modifier = Modifier.height(24.dp))

      // Benefits Box
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BorderLight),
        elevation = CardDefaults.cardElevation(2.dp),
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Text(
            text = "✨ 회원가입 시 즉시 제공되는 혜택",
            fontWeight = FontWeight.Bold,
            fontSize = 13.5.sp,
            color = MastersGreenDark,
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(text = "• 8대 신체조건 & 8대 생활실천 세부 레이더 차트 분석", fontSize = 12.sp, color = TextDark)
          Spacer(modifier = Modifier.height(4.dp))
          Text(text = "• 취약 영역 보완을 위한 1:1 맞춤 웰니스 처방 플랜", fontSize = 12.sp, color = TextDark)
          Spacer(modifier = Modifier.height(4.dp))
          Text(text = "• 복합문화공간 시설 & 전문가 1:1 프라이빗 예약 권한", fontSize = 12.sp, color = TextDark)
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      Button(
        onClick = onSignUp,
        modifier = Modifier
          .fillMaxWidth()
          .height(52.dp)
          .testTag("prompt_signup_button"),
        colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
        shape = RoundedCornerShape(12.dp),
      ) {
        Text("간편 회원가입하고 결과 보기", fontSize = 15.sp, fontWeight = FontWeight.Bold)
      }

      Spacer(modifier = Modifier.height(10.dp))

      OutlinedButton(
        onClick = onLogin,
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .testTag("prompt_login_button"),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.2.dp, MastersGreenPrimary),
      ) {
        Text("기존 계정으로 로그인", color = MastersGreenPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
      }

      Spacer(modifier = Modifier.height(12.dp))

      TextButton(
        onClick = onRetake,
        modifier = Modifier.testTag("prompt_retake_button"),
      ) {
        Text("처음부터 다시 진단하기", color = TextMuted, fontSize = 12.5.sp)
      }

      Spacer(modifier = Modifier.height(40.dp))
    }
  }
}

@Composable
private fun CheckEvaluationView(
  uiState: WellnessUiState,
  onAnswer: (questionId: Int, score: Int) -> Unit,
  onPrev: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val currentIdx = uiState.currentQuestionIndex
  val question = uiState.questions.getOrNull(currentIdx) ?: return
  val total = uiState.questions.size
  val selectedScore = uiState.answers[question.id] ?: 0

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 20.dp),
  ) {
    item {
      Spacer(modifier = Modifier.height(16.dp))

      // Progress bar & header
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = "MY 88 CHECK 평가 중",
          style = MaterialTheme.typography.titleMedium,
          color = MastersGreenDark,
          fontWeight = FontWeight.Bold,
        )
        Text(
          text = "${currentIdx + 1} / $total",
          style = MaterialTheme.typography.titleMedium,
          color = AugustaGoldDark,
          fontWeight = FontWeight.Black,
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      LinearProgressIndicator(
        progress = { (currentIdx + 1) / total.toFloat() },
        modifier = Modifier
          .fillMaxWidth()
          .height(8.dp)
          .clip(RoundedCornerShape(4.dp)),
        color = MastersGreenPrimary,
        trackColor = MastersGreenLight,
      )

      Spacer(modifier = Modifier.height(20.dp))

      // Domain Badge
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .background(MastersGreenLight)
          .padding(horizontal = 12.dp, vertical = 6.dp)
      ) {
        Text(
          text = "평가 영역: ${question.domainNameKo}",
          color = MastersGreenDark,
          fontWeight = FontWeight.Bold,
          fontSize = 13.sp,
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Question card
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        border = BorderStroke(1.2.dp, BorderLight),
        elevation = CardDefaults.cardElevation(2.dp),
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Text(
            text = "Q${currentIdx + 1}. ${question.questionText}",
            style = MaterialTheme.typography.titleLarge,
            color = TextDark,
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp,
          )

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = question.helpText,
            style = MaterialTheme.typography.bodyMedium,
            color = TextMedium,
            fontSize = 13.sp,
            lineHeight = 18.sp,
          )
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      Text(
        text = "가장 일치하는 항목을 터치해 주세요",
        style = MaterialTheme.typography.labelLarge,
        color = TextMedium,
        fontWeight = FontWeight.SemiBold,
      )

      Spacer(modifier = Modifier.height(10.dp))

      // 5 Rating Buttons (1 to 5)
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        listOf(
          5 to "5점 • 매우 자신 있고 훌륭하다",
          4 to "4점 • 양호하며 잘 실천하는 편이다",
          3 to "3점 • 보통이며 그럭저럭 유지 중이다",
          2 to "2점 • 조금 부족하고 개선이 필요하다",
          1 to "1점 • 매우 부족하여 도움이 필요하다",
        ).forEach { (score, label) ->
          val isSelected = selectedScore == score

          Card(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { onAnswer(question.id, score) }
              .testTag("score_option_${score}"),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
              containerColor = if (isSelected) MastersGreenPrimary else SurfaceCard
            ),
            border = BorderStroke(
              width = if (isSelected) 2.dp else 1.dp,
              color = if (isSelected) MastersGreenPrimary else BorderLight,
            ),
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween,
            ) {
              Text(
                text = label,
                color = if (isSelected) Color.White else TextDark,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 15.sp,
              )
              if (isSelected) {
                Icon(
                  imageVector = Icons.Default.CheckCircle,
                  contentDescription = null,
                  tint = AugustaGold,
                  modifier = Modifier.size(20.dp)
                )
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Navigation Buttons
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
      ) {
        if (currentIdx > 0) {
          OutlinedButton(
            onClick = onPrev,
            shape = RoundedCornerShape(10.dp),
          ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("이전 질문")
          }
        } else {
          Spacer(modifier = Modifier.width(1.dp))
        }

        if (selectedScore > 0) {
          Button(
            onClick = { onAnswer(question.id, selectedScore) },
            colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
            shape = RoundedCornerShape(10.dp),
          ) {
            Text(if (currentIdx == total - 1) "결과 및 플랜 보기" else "다음 질문")
            Spacer(modifier = Modifier.width(6.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
          }
        }
      }

      Spacer(modifier = Modifier.height(40.dp))
    }
  }
}

@Composable
private fun CheckResultView(
  uiState: WellnessUiState,
  onRetake: () -> Unit,
  onNavigateToCategory: (ServiceCategoryType) -> Unit,
  modifier: Modifier = Modifier,
) {
  val result = uiState.checkResult

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp),
  ) {
    item {
      Spacer(modifier = Modifier.height(14.dp))

      // 1. Overall Score & Status Card
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MastersGreenDark),
        elevation = CardDefaults.cardElevation(3.dp),
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column {
              Text(
                text = "MY 88 VITALITY REPORT",
                color = AugustaGold,
                fontSize = 12.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp,
              )
              Text(
                text = "${uiState.currentUser?.name ?: "회원"}님의 88 종합 활력 지수",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
              )
            }

            Box(
              modifier = Modifier
                .size(68.dp)
                .clip(CircleShape)
                .background(MastersGreenPrimary)
                .border(2.dp, AugustaGold, CircleShape),
              contentAlignment = Alignment.Center,
            ) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                  text = "${result?.overallScore ?: 72}",
                  color = Color.White,
                  fontWeight = FontWeight.ExtraBold,
                  fontSize = 24.sp,
                )
                Text(
                  text = "/ 100",
                  color = AugustaGold,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "신체 8영역과 생활 8영역을 종합 분석하여 도출된 활력 점수입니다. 88 WELLNESS의 맞춤 플랜으로 균형을 완성하세요.",
            color = Color.White.copy(alpha = 0.85f),
            fontSize = 13.sp,
            lineHeight = 19.sp,
          )
        }
      }
    }

    // 2. The Core Diagnostic Callout (As explicitly requested by user)
    // "현재 당신에게 부족한 것은 근력이 아니라 RECOVER과 CONNECT 입니다"
    item {
      Spacer(modifier = Modifier.height(14.dp))

      val weak1 = result?.weakDomains?.getOrNull(0)
      val weak2 = result?.weakDomains?.getOrNull(1)
      val weak1Name = weak1?.nameEn ?: "RECOVER"
      val weak2Name = weak2?.nameEn ?: "CONNECT"

      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("diagnostic_callout_banner"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9F5)),
        border = BorderStroke(2.dp, AccentCoral),
        elevation = CardDefaults.cardElevation(2.dp),
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
              painter = painterResource(id = R.drawable.img_turtle88_mascot),
              contentDescription = "터틀88 진단",
              modifier = Modifier
                .size(46.dp)
                .clip(CircleShape),
              contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "터틀88의 핵심 처방 분석",
                color = AccentCoral,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
              )
              Text(
                text = "개인별 정밀 진단 결과",
                style = MaterialTheme.typography.titleMedium,
                color = TextDark,
                fontWeight = FontWeight.ExtraBold,
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(Color(0xFFFFECE5))
              .padding(14.dp)
          ) {
            Column {
              Text(
                text = "“현재 회원님에게 부족한 것은 근력이 아니라",
                color = TextDark,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
              )
              Text(
                text = "${weak1Name}과 ${weak2Name} 입니다.”",
                color = AccentCoral,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "회원님은 기초 근력과 관절 밸런스는 우수하시나, 일상에서의 숙면·림프 회복(${weak1Name}) 및 활발한 소셜 교류(${weak2Name})를 보완할 때 진정한 무병장수(Ageless Longevity)가 실현됩니다.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextMedium,
            fontSize = 13.sp,
            lineHeight = 20.sp,
          )
        }
      }
    }

    // 3. Personalized 88 LONGEVITY PLAN
    item {
      Spacer(modifier = Modifier.height(20.dp))

      SectionHeader(
        title = "회원님 맞춤 88 LONGEVITY PLAN",
        subtitle = "평가 결과를 바탕으로 센터가 설계한 개인별 처방전",
        badge = "CUSTOM PLAN",
      )

      Spacer(modifier = Modifier.height(12.dp))

      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        border = BorderStroke(1.dp, BorderLight),
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.CalendarToday,
              contentDescription = null,
              tint = MastersGreenPrimary,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "추천 주간 스케줄 (Weekly Routine)",
              fontWeight = FontWeight.Bold,
              color = TextDark,
              fontSize = 16.sp,
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          result?.longevityPlan?.weeklyRoutine?.forEach { routine ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MastersGreenSubtle)
                .clickable { onNavigateToCategory(routine.category) }
                .padding(12.dp),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .background(MastersGreenPrimary)
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(
                  text = routine.day,
                  color = Color.White,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                )
              }

              Spacer(modifier = Modifier.width(10.dp))

              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = routine.title,
                  fontWeight = FontWeight.Bold,
                  color = TextDark,
                  fontSize = 14.sp,
                )
                Text(
                  text = "${routine.place} • ${routine.time}",
                  fontSize = 12.sp,
                  color = TextMedium,
                )
              }

              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MastersGreenPrimary,
                modifier = Modifier.size(16.dp)
              )
            }
          }
        }
      }
    }

    // 4. Daily Lifestyle Missions & Center Classes
    item {
      Spacer(modifier = Modifier.height(14.dp))

      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = AugustaGoldLight),
        border = BorderStroke(1.dp, AugustaGold.copy(alpha = 0.5f)),
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Lightbulb,
              contentDescription = null,
              tint = AugustaGoldDark,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "오늘부터 시작하는 88 실천 수칙",
              fontWeight = FontWeight.Bold,
              color = AugustaGoldDark,
              fontSize = 15.sp,
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          result?.longevityPlan?.dailyMissions?.forEachIndexed { index, mission ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
              verticalAlignment = Alignment.Top,
            ) {
              Text(
                text = "${index + 1}.",
                color = MastersGreenDark,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = mission,
                color = TextDark,
                fontSize = 13.sp,
                lineHeight = 18.sp,
              )
            }
          }
        }
      }
    }

    // 5. 8 Detailed Domain Scores
    item {
      Spacer(modifier = Modifier.height(20.dp))

      SectionHeader(
        title = "8대 지표 세부 진단표",
        subtitle = "신체와 생활 습관의 균형 분포",
      )

      Spacer(modifier = Modifier.height(12.dp))

      val weakKeys = result?.weakDomains?.map { it.key }?.toSet() ?: setOf("recover", "connect")

      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        uiState.questions.forEach { q ->
          val score = result?.scores?.get(q.domainKey) ?: 70
          val isWeak = weakKeys.contains(q.domainKey)
          ScoreDomainBar(
            nameKo = q.domainNameKo,
            nameEn = q.domainNameEn,
            score = score,
            isAttentionNeeded = isWeak,
          )
        }
      }
    }

    // 6. Retake Button
    item {
      Spacer(modifier = Modifier.height(24.dp))

      OutlinedButton(
        onClick = onRetake,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.2.dp, MastersGreenPrimary),
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .testTag("retake_check_button"),
      ) {
        Icon(
          imageVector = Icons.Default.Replay,
          contentDescription = null,
          tint = MastersGreenPrimary,
          modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "MY 88 CHECK 다시 진단하기",
          color = MastersGreenPrimary,
          fontWeight = FontWeight.Bold,
          fontSize = 15.sp,
        )
      }

      Spacer(modifier = Modifier.height(30.dp))
    }
  }
}
