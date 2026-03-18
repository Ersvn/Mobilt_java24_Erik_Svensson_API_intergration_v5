package se.grit.erik.cveye.model

data class NvdResponse(
    val vulnerabilities: List<Vulnerability>
)

data class Vulnerability(
    val cve: Cve
)

data class Cve(
    val id: String,
    val published: String,
    val lastModified: String,
    val descriptions: List<Description>
)

data class Description(
    val lang: String,
    val value: String
)