package com.mathocosta.learnbasic.models

import java.io.Serializable

data class ShoppingListItem(var name: String,
                            var description: String,
                            var quantity: Int,
                            var isChecked: Boolean) : Serializable