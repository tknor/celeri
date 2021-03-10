package io.celeri.nok.service

import io.celeri.dma.common.asNullable
import io.celeri.nok.dao.*
import io.celeri.nok.dao.entity.*
import io.celeri.nok.dao.repo.EmailNotificationRepo
import io.celeri.nok.dao.repo.ReportStateRepo
import io.celeri.nok.dao.repo.SmsNotificationRepo
import io.celeri.nok.dao.repo.WatchRepo
import io.celeri.nok.domain.*
import org.springframework.stereotype.Service

@Service
class DefaultDomainObjectService(
        val watchRepo: WatchRepo,
        val emailNotificationRepo: EmailNotificationRepo,
        val smsNotificationRepo: SmsNotificationRepo,
        val reportStateRepo: ReportStateRepo
): DomainObjectService {

    private val defaultId: String = "default"

    override fun watch(): Watch? {

        val watchEntity: WatchEntity = watchRepo.findById(defaultId).asNullable() ?: return null

        val emailNotificationEntities = emailNotificationRepo.findByWatch(watchEntity)
        val smsNotificationEntities = smsNotificationRepo.findByWatch(watchEntity)

        return watchMapper(watchEntity, emailNotificationEntities, smsNotificationEntities, this)
    }

    override fun reportState(): ReportState? {

        val reportStateEntity: ReportStateEntity = reportStateRepo.findById(defaultId).asNullable() ?: return null

        return reportStateMapper(reportStateEntity)
    }

    override fun watchHeartbeatChanged(watch: Watch) {
        watchRepo.save(watchEntityMapper(watch))
    }

    override fun watchEmailNotificationSent(watch: Watch, notification: EmailNotification) {
        emailNotificationRepo.save(emailNotificationEntityMapper(watch, notification))
    }

    override fun watchSmsNotificationSent(watch: Watch, notification: SmsNotification) {
        smsNotificationRepo.save(smsNotificationEntityMapper(watch, notification))
    }

    override fun reportEmailSent(reportState: ReportState) {
        reportStateRepo.save(reportStateEntityMapper(reportState))
    }

    override fun reportSmsSent(reportState: ReportState) {
        reportStateRepo.save(reportStateEntityMapper(reportState))
    }
}