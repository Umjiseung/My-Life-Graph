package webpro.mylifegraph.graph.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity
class Graph(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val age: Int,

    @Column(nullable = false)
    val moodIndex: Int,

    @Column(nullable = false)
    val content: String,

    @Column(nullable = false)
    val uniqueId: String
)
