package io.celeri.nok.dao.repo

import io.celeri.nok.dao.entity.EmailNotificationEntity
import io.celeri.nok.dao.entity.SmsNotificationEntity
import io.celeri.nok.dao.entity.WatchEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SmsNotificationRepo: CrudRepository<SmsNotificationEntity, UUID> {

    fun findByWatch(watch: WatchEntity): List<SmsNotificationEntity>
}