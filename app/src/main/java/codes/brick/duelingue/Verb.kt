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

data class Conjugation(
    @SerializedName("group")
    val group: String,
    @SerializedName("value")
    val value: String
) {
    @SerializedName("form")
    val form: String? = null
}