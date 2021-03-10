package io.celeri.dao

import io.celeri.nok.dao.EmailNotificationRepo
import io.celeri.nok.dao.ReportStateRepo
import io.celeri.nok.dao.WatchRepo
import io.celeri.nok.dao.entity.WatchEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.Instant

@DataJpaTest
class ReposTest @Autowired constructor(
        val watchRepo: WatchRepo,
        val emailNotificationRepo: EmailNotificationRepo,
        val reportStateRepo: ReportStateRepo
) {

    @Test
    fun some() {
        val watch = WatchEntity("default", Instant.now())
        watchRepo.save(watch)
        val recoveredWatch = watchRepo.findById("default")
        assertThat(recoveredWatch).isNotNull
    }
}