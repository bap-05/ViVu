package com.example.vivu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vivu.domain.model.BottomNavItem
import com.example.vivu.presentation.screens.HomeScreen
import com.example.vivu.presentation.theme.ViVuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViVuTheme { // Bắt buộc phải có Theme
                // Gọi MainScreen để hiển thị toàn bộ app
                MainScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun MainScreen(){
    val items = listOf(
        BottomNavItem(
            title = "Trang chủ",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            title = "Yêu thích",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder
        ),
        BottomNavItem(
            title = "Hồ sơ",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        )
    )
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.background(color = Color.Black),
        bottomBar = {NavigationBar(modifier = Modifier
            // Cắt bo góc trên bên trái và trên bên phải (24.dp)
            .clip(RoundedCornerShape( 24.dp)),

            containerColor = Color.Black.copy(alpha = 0.5f),
            tonalElevation = 0.dp) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(selected = selectedItemIndex == index,
                    onClick = {selectedItemIndex = index},
                    label = {Text(text = item.title)},
                    icon = {
                        Icon(
                            imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title)


                    },
                    colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Black
                )
                    )
            }
        }}
    ) {innerPadding ->
        Box(modifier = Modifier.fillMaxWidth()) {
            when (selectedItemIndex) {
                0 -> HomeScreen(paddingValues = innerPadding) // Màn hình Trang chủ bạn đã làm ở trên
                1 -> Text("Đây là màn hình Yêu thích") // Tạm thời để Text
                2 -> Text("Đây là màn hình Hồ sơ cá nhân") // Tạm thời để Text
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViVuTheme {
        MainScreen() // Sửa thành MainScreen để xem tổng thể
    }
}
