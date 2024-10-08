package com.ariestech.reciperevolution.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ariestech.reciperevolution.models.Result
import com.ariestech.reciperevolution.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)