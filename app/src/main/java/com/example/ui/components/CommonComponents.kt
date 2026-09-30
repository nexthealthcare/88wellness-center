package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.ServiceProgramItem
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

@Composable
fun AppHeader(
  onMenuClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(
    color = MastersGreenPrimary,
    modifier = modifier.fillMaxWidth(),
    shadowElevation = 3.dp,
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 12.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "88",
            style = MaterialTheme.typography.headlineLarge,
            color = AugustaGold,
            fontWeight = FontWeight.Black,
            fontSize = 26.sp,
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "WELLNESS",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
          )
        }
        Text(
          text = "AGELESS PERFORMANCE CENTER",
          style = MaterialTheme.typography.labelMedium,
          color = Color.White.copy(alpha = 0.8f),
          letterSpacing = 1.5.sp,
          fontSize = 11.sp,
        )
      }

      // 3선(햄버거) 메뉴 아이콘
      IconButton(
        onClick = onMenuClick,
        modifier = Modifier
          .clip(CircleShape)
          .background(MastersGreenDark)
          .border(1.dp, AugustaGold.copy(alpha = 0.5f), CircleShape)
          .size(42.dp)
          .testTag("top_menu_button"),
      ) {
        Icon(
          imageVector = Icons.Default.Menu,
          contentDescription = "전체 메뉴",
          tint = Color.White,
          modifier = Modifier.size(24.dp),
        )
      }
    }
  }
}

@Composable
fun SectionHeader(
  title: String,
  subtitle: String? = null,
  badge: String? = null,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier.fillMaxWidth()) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth(),
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .width(4.dp)
            .height(20.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(MastersGreenPrimary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = title,
          style = MaterialTheme.typography.titleLarge,
          color = TextDark,
          fontWeight = FontWeight.Bold,
        )
      }
      if (badge != null) {
        Text(
          text = badge,
          color = MastersGreenDark,
          style = MaterialTheme.typography.labelMedium,
          modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MastersGreenLight)
            .padding(horizontal = 10.dp, vertical = 4.dp),
          fontWeight = FontWeight.Bold,
        )
      }
    }
    if (subtitle != null) {
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodyMedium,
        color = TextMedium,
        modifier = Modifier.padding(start = 12.dp),
      )
    }
  }
}

@Composable
fun ScoreDomainBar(
  nameKo: String,
  nameEn: String,
  score: Int,
  isAttentionNeeded: Boolean = false,
  modifier: Modifier = Modifier,
) {
  val animatedProgress by animateFloatAsState(
    targetValue = score / 100f,
    label = "score_progress",
  )

  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isAttentionNeeded) Color(0xFFFFF8F5) else SurfaceCard,
    ),
    border = BorderStroke(
      width = if (isAttentionNeeded) 1.5.dp else 1.dp,
      color = if (isAttentionNeeded) Color(0xFFE27D60) else BorderLight,
    ),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = nameKo,
            fontWeight = FontWeight.Bold,
            color = TextDark,
            fontSize = 15.sp,
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "($nameEn)",
            fontSize = 12.sp,
            color = TextMuted,
            fontWeight = FontWeight.Medium,
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          if (isAttentionNeeded) {
            Text(
              text = "집중 보완 필요",
              fontSize = 11.sp,
              color = Color(0xFFD64527),
              fontWeight = FontWeight.Bold,
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFFFECE6))
                .padding(horizontal = 6.dp, vertical = 2.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
          }
          Text(
            text = "$score",
            fontWeight = FontWeight.ExtraBold,
            color = if (isAttentionNeeded) Color(0xFFD64527) else MastersGreenPrimary,
            fontSize = 16.sp,
          )
          Text(
            text = "점",
            fontSize = 13.sp,
            color = TextMedium,
            fontWeight = FontWeight.Normal,
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
          .fillMaxWidth()
          .height(10.dp)
          .clip(RoundedCornerShape(5.dp)),
        color = if (isAttentionNeeded) Color(0xFFE27D60) else MastersGreenPrimary,
        trackColor = if (isAttentionNeeded) Color(0xFFFFECE6) else MastersGreenSubtle,
      )
    }
  }
}

@Composable
fun BookingConfirmationDialog(
  program: ServiceProgramItem,
  onDismiss: () -> Unit,
  onConfirm: () -> Unit,
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    shape = RoundedCornerShape(20.dp),
    containerColor = Color.White,
    title = {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.Info,
          contentDescription = null,
          tint = MastersGreenPrimary,
          modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "체험 및 상담 예약",
          style = MaterialTheme.typography.titleLarge,
          fontWeight = FontWeight.Bold,
          color = TextDark,
        )
      }
    },
    text = {
      Column(modifier = Modifier.fillMaxWidth()) {
        Text(
          text = program.title,
          style = MaterialTheme.typography.titleMedium,
          color = MastersGreenDark,
          fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "진행: ${program.instructor} • ${program.duration}",
          fontSize = 13.sp,
          color = TextMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
          colors = CardDefaults.cardColors(containerColor = MastersGreenSubtle),
          shape = RoundedCornerShape(10.dp),
        ) {
          Text(
            text = "88 WELLNESS 컨시어지에서 회원님의 스케줄에 맞추어 1:1 안내 연락을 드립니다.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextDark,
            modifier = Modifier.padding(12.dp),
          )
        }
      }
    },
    confirmButton = {
      Button(
        onClick = onConfirm,
        colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.testTag("dialog_confirm_button")
      ) {
        Text("예약 신청하기", color = Color.White, fontWeight = FontWeight.Bold)
      }
    },
    dismissButton = {
      OutlinedButton(
        onClick = onDismiss,
        shape = RoundedCornerShape(10.dp),
      ) {
        Text("취소", color = TextMedium)
      }
    }
  )
}

@Composable
fun SuccessDialog(
  onDismiss: () -> Unit,
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    shape = RoundedCornerShape(20.dp),
    containerColor = Color.White,
    icon = {
      Icon(
        imageVector = Icons.Default.CheckCircle,
        contentDescription = null,
        tint = MastersGreenPrimary,
        modifier = Modifier.size(48.dp)
      )
    },
    title = {
      Text(
        text = "예약 신청 완료!",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = TextDark,
        textAlign = TextAlign.Center,
      )
    },
    text = {
      Text(
        text = "88 WELLNESS 복합문화공간 체험 예약이 접수되었습니다. 센터 전담 웰니스 매니저가 유선으로 친절히 안내드리겠습니다.",
        style = MaterialTheme.typography.bodyMedium,
        color = TextMedium,
        textAlign = TextAlign.Center,
      )
    },
    confirmButton = {
      Button(
        onClick = onDismiss,
        colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("dialog_success_ok_button")
      ) {
        Text("확인", color = Color.White, fontWeight = FontWeight.Bold)
      }
    }
  )
}

@Composable
fun MascotStoryDialog(
  onDismiss: () -> Unit,
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    shape = RoundedCornerShape(24.dp),
    containerColor = Color.White,
    title = {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_turtle88_mascot),
          contentDescription = "터틀88",
          modifier = Modifier
            .size(54.dp)
            .clip(CircleShape),
          contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(
            text = "터틀88",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = MastersGreenPrimary,
          )
          Text(
            text = "장수와 팔팔한 활력의 상징",
            style = MaterialTheme.typography.labelMedium,
            color = AugustaGoldDark,
            fontWeight = FontWeight.Bold,
          )
        }
      }
    },
    text = {
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = "“느리지만 멈추지 않는 건강! 삶을 즐기도록 내가 늘 함께할게.”\n\n거북이는 결코 서두르지 않습니다. 하지만 올바른 방향으로 멈추지 않고 나아갑니다.\n\n88웰니스의 마스코트 터틀88은 조급함 대신 매일의 작은 좋은 습관을 쌓아 활기찬 삶을 영위하는 지혜를 상징하는 장수파트너 입니다.",
          style = MaterialTheme.typography.bodyMedium,
          color = TextDark,
          lineHeight = 22.sp,
          textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(14.dp))
        Card(
          colors = CardDefaults.cardColors(containerColor = AugustaGoldLight),
          shape = RoundedCornerShape(12.dp),
          border = BorderStroke(1.dp, AugustaGold.copy(alpha = 0.5f)),
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text(
              text = "88의 의미: 88 웰니스 시스템",
              fontWeight = FontWeight.Bold,
              color = AugustaGoldDark,
              fontSize = 13.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "8가지 핵심 신체조건과 8가지 일상 생활실천을 결합하여, 오래도록 팔팔하고 활기찬 삶을 지속하는 과학적 웰니스 솔루션",
              fontSize = 12.sp,
              color = TextDark,
            )
          }
        }
      }
    },
    confirmButton = {
      Button(
        onClick = onDismiss,
        colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text("함께 팔팔하게 시작하기", color = Color.White, fontWeight = FontWeight.Bold)
      }
    }
  )
}

/**
 * 모회사 (주)넥스트헬스케어 및 대표이사, 이메일, 전화번호 회사 푸터 컴포넌트
 */
@Composable
fun CompanyFooter(
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    HorizontalDivider(
      color = BorderLight,
      thickness = 1.dp,
      modifier = Modifier.padding(bottom = 18.dp),
    )

    // 회사명 & 대표이사
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxWidth(),
    ) {
      Text(
        text = "(주)넥스트헬스케어",
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        color = TextDark,
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "·",
        color = TextMuted,
        fontWeight = FontWeight.Bold,
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "대표이사 김재원",
        fontSize = 13.sp,
        color = TextMedium,
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    // 이메일 & 전화번호 (아이콘 및 탭 동작 지원)
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxWidth(),
    ) {
      // E-MAIL
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .clickable {
            try {
              val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:nexthealthcare8@gmail.com")
              }
              context.startActivity(intent)
            } catch (_: Exception) {}
          }
          .padding(horizontal = 6.dp, vertical = 4.dp),
      ) {
        Icon(
          imageVector = Icons.Default.Email,
          contentDescription = "E-MAIL",
          tint = MastersGreenPrimary,
          modifier = Modifier.size(16.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "nexthealthcare8@gmail.com",
          fontSize = 12.sp,
          color = TextMedium,
        )
      }
    }

    Spacer(modifier = Modifier.height(4.dp))

    // 전화번호
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxWidth(),
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .clickable {
            try {
              val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:01082548883")
              }
              context.startActivity(intent)
            } catch (_: Exception) {}
          }
          .padding(horizontal = 6.dp, vertical = 4.dp),
      ) {
        Icon(
          imageVector = Icons.Default.Phone,
          contentDescription = "전화번호",
          tint = MastersGreenPrimary,
          modifier = Modifier.size(16.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "010-8254-8883",
          fontSize = 12.sp,
          color = TextMedium,
          fontWeight = FontWeight.SemiBold,
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = "88 WELLNESS 복합문화공간  |  모회사 (주)넥스트헬스케어",
      fontSize = 11.sp,
      color = TextMuted,
      textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
      text = "© NEXT HEALTHCARE Inc. All rights reserved.",
      fontSize = 10.sp,
      color = TextMuted.copy(alpha = 0.8f),
      textAlign = TextAlign.Center,
    )
  }
}
