package com.georescue.victim.presentation.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.georescue.victim.R
import com.georescue.victim.domain.models.Incident
import com.georescue.victim.domain.models.IncidentStatus
import com.georescue.victim.presentation.theme.OnSurface
import com.georescue.victim.presentation.theme.OnSurfaceVariant
import com.georescue.victim.presentation.theme.PrimaryContainer
import com.georescue.victim.presentation.theme.SecondaryContainer
import com.georescue.victim.presentation.theme.SurfaceContainerHigh
import com.georescue.victim.presentation.theme.SurfaceContainerHighest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RescueTrackingBottomSheet(incident: Incident) {
    val status = incident.status
    
    // Status color overrides
    val activeColor = PrimaryContainer // Red

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(SurfaceContainerHigh)
            .padding(top = 12.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Header: Rescue Active + ETA
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⚠️",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "RESCUE ACTIVE",
                        style = MaterialTheme.typography.labelLarge,
                        color = activeColor
                    )
                }
                Spacer(Modifier.height(4.dp))
                val responder = incident.assignedResponderId
                Text(
                    text = if (responder != null) "$responder is 5 mins away" else "Locating nearest unit...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurface
                )
            }
            
            // ETA Box
            if (incident.assignedResponderId != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(SurfaceContainerHighest)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "05",
                            style = MaterialTheme.typography.bodyLarge,
                            color = OnSurface
                        )
                        Text(
                            text = "MIN",
                            style = MaterialTheme.typography.labelMedium,
                            color = activeColor
                        )
                    }
                }
            }
        }

        // Timeline Stepper
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            CustomTimelineStep(
                label = "SIGNAL SENT",
                subtext = formatTimestamp(incident.createdAt),
                isComplete = true,
                isActive = false
            )
            TimelineConnector()
            CustomTimelineStep(
                label = "AUTHORITY NOTIFIED",
                subtext = formatTimestamp(incident.createdAt),
                isComplete = status.ordinal >= IncidentStatus.ASSIGNED.ordinal,
                isActive = status == IncidentStatus.CREATED
            )
            TimelineConnector()
            CustomTimelineStep(
                label = "RESPONDER ASSIGNED",
                subtext = if (incident.assignedResponderId != null) "${incident.assignedResponderId} - Helicopter Airborne" else "Pending",
                isComplete = status.ordinal >= IncidentStatus.RESPONDING.ordinal,
                isActive = status == IncidentStatus.ASSIGNED
            )
            TimelineConnector()
            CustomTimelineStep(
                label = "RESCUE ARRIVAL",
                subtext = "Pending",
                isComplete = status == IncidentStatus.RESOLVED,
                isActive = status == IncidentStatus.RESPONDING
            )
        }

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SurfaceContainerHighest,
                    contentColor = OnSurface
                )
            ) {
                Text(
                    text = "💬 MSG UNIT",
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Button(
                onClick = { },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SecondaryContainer, // Warning Orange
                    contentColor = Color(0xFF542600)
                )
            ) {
                Text(
                    text = "🔊 SOUND ALARM",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
private fun CustomTimelineStep(
    label: String,
    subtext: String,
    isComplete: Boolean,
    isActive: Boolean
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isActive) {
                // Active: Pulsing red ring with dot
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(PrimaryContainer.copy(alpha = alpha), CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(PrimaryContainer, CircleShape)
                )
            } else if (isComplete) {
                // Complete: Checkmark in circle
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(SurfaceContainerHighest, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "✓", style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
                }
            } else {
                // Pending: Hollow circle
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .border(2.dp, SurfaceContainerHighest, CircleShape)
                )
            }
        }

        Spacer(Modifier.width(16.dp))

        // Text
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isActive || isComplete) OnSurface else OnSurfaceVariant
            )
            Text(
                text = subtext,
                style = MaterialTheme.typography.labelMedium,
                color = OnSurfaceVariant
            )
        }
    }
}

@Composable
private fun TimelineConnector() {
    Box(
        modifier = Modifier
            .padding(start = 11.dp, top = 4.dp, bottom = 4.dp)
            .width(2.dp)
            .height(24.dp)
            .background(SurfaceContainerHighest)
    )
}

private fun formatTimestamp(timestamp: Any?): String {
    if (timestamp == null) return ""
    val date: Date = when (timestamp) {
        is com.google.firebase.Timestamp -> timestamp.toDate()
        else -> return ""
    }
    return SimpleDateFormat("HH:mm z", Locale.getDefault()).format(date)
}
