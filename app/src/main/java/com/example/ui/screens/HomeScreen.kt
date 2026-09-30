package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.LifestyleDomains
import com.example.model.PhysicalDomains
import com.example.model.ServiceCategoryType
import com.example.ui.components.CompanyFooter
import com.example.ui.components.SectionHeader
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
  onNavigateToCheck: () -> Unit,
  onNavigateToCategory: (ServiceCategoryType) -> Unit,
  onOpenMascotDialog: () -> Unit,
  modifier: Modifier = Modifier,
) {
  var matrixTab by remember { mutableIntStateOf(0) } // 0: 신체 8, 1: 생활 8

  LazyColumn(
    modifier = modifier.fillMaxSize(),
  ) {
    // 1. Hero Banner: AGELESS & LONGEVITY
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(280.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_wellness_hero),
          contentDescription = "88 WELLNESS 라운지",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop,
        )

        // Augusta Green Gradient Overlay for readability
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  MastersGreenDark.copy(alpha = 0.65f),
                  MastersGreenDark.copy(alpha = 0.95f),
                ),
                startY = 60f,
              )
            )
        )

        // Overlay Text Content
        Column(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .background(AugustaGold.copy(alpha = 0.25f))
              .border(1.dp, AugustaGold, RoundedCornerShape(8.dp))
              .padding(horizontal = 10.dp, vertical = 4.dp)
          ) {
            Icon(
              imageVector = Icons.Default.WorkspacePremium,
              contentDescription = null,
              tint = AugustaGold,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "WELLNESS & CULTURAL COMPLEX",
              color = AugustaGoldLight,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = "좋아하는 삶을,\n오래도록 팔팔하게",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 32.sp,
          )

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "운동과 문화. 사회적 교류가 있는 웰니스 라이프센터",
            color = Color.White.copy(alpha = 0.88f),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
          )
        }
      }
    }

    // 2. Mascot 터틀88 Card
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp)
          .clickable { onOpenMascotDialog() }
          .testTag("mascot_intro_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = AugustaGoldLight),
        border = BorderStroke(1.2.dp, AugustaGold.copy(alpha = 0.6f)),
        elevation = CardDefaults.cardElevation(2.dp),
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Box(
            modifier = Modifier
              .size(72.dp)
              .clip(CircleShape)
              .background(MastersGreenPrimary)
              .border(2.dp, AugustaGold, CircleShape)
          ) {
            Image(
              painter = painterResource(id = R.drawable.img_turtle88_mascot),
              contentDescription = "터틀88 마스코트",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop,
            )
          }

          Spacer(modifier = Modifier.width(14.dp))

          Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "안녕! 나는 ",
                color = TextDark,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
              )
              Text(
                text = "터틀88",
                color = MastersGreenDark,
                fontWeight = FontWeight.Black,
                fontSize = 16.sp,
              )
              Text(
                text = "이야!",
                color = TextDark,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
              )
            }
            Spacer(modifier = Modifier.height(3.dp))
            Text(
              text = "“느리지만 멈추지 않는 건강! 삶을 즐기도록 내가 늘 함께할게.”",
              style = MaterialTheme.typography.bodyMedium,
              color = TextMedium,
              lineHeight = 20.sp,
              fontSize = 13.sp,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "마스코트 이야기 보기",
                color = MastersGreenPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
              )
              Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MastersGreenPrimary,
                modifier = Modifier.size(16.dp)
              )
            }
          }
        }
      }
    }

    // 3. 5 Core Service Categories (BODY, MIND, CLASS, LIFE, CARE)
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        SectionHeader(
          title = "5대 웰니스 서비스",
          subtitle = "몸과 마음, 배움과 일상을 완성하는 공간",
          badge = "5 SERVICES",
        )

        Spacer(modifier = Modifier.height(14.dp))

        // 5 Category Cards
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          ServiceCategoryType.values().forEach { category ->
            Card(
              modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToCategory(category) }
                .testTag("service_card_${category.name.lowercase()}"),
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = SurfaceCard),
              border = BorderStroke(1.dp, BorderLight),
              elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Box(
                  modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MastersGreenSubtle),
                  contentAlignment = Alignment.Center,
                ) {
                  Icon(
                    imageVector = category.icon,
                    contentDescription = category.titleKo,
                    tint = MastersGreenPrimary,
                    modifier = Modifier.size(26.dp)
                  )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                      text = category.titleEn,
                      color = MastersGreenDark,
                      fontWeight = FontWeight.Black,
                      fontSize = 15.sp,
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text = "• ${category.subtitle}",
                      color = AugustaGoldDark,
                      fontWeight = FontWeight.SemiBold,
                      fontSize = 12.sp,
                    )
                  }
                  Spacer(modifier = Modifier.height(3.dp))
                  Text(
                    text = category.description,
                    color = TextMedium,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                  )
                }

                Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                  contentDescription = "자세히 보기",
                  tint = MastersGreenPrimary,
                  modifier = Modifier
                    .size(22.dp)
                    .padding(start = 4.dp),
                )
              }
            }
          }
        }
      }
    }

    // 4. 88 LONGEVITY Program Matrix (8 Physical × 8 Lifestyle)
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 14.dp)
      ) {
        Card(
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(containerColor = MastersGreenDark),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Column(modifier = Modifier.padding(18.dp)) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween,
              modifier = Modifier.fillMaxWidth()
            ) {
              Column {
                Text(
                  text = "88 LONGEVITY SYSTEM",
                  color = AugustaGold,
                  fontWeight = FontWeight.Black,
                  fontSize = 13.sp,
                  letterSpacing = 1.sp,
                )
                Text(
                  text = "8가지 조건과 8가지 실천",
                  color = Color.White,
                  style = MaterialTheme.typography.titleLarge,
                  fontWeight = FontWeight.Bold,
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = "88은 단순한 센터 이름이 아닙니다.\n8대 신체 지표와 8대 생활 지표를 융합하여, 언제나 활력 넘치고 건강한 삶을 누릴 수 있도록 이끄는 종합 평가·상담·처방 체계입니다.",
              color = Color.White.copy(alpha = 0.85f),
              style = MaterialTheme.typography.bodyMedium,
              fontSize = 13.sp,
              lineHeight = 20.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Switch Tab between Physical & Lifestyle
            TabRow(
              selectedTabIndex = matrixTab,
              containerColor = MastersGreenPrimary,
              contentColor = Color.White,
              indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                  Modifier.tabIndicatorOffset(tabPositions[matrixTab]),
                  color = AugustaGold,
                  height = 3.dp,
                )
              },
              modifier = Modifier.clip(RoundedCornerShape(10.dp))
            ) {
              Tab(
                selected = matrixTab == 0,
                onClick = { matrixTab = 0 },
                text = {
                  Text(
                    text = "8대 신체점수 (Body)",
                    fontWeight = if (matrixTab == 0) FontWeight.Bold else FontWeight.Normal,
                    color = if (matrixTab == 0) AugustaGold else Color.White,
                    fontSize = 13.sp,
                  )
                }
              )
              Tab(
                selected = matrixTab == 1,
                onClick = { matrixTab = 1 },
                text = {
                  Text(
                    text = "8대 생활점수 (Life)",
                    fontWeight = if (matrixTab == 1) FontWeight.Bold else FontWeight.Normal,
                    color = if (matrixTab == 1) AugustaGold else Color.White,
                    fontSize = 13.sp,
                  )
                }
              )
            }

            Spacer(modifier = Modifier.height(14.dp))

            if (matrixTab == 0) {
              // 8 Physical Scores Grid
              FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
              ) {
                PhysicalDomains.forEach { domain ->
                  Row(
                    modifier = Modifier
                      .clip(RoundedCornerShape(8.dp))
                      .background(Color.White.copy(alpha = 0.08f))
                      .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                      .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                  ) {
                    Icon(
                      imageVector = domain.icon,
                      contentDescription = null,
                      tint = AugustaGold,
                      modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text = "${domain.nameKo} (${domain.nameEn})",
                      color = Color.White,
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Medium,
                    )
                  }
                }
              }
            } else {
              // 8 Lifestyle Scores Grid
              FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
              ) {
                LifestyleDomains.forEach { domain ->
                  Row(
                    modifier = Modifier
                      .clip(RoundedCornerShape(8.dp))
                      .background(Color.White.copy(alpha = 0.08f))
                      .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                      .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                  ) {
                    Icon(
                      imageVector = domain.icon,
                      contentDescription = null,
                      tint = AugustaGold,
                      modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text = "${domain.nameKo} (${domain.nameEn})",
                      color = Color.White,
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Medium,
                    )
                  }
                }
              }
            }
          }
        }
      }
    }

    // 5. Big CTA for MY 88 CHECK
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        border = BorderStroke(2.dp, MastersGreenPrimary),
        elevation = CardDefaults.cardElevation(3.dp),
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
          ) {
            Icon(
              imageVector = Icons.Default.WorkspacePremium,
              contentDescription = null,
              tint = AugustaGoldDark,
              modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "처음 입회하셨나요?",
              style = MaterialTheme.typography.titleMedium,
              color = AugustaGoldDark,
              fontWeight = FontWeight.Bold,
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "MY 88 CHECK 진단 받기",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = MastersGreenDark,
            textAlign = TextAlign.Center,
          )

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "8가지 영역을 점검하고, '근력이 아니라 RECOVER와 CONNECT가 필요합니다'와 같은 나만의 LONGEVITY PLAN을 무료로 처방받으세요.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextMedium,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            fontSize = 13.sp,
          )

          Spacer(modifier = Modifier.height(16.dp))

          Button(
            onClick = onNavigateToCheck,
            colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
              .testTag("home_start_check_button"),
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "나의 88 지수 진단 & 플랜 생성",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
              )
              Spacer(modifier = Modifier.width(8.dp))
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
              )
            }
          }
        }
      }
    }

    item {
      CompanyFooter()
      Spacer(modifier = Modifier.height(20.dp))
    }
  }
}
