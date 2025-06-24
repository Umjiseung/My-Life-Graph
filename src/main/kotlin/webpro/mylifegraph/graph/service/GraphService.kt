package webpro.mylifegraph.graph.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import webpro.mylifegraph.graph.persistence.Graph
import webpro.mylifegraph.graph.persistence.GraphRepository
import webpro.mylifegraph.graph.presentation.dto.*
import webpro.mylifegraph.user.persistence.User
import webpro.mylifegraph.user.persistence.UserRepository
import java.util.UUID

@Service
class GraphService(
    private val graphRepository: GraphRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun createGraph(dto: CreateGraphDto) {
        val uniqueId = UUID.randomUUID()

        val user = User(
            name = dto.name,
            uniqueId = uniqueId.toString()
        )

        userRepository.save(user)

        dto.graphDto.forEach {
            val graph = Graph(
                age = it.age,
                moodIndex = it.moodIndex,
                content = it.content,
                uniqueId = uniqueId.toString()
            )

            graphRepository.save(graph)
        }
    }

    @Transactional(readOnly = true)
    fun getGraphs(): GetGraphsDto {
        val users = userRepository.findAll()

        val graphsDto = users.map { user ->
            GetGraphDto(
                id = user.id,
                name = user.name
            )
        }

        return GetGraphsDto(
            graphs = graphsDto
        )
    }

    fun getGraph(graphId: Long): GetGraphInfoDto {
        val graph = graphRepository.findByIdOrNull(graphId)
            ?: throw IllegalArgumentException("그래프를 찾을 수 없습니다.")

        val userGraphs = graphRepository.findByUniqueId(graph.uniqueId)

        val user = userRepository.findByUniqueId(graph.uniqueId)

        val graphsDto = userGraphs.map {
            GraphDto(
                age = it.age,
                moodIndex = it.moodIndex,
                content = it.content
            )
        }

        return GetGraphInfoDto(
            name = user!!.name,
            graphDto = graphsDto
        )
    }

}