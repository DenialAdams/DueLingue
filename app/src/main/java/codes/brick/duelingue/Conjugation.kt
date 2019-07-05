package codes.brick.duelingue

import com.google.gson.annotations.SerializedName

data class Conjugation(
    @SerializedName("group")
    val group: String,
    @SerializedName("value")
    val value: String
) {
    @SerializedName("form")
    val form: String? = null
}