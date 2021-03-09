package io.celeri.nok.dao

import java.time.Instant

class DataDao(
        lastHeartbeatInstant: Instant,
        reportState: ReportStateDao,
        notificationStates: List<NotificationStateDao>
)