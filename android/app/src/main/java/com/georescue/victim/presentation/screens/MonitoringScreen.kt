package com.georescue.victim.presentation.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.scale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.georescue.victim.R
import com.georescue.victim.domain.models.RiskSeverity
import com.georescue.victim.domain.models.RiskZone
import com.georescue.victim.presentation.GeoRescueUiState
import com.georescue.victim.presentation.IncidentViewModel
import com.georescue.victim.presentation.theme.Background
import com.georescue.victim.presentation.theme.InterFontFamily
import com.georescue.victim.presentation.theme.OnSurface
import com.georescue.victim.presentation.theme.OnSurfaceVariant
import com.georescue.victim.presentation.theme.PrimaryContainer
import com.georescue.victim.presentation.theme.SecondaryContainer
import com.georescue.victim.presentation.theme.SurfaceContainerHigh
import com.georescue.victim.presentation.theme.SurfaceContainerLow
import com.georescue.victim.presentation.theme.TertiaryContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonitoringScreen(
    viewModel: IncidentViewModel,
    onSOSClick: () -> Unit
) {
    val riskZones by viewModel.riskZones.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userLocation by viewModel.userLocation.collectAsStateWithLifecycle()

    var currentTab by remember { mutableStateOf("MAP") }

    LaunchedEffect(Unit) {
        viewModel.fetchLastLocation()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🛡️",
                            fontSize = 20.sp
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            text = "MONITORING\nSENSORS",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 14.sp,
                                lineHeight = 16.sp
                            ),
                            color = PrimaryContainer
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .background(PrimaryContainer, RoundedCornerShape(4.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "⌖ GPS ACTIVE",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(PrimaryContainer.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("⚡", fontSize = 14.sp)
                    }
                    Spacer(Modifier.width(16.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        bottomBar = {
            BottomNavBar(
                currentTab = currentTab,
                onTabSelected = { currentTab = it },
                onSOSClick = onSOSClick
            )
        },
        floatingActionButton = {
            if (currentTab != "STATUS") {
                androidx.compose.material3.SmallFloatingActionButton(
                    onClick = onSOSClick,
                    containerColor = PrimaryContainer,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Text("SOS", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Background Map (always rendered, but overlaid by tabs if needed)
            MapBackground(riskZones = riskZones, userLocation = userLocation)

            // Tab Content Overlays
            when (currentTab) {
                "MAP" -> {
                    // Nothing overlaying the map
                }
                "STATUS" -> {
                    if (uiState is GeoRescueUiState.RescueActive) {
                        val incident = (uiState as GeoRescueUiState.RescueActive).incident
                        val scaffoldState = androidx.compose.material3.rememberBottomSheetScaffoldState()
                        
                        androidx.compose.material3.BottomSheetScaffold(
                            scaffoldState = scaffoldState,
                            sheetContent = { RescueTrackingBottomSheet(incident = incident) },
                            sheetPeekHeight = 280.dp,
                            containerColor = Color.Transparent,
                            sheetContainerColor = SurfaceContainerHigh
                        ) {
                            StatusTab(riskZones = riskZones)
                        }
                    } else {
                        StatusTab(riskZones = riskZones)
                    }
                }
                "LOGS" -> LogsTab()
            }
        }
    }
}

@Composable
private fun BottomNavBar(
    currentTab: String,
    onTabSelected: (String) -> Unit,
    onSOSClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceContainerLow)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(icon = "🆘", label = "SOS", isSelected = false, onClick = onSOSClick)
        BottomNavItem(icon = "🗺️", label = "MAP", isSelected = currentTab == "MAP", onClick = { onTabSelected("MAP") })
        BottomNavItem(icon = "📡", label = "STATUS", isSelected = currentTab == "STATUS", onClick = { onTabSelected("STATUS") })
        BottomNavItem(icon = "🕒", label = "LOGS", isSelected = currentTab == "LOGS", onClick = { onTabSelected("LOGS") })
    }
}

@Composable
private fun BottomNavItem(icon: String, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(72.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(3.dp)
                    .background(PrimaryContainer)
            )
        } else {
            Spacer(modifier = Modifier.height(3.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = icon, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = if (isSelected) PrimaryContainer else OnSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MapBackground(riskZones: List<RiskZone>, userLocation: LatLng?) {
    val defaultLocation = LatLng(20.5937, 78.9629)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 5f)
    }

    LaunchedEffect(userLocation) {
        if (userLocation != null) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(userLocation, 14f),
                durationMs = 1500
            )
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            isMyLocationEnabled = true,
            mapStyleOptions = darkMapStyle()
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            myLocationButtonEnabled = false
        )
    ) {
        riskZones.forEach { zone ->
            val center = LatLng(zone.center.latitude, zone.center.longitude)
            val (fillColor, strokeColor) = zoneColors(zone.severity)

            Circle(
                center = center,
                radius = zone.radius,
                fillColor = fillColor,
                strokeColor = strokeColor,
                strokeWidth = 3f
            )

            Marker(
                state = MarkerState(position = center),
                title = zone.name,
                snippet = zone.type.replaceFirstChar { it.uppercase() },
                icon = BitmapDescriptorFactory.defaultMarker(
                    when (zone.severity) {
                        RiskSeverity.HIGH -> BitmapDescriptorFactory.HUE_RED
                        RiskSeverity.MEDIUM -> BitmapDescriptorFactory.HUE_ORANGE
                        RiskSeverity.LOW -> BitmapDescriptorFactory.HUE_YELLOW
                    }
                )
            )
        }
    }
}

@Composable
private fun zoneColors(severity: RiskSeverity): Pair<Color, Color> = when (severity) {
    RiskSeverity.HIGH -> Color(0x44D32F2F) to Color(0xFFD32F2F)
    RiskSeverity.MEDIUM -> Color(0x44F27A00) to Color(0xFFF27A00)
    RiskSeverity.LOW -> Color(0x44F9A825) to Color(0xFFF9A825)
}

@Composable
private fun darkMapStyle(): com.google.android.gms.maps.model.MapStyleOptions? {
    val json = """
        [{"elementType":"geometry","stylers":[{"color":"#212121"}]},
         {"elementType":"labels.icon","stylers":[{"visibility":"off"}]},
         {"elementType":"labels.text.fill","stylers":[{"color":"#757575"}]},
         {"elementType":"labels.text.stroke","stylers":[{"color":"#212121"}]},
         {"featureType":"road","elementType":"geometry","stylers":[{"color":"#383838"}]},
         {"featureType":"water","elementType":"geometry","stylers":[{"color":"#000000"}]}]
    """.trimIndent()
    return com.google.android.gms.maps.model.MapStyleOptions(json)
}

// ── Status Tab ────────────────────────────────────────────────────────────────

@Composable
private fun StatusTab(riskZones: List<RiskZone>) {
    val infiniteTransition = rememberInfiniteTransition(label = "radar")
    val radarScale by infiniteTransition.animateFloat(
        initialValue  = 1f,
        targetValue   = 1.4f,
        animationSpec = infiniteRepeatable(
            animation  = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "radar_scale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background.copy(alpha = 0.9f))
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ── Radar / Status indicator ─────────────────────────────────────────
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors   = CardDefaults.cardColors(containerColor = SurfaceContainerHigh),
            shape    = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier            = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.Center) {
                    // Outer pulsing ring
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .scale(radarScale)
                            .background(Color(0x1A82DB7E), CircleShape)
                    )
                    // Inner dot
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(TertiaryContainer, CircleShape)
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text  = "● MONITORING ACTIVE",
                    style = MaterialTheme.typography.labelLarge,
                    color = TertiaryContainer
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text  = "All sensors operational",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurfaceVariant
                )
            }
        }

        // ── Risk Zone List ────────────────────────────────────────────────────
        if (riskZones.isEmpty()) {
            Text(
                text  = stringResource(R.string.monitoring_no_zones),
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurfaceVariant
            )
        } else {
            Text(
                text  = "ACTIVE RISK ZONES",
                style = MaterialTheme.typography.labelLarge,
                color = OnSurfaceVariant
            )
            riskZones.forEach { zone -> RiskZoneCard(zone) }
        }
    }
}

@Composable
private fun RiskZoneCard(zone: RiskZone) {
    val accentColor = when (zone.severity) {
        RiskSeverity.HIGH   -> PrimaryContainer
        RiskSeverity.MEDIUM -> SecondaryContainer
        RiskSeverity.LOW    -> Color(0xFFF9A825)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, accentColor.copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerHigh),
        shape  = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier            = Modifier.padding(16.dp),
            verticalAlignment   = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text  = zone.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = OnSurface
                )
                Text(
                    text  = zone.type.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = OnSurfaceVariant
                )
            }
            StatusChip(label = zone.severity.name, color = accentColor)
        }
    }
}

@Composable
private fun LogsTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background.copy(alpha = 0.9f))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "SYSTEM LOGS",
            style = MaterialTheme.typography.labelLarge,
            color = OnSurfaceVariant
        )
        // Placeholders
        repeat(5) { i ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerHigh)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "System Check #${100 - i}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "All sensors calibrated and reporting nominal values.",
                        style = MaterialTheme.typography.labelMedium,
                        color = OnSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun StatusChip(label: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.15f))
            .border(1.dp, color, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text  = label,
            style = MaterialTheme.typography.labelMedium,
            color = color
        )
    }
}
