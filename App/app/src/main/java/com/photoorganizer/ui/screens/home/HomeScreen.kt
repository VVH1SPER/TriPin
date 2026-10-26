package com.photoorganizer.ui.screens.home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.photoorganizer.R
import com.photoorganizer.model.PhotoEntity
import com.photoorganizer.ui.viewmodel.PhotoViewModel
import java.io.File

@Composable
fun HomeScreen(
    viewModel: PhotoViewModel = viewModel(),
    onOpenCamera: () -> Unit = {},
    onOpenTags: () -> Unit = {},
    onOpenPhoto: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val folders by viewModel.folders.collectAsState()
    val photos by viewModel.photos.collectAsState()
    var folderName by remember { mutableStateOf("") }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.tripin_logo),
                        contentDescription = "TriPin logo",
                        modifier = Modifier.size(56.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "TriPin",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Text(
                    text = "Organize photos with folders, tags, and quick capture.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = onOpenCamera, modifier = Modifier.weight(1f)) {
                        Text("Camera")
                    }
                    Button(onClick = onOpenTags, modifier = Modifier.weight(1f)) {
                        Text("Tags")
                    }
                }

                OutlinedTextField(
                    value = folderName,
                    onValueChange = { folderName = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("New folder") }
                )

                Button(
                    onClick = {
                        if (folderName.isNotBlank()) {
                            viewModel.createFolder(folderName)
                            folderName = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Create folder")
                }
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Folders")
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    folders.filter { it.parentId == null }.forEach { folder ->
                        Card(
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                        ) {
                            Text(
                                text = folder.name,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
                            )
                        }
                    }
                }
            }
        }

        if (photos.isEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "No photos yet. Take the first shot from the camera screen.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        items(photos) { photo ->
            PhotoGridItem(
                photo = photo,
                context = context,
                onClick = { onOpenPhoto(photo.id) }
            )
        }
    }
}

@Composable
private fun PhotoGridItem(
    photo: PhotoEntity,
    context: android.content.Context,
    onClick: () -> Unit
) {
    val imageFile = File(context.filesDir, "photos/${photo.fileName}")
    val request = ImageRequest.Builder(context)
        .data(Uri.fromFile(imageFile))
        .crossfade(true)
        .build()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 180.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp)
    ) {
        AsyncImage(
            model = request,
            contentDescription = "Photo thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 180.dp)
                .clip(RoundedCornerShape(18.dp))
        )
    }
}
