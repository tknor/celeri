package io.celeri.nok.dao

import io.celeri.nok.dao.entity.ReportStateEntity
import org.springframework.data.repository.CrudRepository

interface ReportStateRepo: CrudRepository<ReportStateEntity, String>