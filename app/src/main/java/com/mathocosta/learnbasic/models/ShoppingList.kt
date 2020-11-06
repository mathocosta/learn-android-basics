package com.mathocosta.learnbasic.models

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class ShoppingList(var name: String, val items: MutableList<ShoppingListItem>) : Serializable {
    val checkedItems: List<ShoppingListItem>
        get() = items.filter { it.isChecked }
}