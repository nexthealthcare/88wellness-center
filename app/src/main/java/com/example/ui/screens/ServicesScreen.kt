package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AllServicePrograms
import com.example.model.CenterFacilities
import com.example.model.ServiceCategoryType
import com.example.model.ServiceProgramItem
import com.example.ui.components.CompanyFooter
import com.example.ui.components.SectionHeader
import com.example.ui.theme.AugustaGold
import com.example.ui.theme.AugustaGoldDark
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
fun ServicesScreen(
  selectedCategory: ServiceCategoryType?,
  onSelectCategory: (ServiceCategoryType?) -> Unit,
  onBookProgram: (ServiceProgramItem) -> Unit,
  modifier: Modifier = Modifier,
) {
  val filteredPrograms = if (selectedCategory == null) {
    AllServicePrograms
  } else {
    AllServicePrograms.filter { it.category == selectedCategory }
  }

  val filteredFacilities = if (selectedCategory == null) {
    CenterFacilities
  } else {
    CenterFacilities.filter { it.category == selectedCategory }
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp),
  ) {
    item {
      Spacer(modifier = Modifier.height(14.dp))

      SectionHeader(
        title = "복합문화공간",
        subtitle = "오래도록 건강한 삶을 누릴 수 있도록 엄선된 전문 프로그램",
        badge = "5 CATEGORIES",
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Filter Chips (ALL, BODY, MIND, CLASS, LIFE, CARE)
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        FilterChip(
          selected = selectedCategory == null,
          onClick = { onSelectCategory(null) },
          label = { Text("전체 보기", fontWeight = FontWeight.Bold) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MastersGreenPrimary,
            selectedLabelColor = Color.White,
          ),
          modifier = Modifier.testTag("filter_all")
        )

        ServiceCategoryType.values().forEach { cat ->
          FilterChip(
            selected = selectedCategory == cat,
            onClick = { onSelectCategory(cat) },
            label = { Text(cat.titleEn, fontWeight = FontWeight.Bold) },
            leadingIcon = {
              Icon(cat.icon, contentDescription = null, modifier = Modifier.size(16.dp))
            },
            colors = FilterChipDefaults.filterChipColors(
              selectedContainerColor = MastersGreenPrimary,
              selectedLabelColor = Color.White,
              selectedLeadingIconColor = AugustaGold,
            ),
            modifier = Modifier.testTag("filter_${cat.name.lowercase()}")
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Category Description Banner if a specific category is selected
      if (selectedCategory != null) {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MastersGreenLight),
          border = BorderStroke(1.dp, MastersGreenPrimary.copy(alpha = 0.2f)),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MastersGreenPrimary),
              contentAlignment = Alignment.Center,
            ) {
              Icon(
                imageVector = selectedCategory.icon,
                contentDescription = null,
                tint = AugustaGold,
                modifier = Modifier.size(24.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "${selectedCategory.titleEn} • ${selectedCategory.titleKo}",
                fontWeight = FontWeight.Black,
                color = MastersGreenDark,
                fontSize = 15.sp,
              )
              Text(
                text = selectedCategory.description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextMedium,
                fontSize = 12.sp,
                lineHeight = 17.sp,
              )
            }
          }
        }
        Spacer(modifier = Modifier.height(14.dp))
      }
    }

    // Program Cards
    items(filteredPrograms, key = { it.id }) { program ->
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 6.dp)
          .testTag("program_item_${program.id}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        border = BorderStroke(1.dp, BorderLight),
        elevation = CardDefaults.cardElevation(1.5.dp),
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MastersGreenSubtle)
                .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
              Text(
                text = program.category.titleEn,
                color = MastersGreenPrimary,
                fontWeight = FontWeight.Black,
                fontSize = 11.sp,
              )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = null,
                tint = TextMuted,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = program.duration,
                fontSize = 12.sp,
                color = TextMedium,
                fontWeight = FontWeight.Medium,
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = program.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextDark,
            fontSize = 17.sp,
          )

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = program.summary,
            style = MaterialTheme.typography.bodyMedium,
            color = TextMedium,
            fontSize = 13.sp,
            lineHeight = 19.sp,
          )

          Spacer(modifier = Modifier.height(10.dp))

          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Person,
              contentDescription = null,
              tint = AugustaGoldDark,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = program.instructor,
              fontSize = 12.sp,
              color = AugustaGoldDark,
              fontWeight = FontWeight.SemiBold,
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Tags
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            program.tags.forEach { tag ->
              Text(
                text = "#$tag",
                fontSize = 11.sp,
                color = MastersGreenMedium,
                modifier = Modifier
                  .clip(RoundedCornerShape(6.dp))
                  .background(MastersGreenSubtle)
                  .padding(horizontal = 8.dp, vertical = 3.dp),
                fontWeight = FontWeight.Medium,
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          Button(
            onClick = { onBookProgram(program) },
            colors = ButtonDefaults.buttonColors(containerColor = MastersGreenPrimary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(46.dp)
              .testTag("book_button_${program.id}"),
          ) {
            Text(
              text = "체험 신청 & 1:1 상담 예약",
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp,
              color = Color.White,
            )
          }
        }
      }
    }

    // Space & Facilities Section
    item {
      Spacer(modifier = Modifier.height(20.dp))

      SectionHeader(
        title = "복합문화공간 시설 안내",
        subtitle = "자연의 싱그러움과 편안함을 담은 고품격 웰니스 환경",
        badge = "SPACES",
      )

      Spacer(modifier = Modifier.height(12.dp))
    }

    items(filteredFacilities, key = { it.id }) { facility ->
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 5.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        border = BorderStroke(1.dp, BorderLight),
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(MastersGreenLight),
            contentAlignment = Alignment.Center,
          ) {
            Icon(
              imageVector = facility.icon,
              contentDescription = null,
              tint = MastersGreenPrimary,
              modifier = Modifier.size(22.dp)
            )
          }

          Spacer(modifier = Modifier.width(12.dp))

          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = facility.name,
              fontWeight = FontWeight.Bold,
              color = TextDark,
              fontSize = 14.sp,
            )
            Text(
              text = facility.feature,
              color = AugustaGoldDark,
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = facility.description,
              color = TextMedium,
              fontSize = 12.sp,
              lineHeight = 16.sp,
            )
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
