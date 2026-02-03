package com.example.second

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.second.ui.theme.SecondTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DashboardScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Stateful Parent Composable
 * Owns the state (count and isEnabled) and manages recomposition.
 * Demonstrates: Uses remember { mutableStateOf(...) }
 */
@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    // Stateful logic
    var count by remember { mutableIntStateOf(0) }
    var isEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 1. Text displaying a title
        Text(
            text = "Interactive Dashboard",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // 5. Custom Composable: CounterCard (Stateless)
        CounterCard(
            count = count,
            isEnabled = isEnabled,
            onIncrement = { count++ }
        )

        // 5. Custom Composable: SettingsToggle (Stateless)
        SettingsToggle(
            isEnabled = isEnabled,
            onToggle = { isEnabled = it }
        )
    }
}

/**
 * Stateless Child Composable - Counter Card
 * Demonstrates: Receives state via parameters, uses lambda callback, emits UI.
 */
@Composable
fun CounterCard(
    count: Int,
    isEnabled: Boolean,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 3. Dynamic text that updates automatically
            Text(
                text = "Points: $count",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            // 2. Button that changes state
            Button(
                onClick = onIncrement,
                enabled = isEnabled
            ) {
                Text("Increment Points")
            }
        }
    }
}

/**
 * Stateless Child Composable - Settings Toggle
 * Demonstrates: Uses a Row layout and a Switch.
 */
@Composable
fun SettingsToggle(
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // 4. A Row layout
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Allow Increments",
            style = MaterialTheme.typography.bodyLarge
        )
        // 2. Switch that changes state
        Switch(
            checked = isEnabled,
            onCheckedChange = onToggle
        )
    }
}

/**
 * Compose Preview
 * Demonstrates: Correct rendering in Android Studio.
 */
@Preview(showBackground = true, name = "Dashboard Preview")
@Composable
fun DashboardPreview() {
    SecondTheme {
        DashboardScreen()
    }
}
