package io.celeri.nok.dao

import io.celeri.nok.dao.entity.EmailNotificationEntity
import io.celeri.nok.dao.entity.SmsNotificationEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SmsNotificationRepo: CrudRepository<SmsNotificationEntity, UUID>