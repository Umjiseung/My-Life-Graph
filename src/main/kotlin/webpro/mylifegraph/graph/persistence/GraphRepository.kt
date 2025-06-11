package webpro.mylifegraph.graph.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface GraphRepository: JpaRepository<Graph, Long> {
    fun findByUniqueId(uniqueId: String): List<Graph>
}