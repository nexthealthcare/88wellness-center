package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.AppHeader
import com.example.ui.components.AuthGateDialog
import com.example.ui.components.BookingConfirmationDialog
import com.example.ui.components.CompanyIntroDialog
import com.example.ui.components.LoginDialog
import com.example.ui.components.MascotStoryDialog
import com.example.ui.components.PrivacyPolicyDialog
import com.example.ui.components.SideMenuContent
import com.example.ui.components.SignUpDialog
import com.example.ui.components.SuccessDialog
import com.example.ui.screens.Daily88Screen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.My88CheckScreen
import com.example.ui.screens.ServicesScreen
import com.example.ui.theme.AugustaGold
import com.example.ui.theme.MastersGreenDark
import com.example.ui.theme.MastersGreenLight
import com.example.ui.theme.MastersGreenPrimary
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.SurfaceBackground
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import com.example.viewmodel.ScreenTab
import com.example.viewmodel.WellnessViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        WellnessApp()
      }
    }
  }
}

@Composable
fun WellnessApp(viewModel: WellnessViewModel = viewModel()) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  var showMascotDialog by remember { mutableStateOf(false) }

  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val coroutineScope = rememberCoroutineScope()

  // Back button handling
  BackHandler(enabled = drawerState.isOpen || uiState.currentTab != ScreenTab.HOME || uiState.isEvaluating) {
    when {
      drawerState.isOpen -> coroutineScope.launch { drawerState.close() }
      uiState.isEvaluating -> {
        if (uiState.currentQuestionIndex > 0) {
          viewModel.previousQuestion()
        } else {
          viewModel.setTab(ScreenTab.HOME)
        }
      }
      else -> viewModel.setTab(ScreenTab.HOME)
    }
  }

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      SideMenuContent(
        currentUser = uiState.currentUser,
        onClose = { coroutineScope.launch { drawerState.close() } },
        onOpenLogin = { viewModel.openLoginDialog() },
        onOpenSignUp = { viewModel.openSignUpDialog() },
        onOpenCompanyIntro = { viewModel.openCompanyIntroDialog() },
        onOpenPrivacyPolicy = { viewModel.openPrivacyPolicyDialog() },
        onNavigateToTab = { tab -> viewModel.setTab(tab) },
        onOpenMascotStory = { showMascotDialog = true },
        onLogout = { viewModel.logout() },
        onDeleteAccount = { viewModel.deleteAccount() },
      )
    },
  ) {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      topBar = {
        AppHeader(
          onMenuClick = {
            coroutineScope.launch { drawerState.open() }
          },
        )
      },
      bottomBar = {
        NavigationBar(
          containerColor = Color.White,
          tonalElevation = 6.dp,
          modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("bottom_navigation_bar"),
        ) {
          // 1. Home
          NavigationBarItem(
            selected = uiState.currentTab == ScreenTab.HOME,
            onClick = { viewModel.setTab(ScreenTab.HOME) },
            icon = {
              Icon(
                imageVector = if (uiState.currentTab == ScreenTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                contentDescription = "소개 & 홈",
                modifier = Modifier.size(24.dp)
              )
            },
            label = {
              Text(
                text = "소개 & 홈",
                fontWeight = if (uiState.currentTab == ScreenTab.HOME) FontWeight.Bold else FontWeight.Normal,
                fontSize = 12.sp,
              )
            },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = MastersGreenDark,
              selectedTextColor = MastersGreenDark,
              indicatorColor = MastersGreenLight,
              unselectedIconColor = TextMuted,
              unselectedTextColor = TextMuted,
            ),
            modifier = Modifier.testTag("nav_home")
          )

          // 2. MY 88 CHECK
          NavigationBarItem(
            selected = uiState.currentTab == ScreenTab.CHECK,
            onClick = { viewModel.setTab(ScreenTab.CHECK) },
            icon = {
              Icon(
                imageVector = if (uiState.currentTab == ScreenTab.CHECK) Icons.Filled.Assignment else Icons.Outlined.Assignment,
                contentDescription = "88 진단",
                modifier = Modifier.size(24.dp)
              )
            },
            label = {
              Text(
                text = "88 CHECK",
                fontWeight = if (uiState.currentTab == ScreenTab.CHECK) FontWeight.Bold else FontWeight.Normal,
                fontSize = 12.sp,
              )
            },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = MastersGreenDark,
              selectedTextColor = MastersGreenDark,
              indicatorColor = MastersGreenLight,
              unselectedIconColor = TextMuted,
              unselectedTextColor = TextMuted,
            ),
            modifier = Modifier.testTag("nav_check")
          )

          // 3. Services
          NavigationBarItem(
            selected = uiState.currentTab == ScreenTab.SERVICES,
            onClick = { viewModel.setTab(ScreenTab.SERVICES) },
            icon = {
              Icon(
                imageVector = if (uiState.currentTab == ScreenTab.SERVICES) Icons.Filled.FitnessCenter else Icons.Outlined.FitnessCenter,
                contentDescription = "서비스",
                modifier = Modifier.size(24.dp)
              )
            },
            label = {
              Text(
                text = "복합문화공간",
                fontWeight = if (uiState.currentTab == ScreenTab.SERVICES) FontWeight.Bold else FontWeight.Normal,
                fontSize = 12.sp,
              )
            },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = MastersGreenDark,
              selectedTextColor = MastersGreenDark,
              indicatorColor = MastersGreenLight,
              unselectedIconColor = TextMuted,
              unselectedTextColor = TextMuted,
            ),
            modifier = Modifier.testTag("nav_services")
          )

          // 4. Daily 88
          NavigationBarItem(
            selected = uiState.currentTab == ScreenTab.DAILY,
            onClick = { viewModel.setTab(ScreenTab.DAILY) },
            icon = {
              Icon(
                imageVector = if (uiState.currentTab == ScreenTab.DAILY) Icons.Filled.CheckCircleOutline else Icons.Outlined.CheckCircleOutline,
                contentDescription = "데일리 88",
                modifier = Modifier.size(24.dp)
              )
            },
            label = {
              Text(
                text = "데일리 88",
                fontWeight = if (uiState.currentTab == ScreenTab.DAILY) FontWeight.Bold else FontWeight.Normal,
                fontSize = 12.sp,
              )
            },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = MastersGreenDark,
              selectedTextColor = MastersGreenDark,
              indicatorColor = MastersGreenLight,
              unselectedIconColor = TextMuted,
              unselectedTextColor = TextMuted,
            ),
            modifier = Modifier.testTag("nav_daily")
          )
        }
      }
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
          .background(SurfaceBackground)
      ) {
        when (uiState.currentTab) {
          ScreenTab.HOME -> HomeScreen(
            onNavigateToCheck = { viewModel.setTab(ScreenTab.CHECK) },
            onNavigateToCategory = { category ->
              viewModel.selectServiceCategory(category)
              viewModel.setTab(ScreenTab.SERVICES)
            },
            onOpenMascotDialog = { showMascotDialog = true },
          )

          ScreenTab.CHECK -> My88CheckScreen(
            uiState = uiState,
            onStartNewCheck = { viewModel.startNewCheck() },
            onAnswerQuestion = { qId, score -> viewModel.answerQuestion(qId, score) },
            onPreviousQuestion = { viewModel.previousQuestion() },
            onNavigateToCategory = { category ->
              viewModel.selectServiceCategory(category)
              viewModel.setTab(ScreenTab.SERVICES)
            },
            onOpenSignUp = { viewModel.openSignUpDialog() },
            onOpenLogin = { viewModel.openLoginDialog() },
          )

          ScreenTab.SERVICES -> ServicesScreen(
            selectedCategory = uiState.selectedServiceCategory,
            onSelectCategory = { cat -> viewModel.selectServiceCategory(cat) },
            onBookProgram = { program -> viewModel.openBookingDialog(program) },
          )

          ScreenTab.DAILY -> Daily88Screen(
            uiState = uiState,
            onToggleMission = { missionId -> viewModel.toggleDailyMission(missionId) },
            onOpenMascotDialog = { showMascotDialog = true },
          )
        }
      }
    }
  }

  // --- Auth & Information Modal Dialogs ---

  // 1. Login Dialog
  if (uiState.showLoginDialog) {
    LoginDialog(
      onDismiss = { viewModel.closeLoginDialog() },
      onLogin = { email, pw -> viewModel.login(email, pw) },
      onNavigateToSignUp = { viewModel.openSignUpDialog() },
      onOpenPrivacyPolicy = { viewModel.openPrivacyPolicyDialog() },
    )
  }

  // 2. Sign Up Dialog
  if (uiState.showSignUpDialog) {
    SignUpDialog(
      onDismiss = { viewModel.closeSignUpDialog() },
      onSignUp = { name, email, pw, phone -> viewModel.signUp(name, email, pw, phone) },
      onNavigateToLogin = { viewModel.openLoginDialog() },
      onOpenPrivacyPolicy = { viewModel.openPrivacyPolicyDialog() },
    )
  }

  // 3. Auth Gatekeeper Dialog (Triggered after non-logged-in user completes check)
  if (uiState.showAuthGateDialog) {
    AuthGateDialog(
      onDismiss = { viewModel.closeAuthGateDialog() },
      onNavigateToSignUp = { viewModel.openSignUpDialog() },
      onNavigateToLogin = { viewModel.openLoginDialog() },
    )
  }

  // 4. Company Introduction Dialog
  if (uiState.showCompanyIntroDialog) {
    CompanyIntroDialog(
      onDismiss = { viewModel.closeCompanyIntroDialog() }
    )
  }

  // 5. Privacy Policy Dialog
  if (uiState.showPrivacyPolicyDialog) {
    PrivacyPolicyDialog(
      onDismiss = { viewModel.closePrivacyPolicyDialog() }
    )
  }

  // Booking Modal Dialog
  uiState.selectedProgramForBooking?.let { program ->
    BookingConfirmationDialog(
      program = program,
      onDismiss = { viewModel.dismissBookingDialog() },
      onConfirm = { viewModel.confirmBooking() },
    )
  }

  // Booking Success Dialog
  if (uiState.bookingSuccessDialog) {
    SuccessDialog(
      onDismiss = { viewModel.dismissSuccessDialog() }
    )
  }

  // Mascot Story Modal
  if (showMascotDialog) {
    MascotStoryDialog(
      onDismiss = { showMascotDialog = false }
    )
  }
}
