package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.CenterFocusStrong
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

// 5대 핵심 서비스 카테고리
enum class ServiceCategoryType(
  val titleKo: String,
  val titleEn: String,
  val subtitle: String,
  val icon: ImageVector,
  val description: String,
) {
  BODY(
    titleKo = "바디 (신체)",
    titleEn = "BODY",
    subtitle = "팔팔한 활력과 부드러운 관절",
    icon = Icons.Default.FitnessCenter,
    description = "전문 트레이너와 함께하는 관절 보호 근력 강화, 골프 비거리 및 보행 밸런스 운동",
  ),
  MIND(
    titleKo = "마인드 (마음·뇌)",
    titleEn = "MIND",
    subtitle = "명석한 두뇌와 평온한 마음",
    icon = Icons.Default.SelfImprovement,
    description = "치매 예방 브레인 인지 트레이닝, 심신 안정을 위한 싱잉볼 사운드 명상 및 호흡 테라피",
  ),
  CLASS(
    titleKo = "클래스 (배움)",
    titleEn = "CLASS",
    subtitle = "새로운 배움과 지적 호기심",
    icon = Icons.AutoMirrored.Filled.MenuBook,
    description = "스마트폰 & AI 일상 활용법, 인문학 여행 강좌, 가드닝, 수채화 및 음악 살롱",
  ),
  LIFE(
    titleKo = "라이프 (교류)",
    titleEn = "LIFE",
    subtitle = "따뜻한 소셜과 건강한 미식",
    icon = Icons.Default.LocalCafe,
    description = "지중해식 장수 영양 다이닝, 취향을 나누는 88 소셜 살롱, 웰니스 북 라운지",
  ),
  CARE(
    titleKo = "케어 (회복)",
    titleEn = "CARE",
    subtitle = "1:1 맞춤 관리와 힐링 회복",
    icon = Icons.Default.Spa,
    description = "척추 감압 및 체형 밸런스 도수 케어, 전신 온열 스파, 간호·운동 전문가의 헬스 리포트",
  )
}

// 8가지 신체점수 (Physical Score)
data class PhysicalDomain(
  val id: String,
  val nameEn: String,
  val nameKo: String,
  val icon: ImageVector,
  val summary: String,
  val description: String,
)

val PhysicalDomains = listOf(
  PhysicalDomain("strength", "STRENGTH", "근력", Icons.Default.FitnessCenter, "하체와 코어 지지력", "계단 오르내리기와 보행을 거뜬하게 지탱하는 기본 근육량"),
  PhysicalDomain("mobility", "MOBILITY", "가동성", Icons.Default.AccessibilityNew, "유연하고 부드러운 관절", "어깨, 고관절 회전 범위와 굳은 관절의 이완 능력"),
  PhysicalDomain("balance", "BALANCE", "균형감각", Icons.Default.CenterFocusStrong, "낙상 예방 안정성", "한 발 서기 및 불규칙한 지면에서도 흔들림 없는 평형 감각"),
  PhysicalDomain("endurance", "ENDURANCE", "지구력", Icons.AutoMirrored.Filled.DirectionsRun, "지치지 않는 심폐 활력", "장거리 여행과 하루 종일 활동해도 지치지 않는 심폐 체력"),
  PhysicalDomain("power", "POWER", "순발력", Icons.Default.FlashOn, "위기 대처 순간 반응", "발을 헛디뎠을 때 순간적으로 힘을 내어 자세를 바로잡는 힘"),
  PhysicalDomain("coordination", "COORDINATION", "협응력", Icons.Default.Psychology, "신체와 뇌의 조화", "시각과 손발의 기민한 조화로 정교한 동작을 수행하는 능력"),
  PhysicalDomain("agility", "AGILITY", "민첩성", Icons.Default.Speed, "기민한 방향 전환", "빠른 방향 전환과 장애물을 안전하게 피하는 발걸음 속도"),
  PhysicalDomain("recovery", "RECOVERY", "신체 회복력", Icons.Default.Spa, "피로 배출과 관절 회복", "활동 후 심박수가 빠르게 안정되고 다음 날 활력을 되찾는 속도"),
)

// 8가지 생활점수 (Lifestyle Score)
data class LifestyleDomain(
  val id: String,
  val nameEn: String,
  val nameKo: String,
  val icon: ImageVector,
  val summary: String,
  val description: String,
)

val LifestyleDomains = listOf(
  LifestyleDomain("move", "MOVE", "활동", Icons.Default.DirectionsWalk, "하루 7천보 이상 걷기", "좌식 생활을 줄이고 일상 속에서 즐겁게 걷고 움직이는 실천"),
  LifestyleDomain("mind", "MIND", "마음챙김", Icons.Default.SelfImprovement, "스트레스 조절과 평온", "깊은 호흡, 감사 일기, 명상을 통해 뇌 피로를 덜어내는 시간"),
  LifestyleDomain("nourish", "NOURISH", "영양식단", Icons.Default.Restaurant, "고단백·항산화 장수 식사", "근감소를 예방하는 신선한 단백질과 유기농 채소·올리브유 섭취"),
  LifestyleDomain("recover", "RECOVER", "수면·휴식", Icons.Default.Bedtime, "7시간 이상 깊은 숙면", "밤 시간의 질 좋은 숙면과 낮 시간의 따뜻한 릴랙스 휴식"),
  LifestyleDomain("connect", "CONNECT", "소셜 연결", Icons.Default.Groups, "친구와 대화하고 웃기", "고립되지 않고 주기적으로 모여 식사하고 이야기 나누는 교류"),
  LifestyleDomain("learn", "LEARN", "배움", Icons.AutoMirrored.Filled.MenuBook, "새로운 호기심 탐구", "스마트폰 신기능, 책 읽기, 외국어 등 두뇌를 자극하는 배움"),
  LifestyleDomain("purpose", "PURPOSE", "목적·성취", Icons.Default.Star, "아침을 깨우는 소명", "나만의 취미, 봉사, 일상적 목표를 통해 느끼는 살아가는 보람"),
  LifestyleDomain("enjoy", "ENJOY", "즐거움", Icons.Default.EmojiEmotions, "마음껏 웃고 즐기는 삶", "음악, 미술, 산책 등 가슴 뛰는 순간을 온전히 만끽하는 여유"),
)

// MY 88 CHECK 평가 질문 데이터
data class CheckQuestion(
  val id: Int,
  val domainKey: String,
  val domainNameKo: String,
  val domainNameEn: String,
  val questionText: String,
  val helpText: String,
  val options: List<String> = listOf(
    "1점 - 매우 부족 / 전혀 그렇지 않다",
    "2점 - 조금 부족 / 아쉬운 편이다",
    "3점 - 보통 / 그럭저럭 유지 중이다",
    "4점 - 양호 / 잘 실천하는 편이다",
    "5점 - 최상 / 매우 자신 있고 훌륭하다",
  ),
)

val SampleQuestions = listOf(
  CheckQuestion(
    id = 1,
    domainKey = "strength",
    domainNameKo = "근력 (Strength)",
    domainNameEn = "STRENGTH",
    questionText = "무거운 장바구니를 들거나 계단을 오를 때 무릎과 다리에 힘이 든든하게 들어가나요?",
    helpText = "신체 점수: 일상 생활을 힘차게 유지하는 하체와 코어의 기초 지지력 평가",
  ),
  CheckQuestion(
    id = 2,
    domainKey = "mobility",
    domainNameKo = "가동성 (Mobility)",
    domainNameEn = "MOBILITY",
    questionText = "어깨를 뒤로 돌리거나 신발 끈을 묶을 때 관절이 걸림 없이 부드럽게 움직이나요?",
    helpText = "신체 점수: 관절의 유연성과 회전 가동 범위 평가",
  ),
  CheckQuestion(
    id = 3,
    domainKey = "balance",
    domainNameKo = "균형감각 (Balance)",
    domainNameEn = "BALANCE",
    questionText = "눈을 뜨고 한 발로 20초 이상 흔들림 없이 설 수 있으며, 걸을 때 안정적인가요?",
    helpText = "신체 점수: 낙상을 예방하고 자세를 바르게 잡아주는 전정 밸런스 평가",
  ),
  CheckQuestion(
    id = 4,
    domainKey = "endurance",
    domainNameKo = "지구력 (Endurance)",
    domainNameEn = "ENDURANCE",
    questionText = "30분 이상 쉬지 않고 활기차게 걸어도 숨이 턱까지 차지 않고 호흡이 편안한가요?",
    helpText = "신체 점수: 여행과 여가 활동을 종일 즐길 수 있는 심폐 지구력 평가",
  ),
  CheckQuestion(
    id = 5,
    domainKey = "recover",
    domainNameKo = "수면 & 회복 (Recover)",
    domainNameEn = "RECOVER",
    questionText = "밤에 중간에 자주 깨지 않고 깊이 잠들며, 아침에 개운하게 기상하시나요?",
    helpText = "생활 점수: 신체 면역력과 피로 물질을 청소하는 야간 숙면 및 리커버리 평가",
  ),
  CheckQuestion(
    id = 6,
    domainKey = "connect",
    domainNameKo = "사회적 연결 (Connect)",
    domainNameEn = "CONNECT",
    questionText = "일주일에 2회 이상 지인, 친구와 만나 담소를 나누거나 클럽 활동에 참여하시나요?",
    helpText = "생활 점수: 고립감을 없애고 뇌 활력을 불어넣는 사회적 소셜 유대감 평가",
  ),
  CheckQuestion(
    id = 7,
    domainKey = "nourish",
    domainNameKo = "영양 식단 (Nourish)",
    domainNameEn = "NOURISH",
    questionText = "끼니마다 단백질(계란·생선·두부·고기)과 신선한 채소를 골고루 챙겨 드시나요?",
    helpText = "생활 점수: 신체 활력을 유지하고 전신 건강을 증진하는 균형 잡힌 장수 식단 평가",
  ),
  CheckQuestion(
    id = 8,
    domainKey = "learn",
    domainNameKo = "배움 & 호기심 (Learn)",
    domainNameEn = "LEARN",
    questionText = "스마트폰 새 앱을 배우거나 독서, 강좌 등 새로운 지식을 적극적으로 탐구하시나요?",
    helpText = "생활 점수: 인지 능력을 활성화하고 뇌 시냅스를 확장하는 지속적 배움 평가",
  ),
)

// 진단 결과 모델
data class CheckResult(
  val scores: Map<String, Int>, // domainKey -> 0..100
  val overallScore: Int,
  val weakDomains: List<WeakDomainInfo>,
  val longevityPlan: LongevityPlan,
)

data class WeakDomainInfo(
  val key: String,
  val nameEn: String,
  val nameKo: String,
  val score: Int,
  val advice: String,
  val recommendedService: ServiceCategoryType,
)

data class LongevityPlan(
  val title: String,
  val weeklyRoutine: List<WeeklyScheduleItem>,
  val recommendedClasses: List<String>,
  val dailyMissions: List<String>,
)

data class WeeklyScheduleItem(
  val day: String,
  val time: String,
  val title: String,
  val category: ServiceCategoryType,
  val place: String,
)

// 데일리 88 실천 아이템
data class DailyMission(
  val id: String,
  val domainEn: String,
  val domainKo: String,
  val title: String,
  val desc: String,
  val icon: ImageVector,
  var isCompleted: Boolean = false,
)
