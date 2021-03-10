package io.celeri.nok.service

import io.celeri.nok.dao.EmailNotificationRepo
import io.celeri.nok.dao.ReportStateRepo
import io.celeri.nok.dao.WatchRepo
import io.celeri.nok.domain.*
import org.springframework.stereotype.Service

@Service
class DefaultDomainObjectService(
        val watchRepo: WatchRepo,
        val emailNotificationRepo: EmailNotificationRepo,
        val reportStateRepo: ReportStateRepo
): DomainObjectService {

    private val defaultId: String = "default"

    override fun watch(): Watch {
        // TODO
        val watchEntity = watchRepo.findById(defaultId).get() ?: throw RuntimeException("entity not found")
        val notifications = ArrayList<Notification>()
        val watch = Watch(defaultId, watchEntity.heartbeat, notifications, this)
        TODO()
    }

    override fun watchHeartbeatChanged(watch: Watch) {
        TODO("Not yet implemented")
    }

    override fun watchEmailNotificationSent(notification: EmailNotification) {
        TODO("Not yet implemented")
    }

    override fun watchSmsNotificationSent(notification: SmsNotification) {
        TODO("Not yet implemented")
    }

    override fun reportEmailSent(reportState: ReportState) {
        TODO("Not yet implemented")
    }

    override fun reportSmsSent(reportState: ReportState) {
        TODO("Not yet implemented")
    }
}