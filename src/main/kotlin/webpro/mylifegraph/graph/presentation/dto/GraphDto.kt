package webpro.mylifegraph.graph.presentation.dto

data class CreateGraphDto(
    val name: String,
    val graphDto: List<GraphDto>,
)

data class GraphDto(
    val age: Int,
    val moodIndex: Int,
    val content: String,
)

data class GetGraphsDto(
    val graphs: List<GetGraphDto>
)

data class GetGraphDto(
    val id: Long,
    val name: String,
)
