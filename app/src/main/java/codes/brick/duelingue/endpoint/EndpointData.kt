package codes.brick.duelingue.endpoint

import codes.brick.duelingue.VerbGroup
import com.google.gson.annotations.SerializedName

data class EndpointLangData(
    @SerializedName("language")
    val language: String,
    @SerializedName("tenses")
    val tenses: List<String>,
    @SerializedName("verb_groups")
    val verb_groups: List<EndpointVerbGroup>
)

data class EndpointVerbGroup(
    @SerializedName("name")
    val name: String,
    @SerializedName("verbs")
    val verbs: List<String>
)
