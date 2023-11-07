package dev.passerby.data.models.dto


import com.google.gson.annotations.SerializedName

class PizzaDto : ArrayList<PizzaDtoItem>()

data class PizzaDtoItem(
    @SerializedName("category")
    val category: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("sizes")
    val sizes: List<Int>,
    @SerializedName("title")
    val title: String
)