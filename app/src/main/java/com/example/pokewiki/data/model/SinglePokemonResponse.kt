package com.example.pokewiki.data.model

import com.google.gson.annotations.SerializedName

data class SinglePokemonResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("height") val height: Int,
    @field:SerializedName("weight") val weight: Int,
    @field:SerializedName("sprites") val sprites: Sprites? = null
)

data class Sprites(
    @field:SerializedName("back_default") val backDefault: String? = null,
    @field:SerializedName("back_female") val backFemale: Any? = null,
    @field:SerializedName("back_shiny") val backShiny: String? = null,
    @field:SerializedName("other") val other: Other? = null,
    @field:SerializedName("back_shiny_female") val backShinyFemale: Any? = null,
    @field:SerializedName("front_default") val frontDefault: String? = null,
    @field:SerializedName("front_female") val frontFemale: Any? = null,
    @field:SerializedName("front_shiny") val frontShiny: String? = null,
    @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,
)

data class Other(

    @field:SerializedName("dream_world") val dreamWorld: DreamWorld? = null,
    @field:SerializedName("official-artwork") val officialArtwork: OfficialArtwork? = null,
    @field:SerializedName("home") val home: Home? = null

)

data class DreamWorld(

    @field:SerializedName("front_default") val frontDefault: String? = null,
    @field:SerializedName("front_female") val frontFemale: Any? = null,
    @field:SerializedName("home") val home: Home? = null
)

data class OfficialArtwork(
    @field:SerializedName("front_default") val frontDefault: String? = null
)

data class Home(

    @field:SerializedName("front_shiny_female") val frontShinyFemale: Any? = null,
    @field:SerializedName("front_default") val frontDefault: String? = null,
    @field:SerializedName("front_female") val frontFemale: Any? = null,
    @field:SerializedName("front_shiny") val frontShiny: String? = null
)
