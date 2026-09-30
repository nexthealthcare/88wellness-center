package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.UserProfile
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
import com.example.viewmodel.ScreenTab

/**
 * 3선(햄버거) 메뉴 클릭 시 열리는 사이드 드로어 시트
 */
@Composable
fun SideMenuContent(
  currentUser: UserProfile?,
  onClose: () -> Unit,
  onOpenLogin: () -> Unit,
  onOpenSignUp: () -> Unit,
  onOpenCompanyIntro: () -> Unit,
  onOpenPrivacyPolicy: () -> Unit,
  onNavigateToTab: (ScreenTab) -> Unit,
  onOpenMascotStory: () -> Unit,
  onLogout: () -> Unit,
  onDeleteAccount: () -> Unit,
  modifier: Modifier = Modifier,
) {
  var showDeleteAccountConfirm by remember { mutableStateOf(false) }

  ModalDrawerSheet(
    modifier = modifier
      .width(320.dp)
      .fillMaxHeight(),
    drawerContainerColor = Color.White,
  ) {
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .verticalScroll(rememberScrollState()),
    ) {
      // 1. Drawer Header (Branding & Close)
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(MastersGreenDark)
          .padding(start = 20.dp, end = 12.dp, top = 20.dp, bottom = 20.dp),
      ) {
        Column(modifier = Modifier.padding(end = 40.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "88",
              style = MaterialTheme.typography.titleLarge,
              color = AugustaGold,
              fontWeight = FontWeight.Black,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "WELLNESS",
              style = MaterialTheme.typography.titleLarge,
              color = Color.White,
              fontWeight = FontWeight.Bold,
            )
          }
          Text(
            text = "AGELESS PERFORMANCE CENTER",
            color = AugustaGoldLight.copy(alpha = 0.85f),
            fontSize = 10.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.SemiBold,
          )
        }

        IconButton(
          onClick = onClose,
          modifier = Modifier.align(Alignment.TopEnd),
        ) {
          Icon(Icons.Default.Close, contentDescription = "닫기", tint = Color.White)
        }
      }

      // 2. Auth Status Card
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
      ) {
        if (currentUser == null) {
          // 비로그인 상태 카드
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AugustaGoldLight),
            border = BorderStroke(1.dp, AugustaGold.copy(alpha = 0.5f)),
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Text(
                text = "로그인이 필요합니다",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MastersGreenDark,
              )
              Text(
                text = "회원가입 후 MY 88 CHECK 분석 리포트와 맞춤 플랜을 열람하실 수 있습니다.",
                fontSize = 11.5.sp,
                color = TextMedium,
                lineHeight = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp),
              )

              Spacer(modifier = Modifier.height(10.dp))

              Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                  onClick = {
                    onClose()
                    onOpenSignUp()
                  },
                  modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .testTag("drawer_signup_button"),
                  colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
                  shape = RoundedCornerShape(8.dp),
                ) {
                  Text("회원가입", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                  onClick = {
                    onClose()
                    onOpenLogin()
                  },
                  modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .testTag("drawer_login_button"),
                  shape = RoundedCornerShape(8.dp),
                  border = BorderStroke(1.dp, MastersGreenPrimary),
                ) {
                  Text("로그인", fontSize = 12.sp, color = MastersGreenPrimary, fontWeight = FontWeight.Bold)
                }
              }
            }
          }
        } else {
          // 로그인 완료된 사용자 카드
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MastersGreenLight),
            border = BorderStroke(1.dp, MastersGreenPrimary.copy(alpha = 0.3f)),
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                  modifier = Modifier
                    .size(40.dp)
                    .background(MastersGreenPrimary, CircleShape),
                  contentAlignment = Alignment.Center,
                ) {
                  Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp),
                  )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                  Text(
                    text = "${currentUser.name} 회원님",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = MastersGreenDark,
                  )
                  Text(
                    text = currentUser.email,
                    fontSize = 11.5.sp,
                    color = TextMedium,
                  )
                }
              }

              Spacer(modifier = Modifier.height(12.dp))

              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Text(
                  text = "가입일: ${currentUser.joinDate}",
                  fontSize = 11.sp,
                  color = TextMuted,
                )

                Row {
                  TextButton(
                    onClick = onLogout,
                    modifier = Modifier.testTag("drawer_logout_button"),
                  ) {
                    Text("로그아웃", fontSize = 11.5.sp, color = TextMedium)
                  }

                  TextButton(
                    onClick = { showDeleteAccountConfirm = true },
                    modifier = Modifier.testTag("drawer_delete_account_button"),
                  ) {
                    Text("회원탈퇴", fontSize = 11.5.sp, color = MaterialTheme.colorScheme.error)
                  }
                }
              }
            }
          }
        }
      }

      HorizontalDivider(color = BorderLight, modifier = Modifier.padding(horizontal = 16.dp))

      // 3. Category Menu Items
      Column(modifier = Modifier.padding(vertical = 8.dp)) {
        DrawerMenuItem(
          icon = Icons.Default.Business,
          title = "회사소개",
          subtitle = "(주)넥스트헬스케어 & 88 WELLNESS 비전",
          onClick = {
            onClose()
            onOpenCompanyIntro()
          },
          testTag = "menu_company_intro",
        )

        DrawerMenuItem(
          icon = Icons.Default.Shield,
          title = "개인정보처리방침",
          subtitle = "Google Play 정책 준수 처리방침 전문",
          onClick = {
            onClose()
            onOpenPrivacyPolicy()
          },
          testTag = "menu_privacy_policy",
        )

        if (currentUser == null) {
          DrawerMenuItem(
            icon = Icons.Default.PersonAdd,
            title = "회원가입",
            subtitle = "맞춤 웰니스 리포트 보관 및 프로그램 예약",
            onClick = {
              onClose()
              onOpenSignUp()
            },
            testTag = "menu_signup",
          )

          DrawerMenuItem(
            icon = Icons.Default.Person,
            title = "로그인",
            subtitle = "기존 등록 계정으로 접속",
            onClick = {
              onClose()
              onOpenLogin()
            },
            testTag = "menu_login",
          )
        }

        HorizontalDivider(color = BorderLight, modifier = Modifier.padding(16.dp, 8.dp))

        // App Feature Navigation Links
        DrawerMenuItem(
          icon = Icons.Default.Home,
          title = "소개 & 홈",
          subtitle = "88 WELLNESS 비전 및 전체 둘러보기",
          onClick = {
            onClose()
            onNavigateToTab(ScreenTab.HOME)
          },
          testTag = "menu_nav_home",
        )

        DrawerMenuItem(
          icon = Icons.Default.Assignment,
          title = "MY 88 CHECK 진단",
          subtitle = "8가지 신체조건 & 8가지 생활실천 지표 진단",
          onClick = {
            onClose()
            onNavigateToTab(ScreenTab.CHECK)
          },
          testTag = "menu_nav_check",
        )

        DrawerMenuItem(
          icon = Icons.Default.FitnessCenter,
          title = "복합문화공간 시설 & 프로그램",
          subtitle = "스마트 바이오 짐, 88 라운지, 프라이빗 케어",
          onClick = {
            onClose()
            onNavigateToTab(ScreenTab.SERVICES)
          },
          testTag = "menu_nav_services",
        )

        DrawerMenuItem(
          icon = Icons.Default.CheckCircleOutline,
          title = "데일리 88 실천",
          subtitle = "매일 실천하는 8대 웰니스 미션",
          onClick = {
            onClose()
            onNavigateToTab(ScreenTab.DAILY)
          },
          testTag = "menu_nav_daily",
        )

        DrawerMenuItem(
          icon = Icons.Default.WorkspacePremium,
          title = "터틀88 마스코트 이야기",
          subtitle = "느리지만 멈추지 않는 건강 파트너",
          onClick = {
            onClose()
            onOpenMascotStory()
          },
          testTag = "menu_nav_mascot",
        )
      }

      Spacer(modifier = Modifier.weight(1f))

      // 4. Drawer Footer
      Surface(
        color = SurfaceBackground,
        modifier = Modifier.fillMaxWidth(),
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "(주)넥스트헬스케어 · 대표이사 김재원",
            fontSize = 11.5.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark,
          )
          Spacer(modifier = Modifier.height(4.dp))
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Email, contentDescription = null, tint = MastersGreenPrimary, modifier = Modifier.size(13.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "nexthealthcare8@gmail.com", fontSize = 11.sp, color = TextMedium)
          }
          Spacer(modifier = Modifier.height(2.dp))
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Phone, contentDescription = null, tint = MastersGreenPrimary, modifier = Modifier.size(13.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "010-8254-8883", fontSize = 11.sp, color = TextMedium, fontWeight = FontWeight.SemiBold)
          }
        }
      }
    }
  }

  // 회원탈퇴 확인 팝업 (Google Play 필수 규정 준수)
  if (showDeleteAccountConfirm) {
    AlertDialog(
      onDismissRequest = { showDeleteAccountConfirm = false },
      icon = {
        Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error)
      },
      title = { Text("회원탈퇴 및 데이터 영구 삭제") },
      text = {
        Text("정말 탈퇴하시겠습니까?\n\n탈퇴 시 회원님의 계정 정보와 저장된 모든 88 CHECK 자가진단 리포트 데이터가 즉시 영구 삭제되며 복구할 수 없습니다.")
      },
      confirmButton = {
        Button(
          onClick = {
            showDeleteAccountConfirm = false
            onDeleteAccount()
            onClose()
          },
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
        ) {
          Text("탈퇴 및 데이터 삭제")
        }
      },
      dismissButton = {
        TextButton(onClick = { showDeleteAccountConfirm = false }) {
          Text("취소")
        }
      },
    )
  }
}

@Composable
private fun DrawerMenuItem(
  icon: ImageVector,
  title: String,
  subtitle: String,
  onClick: () -> Unit,
  testTag: String,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick() }
      .padding(horizontal = 20.dp, vertical = 12.dp)
      .testTag(testTag),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Box(
      modifier = Modifier
        .size(36.dp)
        .background(MastersGreenLight, CircleShape),
      contentAlignment = Alignment.Center,
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = MastersGreenPrimary,
        modifier = Modifier.size(18.dp),
      )
    }

    Spacer(modifier = Modifier.width(14.dp))

    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = TextDark,
      )
      Text(
        text = subtitle,
        fontSize = 11.sp,
        color = TextMuted,
      )
    }
  }
}
