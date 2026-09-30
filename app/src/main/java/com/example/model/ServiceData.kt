package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Diversity3
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.ui.graphics.vector.ImageVector

data class ServiceProgramItem(
  val id: String,
  val title: String,
  val category: ServiceCategoryType,
  val targetAudience: String,
  val duration: String,
  val summary: String,
  val instructor: String,
  val tags: List<String>,
)

data class FacilitySpaceItem(
  val id: String,
  val name: String,
  val category: ServiceCategoryType,
  val icon: ImageVector,
  val feature: String,
  val description: String,
)

val AllServicePrograms = listOf(
  // BODY
  ServiceProgramItem(
    id = "p_body_1",
    title = "골프 컨디셔닝 & 흉추·고관절 가동성",
    category = ServiceCategoryType.BODY,
    targetAudience = "비거리 회복 및 허리 부담 없이 편안하게 라운딩하고 싶은 골퍼",
    duration = "50분 / 주 2회",
    summary = "푸른 페어웨이 잔디를 밟듯 안정된 하체 밸런스와 회전 유연성을 길러주는 맞춤 스트렝스",
    instructor = "이민우 수석 바이오메카닉 코치",
    tags = listOf("골프 특화", "관절 보호", "하체 밸런스"),
  ),
  ServiceProgramItem(
    id = "p_body_2",
    title = "낙상 ZERO 밸런스 & 코어 필라테스",
    category = ServiceCategoryType.BODY,
    targetAudience = "걸음걸이가 불안정하거나 평형 감각을 키우고 싶은 회원",
    duration = "45분 / 주 2회",
    summary = "특수 에어 밸런스 패드와 체형 기구를 활용해 넘어짐 걱정 없이 꼿꼿한 자세를 완성합니다.",
    instructor = "박수진 재활 필라테스 마스터",
    tags = listOf("낙상 예방", "자세 교정", "발목 안정성"),
  ),
  ServiceProgramItem(
    id = "p_body_3",
    title = "팔팔(88) 유산소 & 파워 워킹 서킷",
    category = ServiceCategoryType.BODY,
    targetAudience = "심폐 활력을 높이고 활기찬 일상 체력을 원하는 회원",
    duration = "40분 / 주 3회",
    summary = "관절 충격을 80% 줄여주는 무중력 보행 트레드밀과 경쾌한 리듬 인터벌 워킹",
    instructor = "정재훈 펑셔널 트레이닝 디렉터",
    tags = listOf("심폐 지구력", "관절 무충격", "활력 충전"),
  ),

  // MIND
  ServiceProgramItem(
    id = "p_mind_1",
    title = "치매 예방 브레인 싱킹 & 뉴로 피트니스",
    category = ServiceCategoryType.MIND,
    targetAudience = "기억력 감퇴를 예방하고 두뇌를 기민하게 깨우고 싶은 분",
    duration = "50분 / 주 2회",
    summary = "이중 과제 인지 운동(Dual-task)과 시각-공간 반응 훈련을 통해 뇌 신경망을 강화합니다.",
    instructor = "김은영 인지의학 박사",
    tags = listOf("두뇌 활력", "기억력 강화", "뉴로 트레이닝"),
  ),
  ServiceProgramItem(
    id = "p_mind_2",
    title = "싱잉볼 힐링 사운드 & 깊은 호흡 명상",
    category = ServiceCategoryType.MIND,
    targetAudience = "불면증과 스트레스로 깊은 쉼이 필요한 회원",
    duration = "60분 / 주 1~2회",
    summary = "티베트 수제 싱잉볼의 맑은 공명과 아로마 테라피로 자율신경계를 안정시키고 뇌파를 알파파로 이끕니다.",
    instructor = "서하은 마인드풀니스 안내자",
    tags = listOf("숙면 유도", "스트레스 완화", "자율신경 조절"),
  ),

  // CLASS
  ServiceProgramItem(
    id = "p_class_1",
    title = "스마트 라이프 마스터 (스마트폰 & 생성 AI)",
    category = ServiceCategoryType.CLASS,
    targetAudience = "스마트폰을 능숙하게 다루고 최신 AI를 일상에 접목하고 싶은 분",
    duration = "60분 / 주 1회",
    summary = "모바일 뱅킹, 건강 앱, 여행 사진 보정 및 나만의 AI 비서 대화법을 친절히 실습합니다.",
    instructor = "최준호 디지털 라이프 코치",
    tags = listOf("스마트폰 정복", "AI 활용", "디지털 자신감"),
  ),
  ServiceProgramItem(
    id = "p_class_2",
    title = "보태니컬 가드닝 & 웰니스 티 블렌딩",
    category = ServiceCategoryType.CLASS,
    targetAudience = "식물을 돌보고 자연의 향기를 만끽하며 소근육 감각을 깨우고 싶은 분",
    duration = "70분 / 격주",
    summary = "항산화 허브 식물을 직접 심고, 체질에 맞는 유기농 장수 차를 블렌딩하여 음미합니다.",
    instructor = "윤예린 티소믈리에 & 가드너",
    tags = listOf("오감 힐링", "식물 테라피", "허브 블렌딩"),
  ),

  // LIFE
  ServiceProgramItem(
    id = "p_life_1",
    title = "88 소셜 살롱 & 북클럽 티타임",
    category = ServiceCategoryType.LIFE,
    targetAudience = "품격 있는 대화와 지적 교류로 취향과 관심사를 함께 나눌 친구를 만나고 싶은 분",
    duration = "90분 / 주 1회",
    summary = "철학, 여행, 문화 예술 명작을 테마로 88 웰니스 라운지에서 커피와 차를 곁들인 나눔",
    instructor = "한동욱 인문학 살롱 호스트",
    tags = listOf("웰니스 친구", "지적 대화", "소셜 네트워크"),
  ),
  ServiceProgramItem(
    id = "p_life_2",
    title = "지중해식 웰니스 장수 다이닝 워크숍",
    category = ServiceCategoryType.LIFE,
    targetAudience = "건강하고 맛있는 항염·항산화 식단을 집에서도 즐기고 싶은 분",
    duration = "60분 / 월 2회",
    summary = "올리브유, 통곡물, 신선한 해산물과 제철 채소를 활용한 영양 셰프의 시식 및 식단 코칭",
    instructor = "임채원 임상영양사",
    tags = listOf("항염 식단", "지중해식", "영양 솔루션"),
  ),

  // CARE
  ServiceProgramItem(
    id = "p_care_1",
    title = "척추 감압 체형 교정 & 림프 순환 케어",
    category = ServiceCategoryType.CARE,
    targetAudience = "허리 디스크 부담, 목·어깨 결림, 만성 다리 부종이 있는 회원",
    duration = "50분 / 1:1 예약제",
    summary = "전문 물리치료사의 섬세한 수기 도수 이완과 온열 감압 기기로 림프 독소와 관절 압박을 해소합니다.",
    instructor = "강민석 수석 물리치료사",
    tags = listOf("1:1 프라이빗", "척추 교정", "림프 부종 완화"),
  ),
  ServiceProgramItem(
    id = "p_care_2",
    title = "적외선 편백 스파 & 바이오 생체 리포트",
    category = ServiceCategoryType.CARE,
    targetAudience = "체온 면역력을 올리고 정기적으로 혈관·체성분 상태를 점검받고 싶은 분",
    duration = "45분 / 상시 예약",
    summary = "원적외선 히노끼 전신 온열 스파 후 자율신경 검사(HRV)와 인바디 정밀 측정을 통해 88 헬스 지표를 발행합니다.",
    instructor = "송지현 전담 웰니스 간호사",
    tags = listOf("체온 면역", "정밀 리포트", "편백 스파"),
  ),
)

val CenterFacilities = listOf(
  FacilitySpaceItem(
    id = "f_1",
    name = "88 웰니스 라운지 (88 Lounge)",
    category = ServiceCategoryType.LIFE,
    icon = Icons.Default.Grass,
    feature = "고급스럽고 평온한 딥그린 인테리어와 자연 채광 통창",
    description = "자연 친화적이고 아늑한 딥그린 티 살롱과 편안한 웰니스 북 서가",
  ),
  FacilitySpaceItem(
    id = "f_2",
    name = "스마트 바이오 짐 (Smart Bio-Gym)",
    category = ServiceCategoryType.BODY,
    icon = Icons.Default.FitnessCenter,
    feature = "관절 충격 없는 공압식 근력 기구 및 에어 트레드밀",
    description = "근감소증 예방을 위해 개인별 근력 수치에 자동 매칭되는 인체공학적 피트니스 공간",
  ),
  FacilitySpaceItem(
    id = "f_3",
    name = "브레인 & 마인드 챔버 (Mind Chamber)",
    category = ServiceCategoryType.MIND,
    icon = Icons.Default.Psychology,
    feature = "차음 방음 설계, 편백나무 향, 싱잉볼 사운드 시스템",
    description = "잡념을 내려놓고 뇌신경을 쉬게 하는 명상 및 인지 인텔리전스 훈련실",
  ),
  FacilitySpaceItem(
    id = "f_4",
    name = "88 아카데미 살롱 룸 (Salon Room)",
    category = ServiceCategoryType.CLASS,
    icon = Icons.Default.MenuBook,
    feature = "대형 터치스크린과 편안한 원목 테이블 세팅",
    description = "스마트폰 강좌, 예술, 인문학 세미나가 열리는 지적 배움과 교류의 장",
  ),
  FacilitySpaceItem(
    id = "f_5",
    name = "하이드로 & 히노끼 스파 (Hydro Spa)",
    category = ServiceCategoryType.CARE,
    icon = Icons.Default.Bathtub,
    feature = "편백 원목 원적외선 챔버 및 1:1 감압 룸",
    description = "운동 후 뭉친 근육을 부드럽게 풀고 면역 체온을 유지하는 럭셔리 회복 공간",
  ),
)
