package io.celeri.nok.dao

import io.celeri.nok.dao.entity.WatchEntity
import org.springframework.data.repository.CrudRepository

interface WatchRepo: CrudRepository<WatchEntity, String>