package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
  primary = MastersGreenPrimary,
  onPrimary = Color.White,
  primaryContainer = MastersGreenLight,
  onPrimaryContainer = MastersGreenDark,
  secondary = AugustaGold,
  onSecondary = Color.Black,
  secondaryContainer = AugustaGoldLight,
  onSecondaryContainer = AugustaGoldDark,
  tertiary = MastersGreenMedium,
  onTertiary = Color.White,
  background = SurfaceBackground,
  onBackground = TextDark,
  surface = SurfaceCard,
  onSurface = TextDark,
  surfaceVariant = MastersGreenSubtle,
  onSurfaceVariant = TextMedium,
  outline = BorderLight,
)

private val DarkColorScheme = darkColorScheme(
  primary = MastersGreenMedium,
  onPrimary = Color.White,
  primaryContainer = MastersGreenDark,
  onPrimaryContainer = MastersGreenLight,
  secondary = AugustaGold,
  onSecondary = Color.Black,
  secondaryContainer = AugustaGoldDark,
  onSecondaryContainer = AugustaGoldLight,
  tertiary = MastersGreenPrimary,
  onTertiary = Color.White,
  background = Color(0xFF0A140F),
  onBackground = Color(0xFFE4EDE7),
  surface = Color(0xFF112019),
  onSurface = Color(0xFFE4EDE7),
  surfaceVariant = Color(0xFF192F25),
  onSurfaceVariant = Color(0xFFB5CAC0),
  outline = Color(0xFF2C4538),
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // For 88 WELLNESS, we preserve the signature Augusta Masters Green
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content,
  )
}
