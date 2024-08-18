package com.ariestech.reciperevolution.ui.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import coil.compose.AsyncImage
import com.ariestech.reciperevolution.R
import com.ariestech.reciperevolution.databinding.FragmentHomeBinding
import com.ariestech.reciperevolution.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        binding.composeView.setContent {
            RecipeRevolutionApp()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipeRevolutionApp() {
        Column(modifier = Modifier
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .padding(start = 8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            CategoryButtons()
            Spacer(modifier = Modifier.height(16.dp))
            HeroImageBanner()
            Spacer(modifier = Modifier.height(24.dp))
            RecommendationContents()
            Spacer(modifier = Modifier.height(24.dp))
            DietTypes()
        }
}

@Composable
fun CategoryButtons() {
    Column(Modifier.padding(top = 10.dp, bottom = 2.dp)) {
        var selectedIndex by remember { mutableStateOf(0) }
        val categories = listOf(
            "Main",
            "Side Dish",
            "Dessert",
            "Appetizer",
            "Salad",
            "Bread",
            "Breakfast",
            "Soup",
            "Beverage",
            "Sauce",
            "Marinade",
            "Finger Food",
            "Snack",
            "Drinks"
        )

        LazyRow {
            items(categories) { category ->
                val index = categories.indexOf(category)
                val isSelected = index == selectedIndex
                Button(
                    onClick = {
                        selectedIndex = if (isSelected) -1 else index
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) colorResource(R.color.colorPrimary) else Color.Transparent,
                        contentColor = if (isSelected) Color.White else colorResource(R.color.colorPrimary),

                        ),
                    border = if (!isSelected) {
                        BorderStroke(1.dp, colorResource(R.color.colorPrimary))
                    } else {
                        null
                    },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .wrapContentWidth()
                ) {
                    Text(text = category)
                }
            }
        }

    }

}

@Composable
fun HeroImageBanner() {
    val images = listOf(
        "https://drive.google.com/uc?export=view&id=1TuHPVbf-UNRZI04w8m_iURYb5NXixuVk",
        "https://drive.google.com/uc?export=view&id=1NaZXbiPyX4GL0ySbB2YdzHnBc8aOpf6B",
        "https://drive.google.com/uc?export=view&id=1SDCPxYtbKzsCPXiHjH_7zGc9BH7euNgj"
    )
    var currentImageIndex by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(8000)
            visible = false
            delay(500) // Duration for fade-out
            currentImageIndex = (currentImageIndex + 1) % images.size
            visible = true
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
        exit = fadeOut(animationSpec = tween(durationMillis = 1000))
    ) {
        AsyncImage(
            model = images[currentImageIndex],
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            placeholder = null,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(8.dp))
                .padding(end = 8.dp)
        )
    }
}


@Composable
fun RecommendationContents() {
    val recommendations = listOf(
        "https://drive.google.com/uc?export=view&id=1j-Z5tJC4rjL5NB2xhvn17obEJlmbgesm" to "Pasta",
        "https://drive.google.com/uc?export=view&id=16abDROUD5UDEH3cKY2jWuejxsyZIbMgQ" to "Burger",
    )

    Column {
        Text(
            text = "Recommendations",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyRow {
            items(recommendations) { (image, text) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 16.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .width(200.dp) // Set a fixed width for the image box
                            .height(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            contentScale = ContentScale.Inside, // Crop to fit the box size
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize() // Fills the entire image box
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black),
                                        startY = 0f,
                                        endY = 500f
                                    )
                                ),
                        ){
                            Text(
                                text = text,
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                ),
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(8.dp),
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DietTypes() {
    val recommendations = listOf(
        "https://drive.google.com/uc?export=view&id=1lf7JS3zl8Q1DU9W7NVNLulhmg3V3uogD" to "Vegetarian",
        "https://drive.google.com/uc?export=view&id=1alGyBWyvEplcAYP4jzMrSTmkoNraV28d" to "Ketogenic",
    )

    Column {
        Text(
            text = "Diet Types",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyRow {
            items(recommendations) { (image, text) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 16.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .width(200.dp) // Set a fixed width for the image box
                            .height(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            contentScale = ContentScale.Inside, // Crop to fit the box size
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize() // Fills the entire image box
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black),
                                        startY = 0f,
                                        endY = 500f
                                    )
                                ),
                        ){
                            Text(
                                text = text,
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                ),
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(8.dp),
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
        }
    }
}