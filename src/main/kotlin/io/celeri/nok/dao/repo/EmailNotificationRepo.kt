package io.celeri.nok.dao.repo

import io.celeri.nok.dao.entity.EmailNotificationEntity
import io.celeri.nok.dao.entity.WatchEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface EmailNotificationRepo: CrudRepository<EmailNotificationEntity, UUID> {

    fun findByWatch(watch: WatchEntity): List<EmailNotificationEntity>
}