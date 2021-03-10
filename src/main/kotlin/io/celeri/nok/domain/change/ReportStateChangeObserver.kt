package io.celeri.nok.domain.change

import io.celeri.nok.domain.ReportState

interface ReportStateChangeObserver {

    fun reportEmailSent(reportState: ReportState)
    fun reportSmsSent(reportState: ReportState)
}