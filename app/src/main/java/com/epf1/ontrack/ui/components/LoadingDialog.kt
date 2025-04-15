package com.epf1.ontrack.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.epf1.ontrack.R
import androidx.compose.ui.graphics.drawscope.clipRect

@Composable
fun LoadingDialog() {
    val progress = remember { Animatable(0f) }
    val baseCircuitIcons = listOf(R.drawable.ic_interlagos_base, R.drawable.ic_monaco_base, R.drawable.ic_spa_base)
    val redCircuitIcons = listOf(R.drawable.ic_interlagos_red, R.drawable.ic_monaco_red,  R.drawable.ic_spa_red)
    val randomNum = (0..2).random()

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(2500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Dialog(onDismissRequest = {}) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            color = Color(0xFF1C1C1C)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Warming up the track...",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                ) {
                    // Circuito em cinza (base)
                    Image(
                        painter = painterResource(id = baseCircuitIcons[randomNum]), // circuito cinza
                        contentDescription = "Circuit Outline",
                        modifier = Modifier.matchParentSize()
                    )

                    // Circuito em vermelho (preenchido parcialmente)
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .drawWithContent {
                                val clipWidth = size.width * progress.value
                                clipRect(right = clipWidth) {
                                    this@drawWithContent.drawContent()
                                }
                            }
                    ) {
                        Image(
                            painter = painterResource(id = redCircuitIcons[randomNum]),
                            contentDescription = "Filling Circuit",
                            modifier = Modifier.matchParentSize()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Fetching race data...",
                    fontSize = 16.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}
