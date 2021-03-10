package io.celeri.nok.service

import io.celeri.nok.dao.NotificationStateRepo
import io.celeri.nok.dao.ReportStateRepo
import io.celeri.nok.dao.WatchRepo
import io.celeri.nok.domain.Watch
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DefaultDomainObjectService(
        val watchRepo: WatchRepo,
        val notificationStateRepo: NotificationStateRepo,
        val reportStateRepo: ReportStateRepo
): DomainObjectService {

    private val defaultId: String = "default"

//    @Value("\${lab.beansAtStart}")
//    private val beansAtStart: Boolean = false // default value is not used, it is there to satisfy compiler

    override fun watch(): Watch {
        val watchEntity = watchRepo.findById(defaultId)
        TODO()

    }
}