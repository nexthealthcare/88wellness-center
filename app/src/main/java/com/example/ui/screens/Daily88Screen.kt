package com.example.ui.screens

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
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
import com.example.viewmodel.WellnessUiState

@Composable
fun Daily88Screen(
  uiState: WellnessUiState,
  onToggleMission: (String) -> Unit,
  onOpenMascotDialog: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val missions = uiState.dailyMissions
  val completedCount = missions.count { it.isCompleted }
  val total = missions.size
  val vitalityPercent = if (total > 0) (completedCount * 100) / total else 0

  val animatedProgress by animateFloatAsState(
    targetValue = completedCount / total.toFloat(),
    label = "daily_progress",
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp),
  ) {
    item {
      Spacer(modifier = Modifier.height(14.dp))

      SectionHeader(
        title = "데일리 88 실천 챌린지",
        subtitle = "8대 생활 실천으로 채우는 오늘의 활력",
        badge = "DAILY 8",
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Vitality Tracker Header Card
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MastersGreenDark),
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column {
              Text(
                text = "오늘의 팔팔 활력 충전",
                color = AugustaGold,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
              )
              Row(verticalAlignment = Alignment.Bottom) {
                Text(
                  text = "$vitalityPercent",
                  color = Color.White,
                  fontSize = 32.sp,
                  fontWeight = FontWeight.Black,
                )
                Text(
                  text = "%",
                  color = AugustaGold,
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(bottom = 4.dp, start = 2.dp)
                )
              }
            }

            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MastersGreenPrimary)
                .border(1.dp, AugustaGold.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = "$completedCount / $total 완료",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier
              .fillMaxWidth()
              .height(10.dp)
              .clip(RoundedCornerShape(5.dp)),
            color = AugustaGold,
            trackColor = MastersGreenPrimary,
          )

          Spacer(modifier = Modifier.height(14.dp))

          // TURTLE88 Cheer Message
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(Color.White.copy(alpha = 0.1f))
              .clickable { onOpenMascotDialog() }
              .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Image(
              painter = painterResource(id = R.drawable.img_turtle88_mascot),
              contentDescription = "터틀88 응원",
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape),
              contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = uiState.mascotMessage,
              color = Color.White,
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium,
              lineHeight = 17.sp,
              modifier = Modifier.weight(1f),
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = "8가지 실천 항목 (터치하여 체크)",
        style = MaterialTheme.typography.titleMedium,
        color = TextDark,
        fontWeight = FontWeight.Bold,
      )

      Spacer(modifier = Modifier.height(8.dp))
    }

    // 8 Daily Habit Items
    items(missions, key = { it.id }) { mission ->
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp)
          .clickable { onToggleMission(mission.id) }
          .testTag("mission_item_${mission.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (mission.isCompleted) MastersGreenSubtle else SurfaceCard
        ),
        border = BorderStroke(
          width = if (mission.isCompleted) 1.5.dp else 1.dp,
          color = if (mission.isCompleted) MastersGreenPrimary else BorderLight,
        ),
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Box(
            modifier = Modifier
              .size(40.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(if (mission.isCompleted) MastersGreenPrimary else MastersGreenLight),
            contentAlignment = Alignment.Center,
          ) {
            Icon(
              imageVector = mission.icon,
              contentDescription = null,
              tint = if (mission.isCompleted) AugustaGold else MastersGreenPrimary,
              modifier = Modifier.size(20.dp),
            )
          }

          Spacer(modifier = Modifier.width(12.dp))

          Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "${mission.domainEn} • ${mission.domainKo}",
                color = if (mission.isCompleted) MastersGreenDark else AugustaGoldDark,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
              )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = mission.title,
              fontWeight = FontWeight.Bold,
              color = TextDark,
              fontSize = 14.sp,
            )
            Text(
              text = mission.desc,
              color = TextMedium,
              fontSize = 12.sp,
            )
          }

          Checkbox(
            checked = mission.isCompleted,
            onCheckedChange = { onToggleMission(mission.id) },
            colors = CheckboxDefaults.colors(
              checkedColor = MastersGreenPrimary,
              checkmarkColor = Color.White,
              uncheckedColor = BorderLight,
            ),
          )
        }
      }
    }

    // Longevity Wisdom Tip Card
    item {
      Spacer(modifier = Modifier.height(16.dp))

      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AugustaGoldLight),
        border = BorderStroke(1.dp, AugustaGold.copy(alpha = 0.5f)),
        modifier = Modifier.fillMaxWidth(),
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
              text = "오늘의 88 장수 지혜",
              fontWeight = FontWeight.Bold,
              color = AugustaGoldDark,
              fontSize = 14.sp,
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "“장수하는 사람들의 공통점은 과격한 운동이 아닌, 즐겁고 지속적인 일상 속 움직임과 따뜻한 친구 관계입니다. 오늘 하루도 88 WELLNESS와 함께 나만의 리듬으로 걸어가세요.”",
            style = MaterialTheme.typography.bodyMedium,
            color = TextDark,
            fontSize = 13.sp,
            lineHeight = 19.sp,
          )
        }
      }

      Spacer(modifier = Modifier.height(30.dp))
    }
  }
}
