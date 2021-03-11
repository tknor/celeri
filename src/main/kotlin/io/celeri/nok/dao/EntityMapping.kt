package io.celeri.nok.dao

import io.celeri.dma.common.minutesToMillis
import io.celeri.nok.config.EmailNotificationConfig
import io.celeri.nok.config.SmsNotificationConfig
import io.celeri.nok.dao.entity.EmailNotificationEntity
import io.celeri.nok.dao.entity.ReportStateEntity
import io.celeri.nok.dao.entity.SmsNotificationEntity
import io.celeri.nok.dao.entity.WatchEntity
import io.celeri.nok.domain.*
import io.celeri.nok.domain.change.WatchChangeObserver
import java.nio.file.Paths
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

fun watchMapper(
        watchEntity: WatchEntity,
        emailNotificationEntities: List<EmailNotificationEntity>,
        smsNotificationEntities: List<SmsNotificationEntity>,
        watchChangeObserver: WatchChangeObserver): Watch {

    val notifications = ArrayList<Notification>()

    emailNotificationEntities
            .map { emailNotificationMapper(it) }
            .forEach { notifications.add(it) }

    smsNotificationEntities
            .map { smsNotificationMapper(it) }
            .forEach { notifications.add(it) }

    return Watch(watchEntity.id, watchEntity.heartbeat, notifications, watchChangeObserver)
}

fun emailNotificationMapper(entity: EmailNotificationEntity) = EmailNotification(
        entity.id,
        EmailNotificationTarget(entity.recipientEmail),
        entity.emailSubject,
        Paths.get(entity.emailMessageResourcePath),
        entity.heartbeatToTriggerMillis,
        entity.lastNotification)

fun smsNotificationMapper(entity: SmsNotificationEntity) = SmsNotification(
        entity.id,
        SmsNotificationTarget(entity.recipientPhoneNumber),
        Paths.get(entity.smsMessageResourcePath),
        entity.heartbeatToTriggerMillis,
        entity.lastNotification)

fun reportStateMapper(entity: ReportStateEntity) = ReportState(
        entity.id,
        entity.lastEmail,
        entity.lastSms,
        entity.emailFrequencyMillis,
        entity.smsFrequencyMillis)

fun watchEntityMapper(watch: Watch) = WatchEntity(
        watch.id,
        watch.heartbeat)

fun emailNotificationEntityMapper(watch: Watch, notification: EmailNotification) = EmailNotificationEntity(
        notification.id,
        notification.notificationTarget.emailAddress,
        notification.emailSubject,
        notification.emailMessageResourcePath.toString(),
        notification.heartbeatToTriggerMillis,
        notification.lastNotification,
        WatchEntity(watch.id, watch.heartbeat))

fun emailNotificationEntityMapper(watchEntity: WatchEntity, notification: EmailNotificationConfig) = EmailNotificationEntity(
        UUID.randomUUID(),
        notification.recipientEmail,
        notification.emailSubject,
        notification.emailMessageResourcePath,
        minutesToMillis(notification.heartbeatToTriggerMinutes),
        Instant.now(),
        watchEntity)

fun smsNotificationEntityMapper(watch: Watch, notification: SmsNotification) = SmsNotificationEntity(
        notification.id,
        notification.notificationTarget.phoneNumber,
        notification.smsMessageResourcePath.toString(),
        notification.heartbeatToTriggerMillis,
        notification.lastNotification,
        WatchEntity(watch.id, watch.heartbeat))

fun smsNotificationEntityMapper(watchEntity: WatchEntity, notification: SmsNotificationConfig) = SmsNotificationEntity(
        UUID.randomUUID(),
        notification.recipientPhoneNumber,
        notification.smsMessageResourcePath,
        minutesToMillis(notification.heartbeatToTriggerMinutes),
        Instant.now(),
        watchEntity)

fun reportStateEntityMapper(reportState: ReportState) = ReportStateEntity(
        reportState.id,
        reportState.lastEmail,
        reportState.lastSms,
        reportState.emailFrequencyMillis,
        reportState.smsFrequencyMillis)