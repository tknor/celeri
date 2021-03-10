package io.celeri.nok.dao

import io.celeri.nok.dao.entity.EmailNotificationEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface EmailNotificationRepo: CrudRepository<EmailNotificationEntity, UUID>