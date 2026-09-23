package com.example.vivu.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vivu.R
import com.example.vivu.presentation.theme.ViVuTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp


data class SuggestionItem(val title: String, val icon: String)
@Composable
fun HomeScreen(paddingValues: PaddingValues = PaddingValues(0.dp)){
    Column (Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)){
        HomeHeader()
        LazyColumn (
            modifier = Modifier.fillMaxSize(),contentPadding = paddingValues,
            // Tạo khoảng trống ở trên/dưới của toàn bộ danh sách

            // Tự động tạo khoảng cách 16.dp giữa tất cả các phần tử (không cần dùng Spacer nữa)
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            item {
                SearchBar()
            }
            item { SuggestionSection() }
            item { Trending() }
        }

    }
}

@Composable
fun HomeHeader() {
    Row (Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Image(

            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp,60.dp)
        )
        Surface(
            modifier = Modifier.padding(10.dp).size(48.dp),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.secondary // Màu Mellow Yellow
        ) {
            // Icon tạm thời
        }
    }
}

@Composable
fun SearchBar(){
    var searchText by remember { mutableStateOf("") }
    Column(Modifier.fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Hôm nay bạn muốn đi đâu?",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(

            value = searchText,
            onValueChange = {  searchText = it }, // Cập nhật chữ khi gõ
            modifier = Modifier.fillMaxWidth(),

            // Chữ mờ gợi ý khi ô nhập trống
            placeholder = {
                Text("Tìm kiếm điểm đến")
            },

            // Kính lúp ở đầu thanh tìm kiếm
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },

            // Bo tròn 4 góc
            shape = RoundedCornerShape(16.dp),

            // Chỉ cho phép gõ trên 1 dòng, không tự động xuống dòng
            singleLine = true,

            // Xóa đường gạch chân (underline) mặc định xấu xí của TextField
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black
            )
        )
    }
}
@Composable
fun SuggestionSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Gợi ý hôm nay",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Chạm để tìm nhanh",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(Modifier.weight(1f)) { SuggestionCard(SuggestionItem("Trending", "🌟")) }
            Box(Modifier.weight(1f)) { SuggestionCard(SuggestionItem("Gần bạn", "📍")) }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(Modifier.weight(1f)) { SuggestionCard(SuggestionItem("Biển", "🏖️")) }
            Box(Modifier.weight(1f)) { SuggestionCard(SuggestionItem("Văn hóa", "🏯")) }
        }
        // Thẻ cuối cùng không nằm trong Row, mặc định sẽ full width
        SuggestionCard(SuggestionItem("Thiên nhiên", "🌿"))
    }
}
@Composable
fun SuggestionCard(item: SuggestionItem) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        modifier = Modifier.fillMaxWidth(),
        // Tạo viền mờ giống trong ảnh
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tạm dùng Text thay cho Icon (bạn có thể thay bằng Image/Icon thực tế)
            Text(text = item.icon, modifier = Modifier.padding(end = 8.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }
}
@Composable
fun Trending(){
    Column(
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
    ){
        Row ( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "Đang thịnh hành",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge

            )
            OutlinedButton(onClick = {},
                modifier = Modifier.size(120.dp,40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            )
            {
                Text(text = "Xem thêm",
                    fontSize = 12.sp)
            }
        }
        OutlinedCard(
            modifier = Modifier. fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp,Color(0xFFE0E0E0)),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color.White,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ){
            Column (modifier = Modifier.padding(10.dp)){
                Image(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = ""
                )
                Text(text = "Hồ Xuân Hương",
                    fontSize = 16.sp,
                    )
                Text(
                    text = "Đà Lạt • ⭐ 4.6 • 1,8k đánh giá",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    // Tạm thời dùng MaterialTheme mặc định nếu bạn chưa cấu hình xong ViVuTheme
    ViVuTheme {
        HomeScreen() // Gọi hàm giao diện chính của bạn vào đây
    }
}