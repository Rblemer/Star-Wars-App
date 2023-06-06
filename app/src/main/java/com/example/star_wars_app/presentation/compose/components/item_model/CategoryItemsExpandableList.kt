package com.example.star_wars_app.presentation.compose.components.item_model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.star_wars_app.presentation.compose.components.card.DefaultExpandableCard
import com.example.star_wars_app.presentation.compose.widgets.image.SmallCategoryItemImage
import com.example.star_wars_app.presentation.compose.widgets.state.error.DefaultErrorText
import com.example.star_wars_app.presentation.compose.widgets.state.loading.DefaultLoading
import com.example.star_wars_app.presentation.model.SmallItemModel
import com.example.star_wars_app.presentation.model.StateUI

@Composable
fun CategoryItemsExpandableList(
    name: String,
    listState: StateUI<List<SmallItemModel>>,
    onClick: (url: String) -> Unit
) {
    DefaultExpandableCard(name = name) {
        when (listState) {
            is StateUI.Error -> DefaultErrorText(message = listState.message)
            is StateUI.Idle -> Unit
            is StateUI.Processed -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(all = 16.dp)
                ) {
                    items(listState.data) { item ->
                        SmallCategoryItemImage(
                            text = item.name,
                            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            image = item.image,
                            onClick = {
                                onClick(item.url)
                            }
                        )
                    }
                }
            }
            is StateUI.Processing -> DefaultLoading(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}