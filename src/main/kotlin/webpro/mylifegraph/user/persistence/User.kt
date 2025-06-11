package webpro.mylifegraph.user.persistence

import jakarta.persistence.*
import java.util.UUID

@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val uniqueId: String
)
