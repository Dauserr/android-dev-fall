package com.example.sis2assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sis2assignment.ui.theme.SIS2AssignmentTheme

class MainActivity : ComponentActivity() {
    val mockPosts = listOf(
        Post("Sunset at the beach", "https://images.unsplash.com/photo-1506744038136-46273834b3fb", false),
        Post("Mountain waterfall", "https://images.unsplash.com/photo-1465101046530-73398c7f28ca", false),
        Post("City lights at night", "https://images.unsplash.com/photo-1519125323398-675f0ddb6308", false),
        Post("Calm forest", "https://images.unsplash.com/photo-1444065381814-865dc9da92c0", false),
        Post("Hot air balloons", "https://images.unsplash.com/photo-1465101178521-1e2893d2e204", false),
        Post("Desert dunes", "https://images.unsplash.com/photo-1444522652429-77a4c9b3f8c5", false),
        Post("River under a bridge", "https://images.unsplash.com/photo-1469474968028-56623f02e42e", false),
        Post("Snowy peaks", "https://images.unsplash.com/photo-1501785888041-af3ef285b470", false),
        Post("Beautiful meadow", "https://images.unsplash.com/photo-1500534623283-312aade485b7", false),
        Post("Foggy skyline", "https://images.unsplash.com/photo-1465101178521-1e2893d2e204", false),
        Post("Sunny autumn park", "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e", false),
        Post("Colorful flowers field", "https://images.unsplash.com/photo-1454023492550-5696afc6e81c", false),
        Post("Rocky shore", "https://images.unsplash.com/photo-1465101178521-1e2893d2e204", false),
        Post("Rainy window", "https://images.unsplash.com/photo-1506744038136-46273834b3fb", false),
        Post("Green rice terrace", "https://images.unsplash.com/photo-1465101046530-73398c7f28ca", false),
        Post("Lake reflections", "https://images.unsplash.com/photo-1444065381814-865dc9da92c0", false),
        Post("Busy downtown street", "https://images.unsplash.com/photo-1519125323398-675f0ddb6308", false),
        Post("Ancient castle", "https://images.unsplash.com/photo-1465101178521-1e2893d2e204", false),
        Post("Village sunset", "https://images.unsplash.com/photo-1501785888041-af3ef285b470", false),
        Post("Tropical beach", "https://images.unsplash.com/photo-1506744038136-46273834b3fb", false)
    )

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray("likes", mockPosts.map {it.liked}.toBooleanArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val likes = savedInstanceState.getBooleanArray("likes")
        if (likes != null){
            for (i in likes.indices){
                mockPosts[i].liked = likes[i]
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) //показывать вертикально
        recyclerView.adapter = PostAdapter(mockPosts)
    }
}
