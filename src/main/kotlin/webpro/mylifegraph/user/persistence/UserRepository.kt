package webpro.mylifegraph.user.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUniqueId(uniqueId: String): User?
}