package com.example.artspace

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge // Assurez-vous que cette méthode existe
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

data class ImageData(val title: String, val author: String, val imageResId: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Vérifiez que cette méthode est disponible dans votre configuration
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().height(420.dp),
                    shape= RectangleShape, shadowElevation = 40.dp,
                    color = MaterialTheme.colorScheme.background

                ) {
                    ImageApp()
                }
            }
        }
    }

    @Composable
    fun ImageApp(modifier: Modifier = Modifier) {
        val imageList = listOf(
            ImageData("The mysterious book", "James Adams", R.drawable.image_1),
            ImageData("River of joy", "Emilie Hooks", R.drawable.image_4),
            ImageData("Thousand miles","Jean Pierre", R.drawable.image_2)
        )

        var currentIndex by remember { mutableStateOf(0) }

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageList[currentIndex].imageResId),
                contentDescription = null,
                modifier = Modifier.wrapContentSize(Alignment.Center)
                    .width(350.dp)
                    .height(350.dp)  ,
                    contentScale= ContentScale.Crop
//



            )
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .width(450.dp)
                    .background(Color.White, RoundedCornerShape(4.dp)), // Ajout d'une forme arrondie si nécessaire
                contentAlignment = Alignment.Center // Centrer le contenu
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement=Arrangement.Center  ) {
                    Text(
                        text = imageList[currentIndex].title,
                        modifier = Modifier.padding(2.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = imageList[currentIndex].author,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 22.sp
                    )
                    Spacer(modifier=modifier.height(110.dp))
                }
                
            }
                    // Navigation des boutons

            Row(
                modifier = Modifier
                    .fillMaxWidth() // Prendre toute la largeur
                    .padding(24.dp), // Ajouter un padding si nécessaire
                horizontalArrangement = Arrangement.SpaceBetween // Espace entre les boutons
            ) {
                        Button(
                            onClick = { if (currentIndex > 0) currentIndex-- },
                            enabled = currentIndex > 0
                        ) {
                            Text("Retour")
                        }


                        Button(
                            onClick = { if (currentIndex < imageList.size - 1) currentIndex++ },
                            enabled = currentIndex < imageList.size - 1

                        ) {
                            Text("Suivant")
                        }


                }


        }
    }
    @Preview(showBackground = true)
    @Composable
    fun ImageSlidePreview() {
        ArtSpaceTheme {
            ImageApp()
        }
    }
}

