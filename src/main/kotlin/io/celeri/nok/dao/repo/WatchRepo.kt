package io.celeri.nok.dao.repo

import io.celeri.nok.dao.entity.WatchEntity
import org.springframework.data.repository.CrudRepository

interface WatchRepo: CrudRepository<WatchEntity, String>