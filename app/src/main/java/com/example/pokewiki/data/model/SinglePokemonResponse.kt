package com.example.pokewiki.data.model

import com.google.gson.annotations.SerializedName

data class SinglePokemonResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("height") val height: Int? = null,
    @SerializedName("weight") val weight: Int? = null,
    @SerializedName("sprites") val sprites: Sprites? = null
)

data class Sprites(
    @SerializedName("back_default") val backDefault: String? = null,
    @SerializedName("back_female") val backFemale: Any? = null,
    @SerializedName("back_shiny") val backShiny: String? = null,
    @SerializedName("back_shiny_female") val backShinyFemale: Any? = null,
    @SerializedName("front_default") val frontDefault: String? = null,
    @SerializedName("front_female") val frontFemale: Any? = null,
    @SerializedName("front_shiny") val frontShiny: String? = null,
    @SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,
)