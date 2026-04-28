package com.georescue.victim.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ─── Design Tokens (mirroring Stitch project exactly) ────────────────────────

// Backgrounds & Surfaces
val Background       = Color(0xFF131313)
val Surface          = Color(0xFF201F1F)
val SurfaceDim       = Color(0xFF131313)
val SurfaceBright    = Color(0xFF393939)
val SurfaceContainerLow     = Color(0xFF1C1B1B)
val SurfaceContainerHigh    = Color(0xFF2A2A2A)
val SurfaceContainerHighest = Color(0xFF353534)

// Primary — Emergency Red
val Primary              = Color(0xFFFFB3AC)
val OnPrimary            = Color(0xFF680008)
val PrimaryContainer     = Color(0xFFD32F2F)
val OnPrimaryContainer   = Color(0xFFFFF2F0)

// Secondary — Warning Orange
val Secondary            = Color(0xFFFFB786)
val OnSecondary          = Color(0xFF502400)
val SecondaryContainer   = Color(0xFFF27A00)
val OnSecondaryContainer = Color(0xFF542600)

// Tertiary — Safe Green
val Tertiary             = Color(0xFF82DB7E)
val OnTertiary           = Color(0xFF00390A)
val TertiaryContainer    = Color(0xFF298030)
val OnTertiaryContainer  = Color(0xFFDAFFD1)

// Error
val Error              = Color(0xFFFFB4AB)
val OnError            = Color(0xFF690005)
val ErrorContainer     = Color(0xFF93000A)
val OnErrorContainer   = Color(0xFFFFDAD6)

// Text & Borders
val OnSurface          = Color(0xFFE5E2E1)
val OnSurfaceVariant   = Color(0xFFE4BEBA)
val Outline            = Color(0xFFAB8985)
val OutlineVariant     = Color(0xFF5B403D)

// ─── Color Scheme ────────────────────────────────────────────────────────────

private val DarkColorScheme = darkColorScheme(
    primary              = Primary,
    onPrimary            = OnPrimary,
    primaryContainer     = PrimaryContainer,
    onPrimaryContainer   = OnPrimaryContainer,
    secondary            = Secondary,
    onSecondary          = OnSecondary,
    secondaryContainer   = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary             = Tertiary,
    onTertiary           = OnTertiary,
    tertiaryContainer    = TertiaryContainer,
    onTertiaryContainer  = OnTertiaryContainer,
    error                = Error,
    onError              = OnError,
    errorContainer       = ErrorContainer,
    onErrorContainer     = OnErrorContainer,
    background           = Background,
    onBackground         = OnSurface,
    surface              = Surface,
    onSurface            = OnSurface,
    onSurfaceVariant     = OnSurfaceVariant,
    outline              = Outline,
    outlineVariant       = OutlineVariant,
    surfaceDim           = SurfaceDim,
    surfaceBright        = SurfaceBright,
    surfaceContainerLow  = SurfaceContainerLow,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest
)

// ─── Theme Composable ─────────────────────────────────────────────────────────

/**
 * Root theme wrapper for the entire GeoRescue AI application.
 * Always dark mode — per Stitch design system spec for night-vision preservation
 * and emergency scenario legibility.
 */
@Composable
fun GeoRescueTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography  = GeoRescueTypography,
        content     = content
    )
}
