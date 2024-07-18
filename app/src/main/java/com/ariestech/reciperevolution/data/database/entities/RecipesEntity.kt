package com.ariestech.reciperevolution.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ariestech.reciperevolution.models.FoodRecipe
import com.ariestech.reciperevolution.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}