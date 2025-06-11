package webpro.mylifegraph.graph.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import webpro.mylifegraph.graph.presentation.dto.CreateGraphDto
import webpro.mylifegraph.graph.service.GraphService

@RestController
@RequestMapping("/api/graph")
class GraphController(
    private val graphService: GraphService
) {

    @PostMapping
    fun createGraph(
        @RequestBody dto: CreateGraphDto
    ): ResponseEntity<Void> {
        graphService.createGraph(dto)
        return ResponseEntity.ok().build()
    }
}