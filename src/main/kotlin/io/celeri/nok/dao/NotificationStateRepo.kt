package io.celeri.nok.dao

import io.celeri.nok.dao.entity.NotificationStateEntity
import org.springframework.data.repository.CrudRepository

interface NotificationStateRepo: CrudRepository<NotificationStateEntity, String>