package se.grit.erik.cveye

data class CveItem(
    val id: String,
    val published: String,
    val lastModified: String,
    val description: String
)