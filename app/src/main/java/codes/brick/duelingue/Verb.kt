package codes.brick.duelingue

import com.google.gson.annotations.SerializedName

data class Verb(
    @SerializedName("conjugations")
    val conjugations: List<Conjugation>,
    @SerializedName("definitions")
    val definitions: List<String>,
    @SerializedName("word")
    val word: String
)