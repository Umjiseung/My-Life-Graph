package webpro.mylifegraph.graph.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import webpro.mylifegraph.graph.persistence.Graph
import webpro.mylifegraph.graph.persistence.GraphRepository
import webpro.mylifegraph.graph.presentation.dto.CreateGraphDto
import webpro.mylifegraph.graph.presentation.dto.GetGraphDto
import webpro.mylifegraph.graph.presentation.dto.GetGraphsDto
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
        val graphs = graphRepository.findAll()

        val graphsDto = graphs.map {
            val user = userRepository.findByUniqueId(it.uniqueId)

            GetGraphDto(
                id = it.id,
                name = user!!.name
            )
        }

        return GetGraphsDto(
            graphs = graphsDto
        )
    }

}