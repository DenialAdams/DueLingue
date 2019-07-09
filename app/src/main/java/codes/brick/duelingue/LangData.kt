package codes.brick.duelingue

data class LangData(
    val tenses: List<String>,
    val verb_groups: List<VerbGroup>
)

data class VerbGroup(
    val name: String,
    val verbs: List<Verb>
)