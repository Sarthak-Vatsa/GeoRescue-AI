package com.georescue.victim.presentation.screens

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.georescue.victim.R
import com.georescue.victim.domain.usecases.FailsafeTimer
import com.georescue.victim.presentation.IncidentViewModel
import com.georescue.victim.presentation.theme.Background
import com.georescue.victim.presentation.theme.OnSurface
import com.georescue.victim.presentation.theme.OnSurfaceVariant
import com.georescue.victim.presentation.theme.PrimaryContainer
import com.georescue.victim.presentation.theme.SurfaceContainerHigh
import com.georescue.victim.presentation.theme.TertiaryContainer

/**
 * SOS Countdown screen — shown when inactivity is detected or when the user
 * manually initiates an SOS flow.
 *
 * Features:
 *  - Animated circular countdown ring driven by [FailsafeTimer.remainingTime]
 *  - Intensifying haptic feedback (light → medium → heavy) as timer nears zero
 *  - "I'M SAFE" cancels the timer and returns home
 *  - "SEND SOS NOW" triggers signal immediately and waits for reactive navigation
 *    to [RescueTrackingScreen] via [GeoRescueUiState.RescueActive]
 */
@Composable
fun SOSCountdownScreen(
    viewModel: IncidentViewModel,
    onSafe: () -> Unit,
    onSOSSent: () -> Unit
) {
    val remaining by viewModel.failsafeRemainingTime.collectAsStateWithLifecycle()
    val context   = LocalContext.current

    // ── Haptic feedback (escalating intensity as timer decrements) ───────────
    val vibrator = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (context.getSystemService(VibratorManager::class.java))?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Vibrator::class.java)
        }
    }

    LaunchedEffect(remaining) {
        val amplitude = when {
            remaining <= 5  -> VibrationEffect.DEFAULT_AMPLITUDE  // heavy
            remaining <= 10 -> 128                                 // medium
            remaining <= 20 -> 64                                  // light
            else            -> return@LaunchedEffect               // silent
        }
        vibrator?.vibrate(
            VibrationEffect.createOneShot(80L, amplitude)
        )
    }

    // ── Pulsing ring border for urgency ──────────────────────────────────────
    val infiniteTransition = rememberInfiniteTransition(label = "sos_pulse")
    val borderAlpha by infiniteTransition.animateFloat(
        initialValue  = 0.4f,
        targetValue   = 1f,
        animationSpec = infiniteRepeatable(
            animation  = tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "border_alpha"
    )

    val progress = remaining.toFloat() / FailsafeTimer.COUNTDOWN_SECONDS.toFloat()
    val ringColor = when {
        remaining <= 5  -> PrimaryContainer
        remaining <= 15 -> Color(0xFFF27A00) // orange
        else            -> Color(0xFFFFB786)  // light orange
    }

    // ── Screen ───────────────────────────────────────────────────────────────
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier            = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text      = stringResource(R.string.sos_header),
                    style     = MaterialTheme.typography.headlineLarge,
                    color     = OnSurface,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text      = stringResource(R.string.sos_subtitle),
                    style     = MaterialTheme.typography.bodyMedium,
                    color     = OnSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }

            // ── Countdown ring ───────────────────────────────────────────────
            Box(contentAlignment = Alignment.Center) {
                // Track ring (background)
                CircularProgressIndicator(
                    progress         = 1f,
                    modifier         = Modifier.size(220.dp),
                    strokeWidth      = 12.dp,
                    color            = SurfaceContainerHigh,
                    strokeCap        = StrokeCap.Round
                )
                // Progress ring (countdown)
                CircularProgressIndicator(
                    progress         = progress,
                    modifier         = Modifier.size(220.dp),
                    strokeWidth      = 12.dp,
                    color            = ringColor,
                    strokeCap        = StrokeCap.Round
                )
                // Centre: countdown number + label
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text  = remaining.toString(),
                        style = MaterialTheme.typography.displayLarge,
                        color = ringColor
                    )
                    Text(
                        text  = stringResource(R.string.sos_seconds_remaining),
                        style = MaterialTheme.typography.labelMedium,
                        color = OnSurfaceVariant
                    )
                }
            }

            // ── Action Buttons ───────────────────────────────────────────────
            Column(
                modifier            = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // I'M SAFE
                Button(
                    onClick  = onSafe,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape  = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TertiaryContainer,
                        contentColor   = Color.White
                    )
                ) {
                    Text(
                        text  = "✅  ${stringResource(R.string.sos_safe_btn)}",
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                // SEND SOS NOW
                Button(
                    onClick  = onSOSSent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape  = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryContainer,
                        contentColor   = Color.White
                    )
                ) {
                    Text(
                        text  = "🚨  ${stringResource(R.string.sos_send_btn)}",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}
