package com.example.pokewiki.data.model

import com.google.gson.annotations.SerializedName

data class SinglePokemonResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("height") val height: Int?,
    @SerializedName("weight") val weight: Int?,
    @SerializedName("sprites") val sprites: Sprites?
) {

    data class Sprites(
        @SerializedName("back_default") val backDefault: String?,
        @SerializedName("back_female") val backFemale: Any?,
        @SerializedName("back_shiny") val backShiny: String?,
        @SerializedName("back_shiny_female") val backShinyFemale: Any?,
        @SerializedName("front_default") val frontDefault: String?,
        @SerializedName("front_female") val frontFemale: Any?,
        @SerializedName("front_shiny") val frontShiny: String?,
        @SerializedName("front_shiny_female") val frontShinyFemale: Any?,
        @SerializedName("other") val other: Other?
    ) {
        data class Other(
            @SerializedName("dream_world") val dreamWorld: DreamWorld?,
            @SerializedName("home") val home: Home?,
            @SerializedName("official-artwork") val officialArtwork: OfficialArtwork?
        ) {
            data class DreamWorld(
                @SerializedName("front_default") val frontDefault: String?,
                @SerializedName("front_female") val frontFemale: Any?
            )

            data class Home(
                @SerializedName("front_default") val frontDefault: String?,
                @SerializedName("front_female") val frontFemale: Any?,
                @SerializedName("front_shiny") val frontShiny: String?,
                @SerializedName("front_shiny_female") val frontShinyFemale: Any?
            )

            data class OfficialArtwork(
                @SerializedName("front_default") val frontDefault: String?
            )
        }

    }
}