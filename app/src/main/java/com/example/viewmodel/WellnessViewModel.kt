package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.model.AllServicePrograms
import com.example.model.CheckQuestion
import com.example.model.CheckResult
import com.example.model.DailyMission
import com.example.model.LifestyleDomains
import com.example.model.LongevityPlan
import com.example.model.PhysicalDomains
import com.example.model.SampleQuestions
import com.example.model.ServiceCategoryType
import com.example.model.ServiceProgramItem
import com.example.model.UserProfile
import com.example.model.WeakDomainInfo
import com.example.model.WeeklyScheduleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class ScreenTab(val title: String) {
  HOME("소개 & 홈"),
  CHECK("MY 88 CHECK"),
  SERVICES("복합문화공간"),
  DAILY("데일리 88 실천"),
}

data class WellnessUiState(
  val currentTab: ScreenTab = ScreenTab.HOME,
  // Auth State
  val currentUser: UserProfile? = null,
  val showLoginDialog: Boolean = false,
  val showSignUpDialog: Boolean = false,
  val showCompanyIntroDialog: Boolean = false,
  val showPrivacyPolicyDialog: Boolean = false,
  val showAuthGateDialog: Boolean = false,
  val showSideMenuDrawer: Boolean = false,
  val hasPendingCheckResultAfterAuth: Boolean = false,

  // 88 Check State
  val questions: List<CheckQuestion> = SampleQuestions,
  val currentQuestionIndex: Int = 0,
  val answers: Map<Int, Int> = mapOf(
    1 to 4, // Strength: 80%
    2 to 4, // Mobility: 80%
    3 to 4, // Balance: 80%
    4 to 4, // Endurance: 80%
    5 to 2, // Recover: 40%
    6 to 2, // Connect: 40%
    7 to 4, // Nourish: 80%
    8 to 4, // Learn: 80%
  ),
  val isCheckCompleted: Boolean = false,
  val checkResult: CheckResult? = null,
  val isEvaluating: Boolean = false,

  // Services State
  val selectedServiceCategory: ServiceCategoryType? = null,
  val selectedProgramForBooking: ServiceProgramItem? = null,
  val bookingSuccessDialog: Boolean = false,

  // Daily Missions
  val dailyMissions: List<DailyMission> = listOf(
    DailyMission("m1", "MOVE", "활동", "하루 7,000보 활기차게 걷기", "근감소 예방과 심폐 활력을 위한 활기찬 발걸음", PhysicalDomains[0].icon, true),
    DailyMission("m2", "MIND", "마음챙김", "5분 깊은 복식호흡 & 감사 명상", "자율신경 밸런스와 뇌 피로 회복 시간", LifestyleDomains[1].icon, true),
    DailyMission("m3", "NOURISH", "영양", "끼니마다 단백질(계란·생선·두부) 챙기기", "근육 합성을 촉진하는 필수 아미노산 공급", LifestyleDomains[2].icon, true),
    DailyMission("m4", "RECOVER", "수면·휴식", "오후 따뜻한 차 한 잔과 20분 릴랙스", "체온 면역력 유지 및 긴장된 관절 이완", LifestyleDomains[3].icon, false),
    DailyMission("m5", "CONNECT", "소셜 연결", "88 살롱 친구 또는 지인에게 안부 전하기", "사회적 유대감으로 뇌 활력과 웃음 채우기", LifestyleDomains[4].icon, false),
    DailyMission("m6", "LEARN", "배움", "스마트폰 새 기능 또는 신문 칼럼 읽기", "매일 새로운 시냅스를 자극하는 두뇌 배움", LifestyleDomains[5].icon, true),
    DailyMission("m7", "PURPOSE", "목적", "오늘 완수한 작은 성취 1가지 기록하기", "삶의 의미와 보람찬 하루의 매듭짓기", LifestyleDomains[6].icon, false),
    DailyMission("m8", "ENJOY", "즐거움", "좋아하는 음악 듣기나 정원 꽃 감상하기", "도파민과 행복 호르몬을 깨우는 나만의 쉼", LifestyleDomains[7].icon, true),
  ),
  val mascotMessage: String = "오늘도 팔팔하게! 좋아하는 삶을 더 오래도록 함께 누려요.",
)

class WellnessViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(WellnessUiState())
  val uiState: StateFlow<WellnessUiState> = _uiState.asStateFlow()

  // In-memory mock registered user database
  private val registeredUsers = mutableMapOf<String, Pair<String, UserProfile>>(
    "nexthealthcare8@gmail.com" to Pair(
      "123456",
      UserProfile(
        name = "김재원",
        email = "nexthealthcare8@gmail.com",
        phone = "010-8254-8883",
        joinDate = "2026.09.30",
      )
    )
  )

  init {
    // Pre-calculate sample result for when user logs in or completes
    calculateResultFromAnswers(_uiState.value.answers)
  }

  // --- Auth Controls ---
  fun openSideMenu() = _uiState.update { it.copy(showSideMenuDrawer = true) }
  fun closeSideMenu() = _uiState.update { it.copy(showSideMenuDrawer = false) }

  fun openLoginDialog() = _uiState.update { it.copy(showLoginDialog = true, showSignUpDialog = false) }
  fun closeLoginDialog() = _uiState.update { it.copy(showLoginDialog = false) }

  fun openSignUpDialog() = _uiState.update { it.copy(showSignUpDialog = true, showLoginDialog = false) }
  fun closeSignUpDialog() = _uiState.update { it.copy(showSignUpDialog = false) }

  fun openCompanyIntroDialog() = _uiState.update { it.copy(showCompanyIntroDialog = true) }
  fun closeCompanyIntroDialog() = _uiState.update { it.copy(showCompanyIntroDialog = false) }

  fun openPrivacyPolicyDialog() = _uiState.update { it.copy(showPrivacyPolicyDialog = true) }
  fun closePrivacyPolicyDialog() = _uiState.update { it.copy(showPrivacyPolicyDialog = false) }

  fun openAuthGateDialog() = _uiState.update { it.copy(showAuthGateDialog = true) }
  fun closeAuthGateDialog() = _uiState.update { it.copy(showAuthGateDialog = false) }

  fun login(emailInput: String, passwordInput: String): Boolean {
    val emailKey = emailInput.lowercase().trim()
    val record = registeredUsers[emailKey]
    val userProfile = if (record != null) {
      if (record.first == passwordInput) record.second else return false
    } else {
      // Allow flexible seamless demo login for any new credentials
      val profile = UserProfile(
        name = emailInput.substringBefore("@").replaceFirstChar { it.uppercase() },
        email = emailInput,
        phone = "010-8254-8883",
        joinDate = "2026.09.30",
      )
      registeredUsers[emailKey] = Pair(passwordInput, profile)
      profile
    }

    _uiState.update {
      it.copy(
        currentUser = userProfile,
        showLoginDialog = false,
        showAuthGateDialog = false,
        isCheckCompleted = true,
        hasPendingCheckResultAfterAuth = false,
      )
    }
    return true
  }

  fun signUp(name: String, email: String, passwordInput: String, phone: String): Boolean {
    val emailKey = email.lowercase().trim()
    val profile = UserProfile(
      name = name,
      email = email,
      phone = phone,
      joinDate = "2026.09.30",
    )
    registeredUsers[emailKey] = Pair(passwordInput, profile)

    _uiState.update {
      it.copy(
        currentUser = profile,
        showSignUpDialog = false,
        showAuthGateDialog = false,
        isCheckCompleted = true,
        hasPendingCheckResultAfterAuth = false,
      )
    }
    return true
  }

  fun logout() {
    _uiState.update {
      it.copy(
        currentUser = null,
        showSideMenuDrawer = false,
      )
    }
  }

  fun deleteAccount() {
    val currentEmail = _uiState.value.currentUser?.email?.lowercase()
    if (currentEmail != null) {
      registeredUsers.remove(currentEmail)
    }
    _uiState.update {
      it.copy(
        currentUser = null,
        isCheckCompleted = false,
        hasPendingCheckResultAfterAuth = false,
        showSideMenuDrawer = false,
      )
    }
  }

  fun setTab(tab: ScreenTab) {
    _uiState.update { it.copy(currentTab = tab) }
  }

  fun selectServiceCategory(category: ServiceCategoryType?) {
    _uiState.update { it.copy(selectedServiceCategory = category) }
  }

  fun startNewCheck() {
    _uiState.update {
      it.copy(
        isCheckCompleted = false,
        currentQuestionIndex = 0,
        isEvaluating = true,
        hasPendingCheckResultAfterAuth = false,
      )
    }
  }

  fun answerQuestion(questionId: Int, scoreValue: Int) {
    val updatedAnswers = _uiState.value.answers.toMutableMap()
    updatedAnswers[questionId] = scoreValue

    val nextIndex = _uiState.value.currentQuestionIndex + 1
    val isLast = nextIndex >= _uiState.value.questions.size

    _uiState.update {
      it.copy(
        answers = updatedAnswers,
        currentQuestionIndex = if (isLast) it.currentQuestionIndex else nextIndex,
      )
    }

    if (isLast) {
      calculateResultFromAnswers(updatedAnswers)
      if (_uiState.value.currentUser == null) {
        // Unauthenticated user: Completed questionnaire, but requires login/signup to view result
        _uiState.update {
          it.copy(
            isEvaluating = false,
            hasPendingCheckResultAfterAuth = true,
            showAuthGateDialog = true,
          )
        }
      } else {
        completeAssessment(updatedAnswers)
      }
    }
  }

  fun previousQuestion() {
    if (_uiState.value.currentQuestionIndex > 0) {
      _uiState.update { it.copy(currentQuestionIndex = it.currentQuestionIndex - 1) }
    }
  }

  fun completeAssessment(answers: Map<Int, Int>) {
    calculateResultFromAnswers(answers)
    _uiState.update {
      it.copy(
        isCheckCompleted = true,
        isEvaluating = false,
        hasPendingCheckResultAfterAuth = false,
      )
    }
  }

  private fun calculateResultFromAnswers(answers: Map<Int, Int>) {
    val questions = _uiState.value.questions
    val scoreMap = mutableMapOf<String, Int>()

    questions.forEach { q ->
      val raw = answers[q.id] ?: 3
      val scaled = raw * 20 // 1..5 -> 20..100
      scoreMap[q.domainKey] = scaled
    }

    val overallScore = if (scoreMap.isNotEmpty()) scoreMap.values.average().toInt() else 75

    // Find the 2 lowest scoring domains
    val sortedAsc = scoreMap.toList().sortedBy { it.second }
    val weakKey1 = sortedAsc.getOrNull(0)?.first ?: "recover"
    val weakKey2 = sortedAsc.getOrNull(1)?.first ?: "connect"

    val weakList = listOf(weakKey1, weakKey2).map { key ->
      buildWeakDomainInfo(key, scoreMap[key] ?: 40)
    }

    // Build personalized Longevity Plan
    val plan = buildPersonalizedPlan(weakList)

    val result = CheckResult(
      scores = scoreMap,
      overallScore = overallScore,
      weakDomains = weakList,
      longevityPlan = plan,
    )

    _uiState.update { it.copy(checkResult = result) }
  }

  private fun buildWeakDomainInfo(key: String, score: Int): WeakDomainInfo {
    return when (key) {
      "recover" -> WeakDomainInfo(
        key = "recover",
        nameEn = "RECOVER",
        nameKo = "수면 & 신체 회복",
        score = score,
        advice = "숙면과 림프 배출 부족으로 피로 물질이 누적되어 있습니다. 저녁 온열 릴랙스와 편백 스파 케어가 필요합니다.",
        recommendedService = ServiceCategoryType.CARE,
      )
      "connect" -> WeakDomainInfo(
        key = "connect",
        nameEn = "CONNECT",
        nameKo = "사회적 연결 & 소셜",
        score = score,
        advice = "친밀한 대화와 소셜 교류 빈도가 낮아 활력이 정체되어 있습니다. 88 소셜 살롱 티타임과 북클럽 참여를 권장합니다.",
        recommendedService = ServiceCategoryType.LIFE,
      )
      "strength" -> WeakDomainInfo(
        key = "strength",
        nameEn = "STRENGTH",
        nameKo = "신체 근력 & 코어",
        score = score,
        advice = "하체와 척추 지지 근육량이 부족합니다. 관절에 무리 없는 공압식 88 피트니스와 코어 트레이닝이 필요합니다.",
        recommendedService = ServiceCategoryType.BODY,
      )
      "mobility" -> WeakDomainInfo(
        key = "mobility",
        nameEn = "MOBILITY",
        nameKo = "관절 가동성 & 유연성",
        score = score,
        advice = "어깨와 고관절의 회전 가동 범위가 좁아져 있습니다. 1:1 체형 스트레칭과 필라테스를 추천합니다.",
        recommendedService = ServiceCategoryType.BODY,
      )
      "balance" -> WeakDomainInfo(
        key = "balance",
        nameEn = "BALANCE",
        nameKo = "균형감각 & 낙상 예방",
        score = score,
        advice = "전정 밸런스와 발목 지지력이 약화되어 보행 안정성 관리가 시급합니다. 에어 밸런스 트레이닝을 권장합니다.",
        recommendedService = ServiceCategoryType.BODY,
      )
      "endurance" -> WeakDomainInfo(
        key = "endurance",
        nameEn = "ENDURANCE",
        nameKo = "심폐 지구력",
        score = score,
        advice = "장시간 활동 시 피로를 빠르게 느낍니다. 무중력 보행 파워 워킹으로 심폐 활력을 높여야 합니다.",
        recommendedService = ServiceCategoryType.BODY,
      )
      "nourish" -> WeakDomainInfo(
        key = "nourish",
        nameEn = "NOURISH",
        nameKo = "영양 식단 & 단백질",
        score = score,
        advice = "근감소증 방지를 위한 고단백 영양 섭취와 항산화 식단 습관 개선이 필요합니다. 지중해식 다이닝 워크숍을 추천합니다.",
        recommendedService = ServiceCategoryType.LIFE,
      )
      else -> WeakDomainInfo(
        key = "learn",
        nameEn = "LEARN",
        nameKo = "배움 & 뇌 자극",
        score = score,
        advice = "새로운 자극과 호기심 충전이 필요합니다. 디지털 스마트폰 교실 및 인문학 살롱 강좌를 권장합니다.",
        recommendedService = ServiceCategoryType.CLASS,
      )
    }
  }

  private fun buildPersonalizedPlan(weakList: List<WeakDomainInfo>): LongevityPlan {
    val weakNamesEn = weakList.joinToString(" & ") { it.nameEn }
    val title = "회원님 맞춤 [$weakNamesEn] 집중 강화 88 LONGEVITY PLAN"

    val weeklyRoutine = mutableListOf<WeeklyScheduleItem>()

    // Day 1: Tuesday Recover & Care
    weeklyRoutine.add(
      WeeklyScheduleItem(
        day = "화요일 (10:30)",
        time = "50분",
        title = "적외선 편백 스파 & 림프 순환 케어",
        category = ServiceCategoryType.CARE,
        place = "하이드로 & 히노끼 스파룸",
      )
    )

    // Day 2: Thursday Social & Mind/Life
    weeklyRoutine.add(
      WeeklyScheduleItem(
        day = "목요일 (14:00)",
        time = "60분",
        title = "88 소셜 살롱 & 웰니스 티타임",
        category = ServiceCategoryType.LIFE,
        place = "88 웰니스 라운지",
      )
    )

    // Day 3: Saturday Vitality Move
    weeklyRoutine.add(
      WeeklyScheduleItem(
        day = "토요일 (11:00)",
        time = "45분",
        title = "낙상 ZERO 코어 밸런스 운동",
        category = ServiceCategoryType.BODY,
        place = "스마트 바이오 짐",
      )
    )

    val recommendedClasses = listOf(
      "싱잉볼 힐링 사운드 & 깊은 숙면 호흡 테라피",
      "스마트 라이프 & 소셜 커뮤니티 클럽",
      "항염·항산화 지중해식 건강식단 클래스",
    )

    val dailyMissions = listOf(
      "취침 1시간 전 스마트폰 멀리하고 따뜻한 허브티 마시기",
      "매일 친한 지인 1명에게 기분 좋은 안부 카톡이나 전화하기",
      "낮 시간 햇살 받으며 30분 기분 좋게 산책하기",
    )

    return LongevityPlan(
      title = title,
      weeklyRoutine = weeklyRoutine,
      recommendedClasses = recommendedClasses,
      dailyMissions = dailyMissions,
    )
  }

  fun toggleDailyMission(missionId: String) {
    _uiState.update { state ->
      val updatedList = state.dailyMissions.map { m ->
        if (m.id == missionId) m.copy(isCompleted = !m.isCompleted) else m
      }
      val completedCount = updatedList.count { it.isCompleted }
      val msg = when {
        completedCount == 8 -> "대단해요! 오늘 8가지 88 실천을 모두 완료하셨습니다! 완벽한 팔팔 데이!"
        completedCount >= 5 -> "활력이 가득 차오르고 있어요! 터틀88이 힘차게 응원합니다."
        else -> "오늘도 팔팔하게! 좋아하는 삶을 더 오래도록 함께 누려요."
      }
      state.copy(dailyMissions = updatedList, mascotMessage = msg)
    }
  }

  fun openBookingDialog(program: ServiceProgramItem) {
    _uiState.update { it.copy(selectedProgramForBooking = program) }
  }

  fun dismissBookingDialog() {
    _uiState.update { it.copy(selectedProgramForBooking = null) }
  }

  fun confirmBooking() {
    _uiState.update {
      it.copy(
        selectedProgramForBooking = null,
        bookingSuccessDialog = true,
      )
    }
  }

  fun dismissSuccessDialog() {
    _uiState.update { it.copy(bookingSuccessDialog = false) }
  }
}
