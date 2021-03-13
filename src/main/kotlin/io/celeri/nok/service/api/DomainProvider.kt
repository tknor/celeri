package io.celeri.nok.service.api

import io.celeri.nok.domain.ReportState
import io.celeri.nok.domain.Watch
import io.celeri.nok.domain.change.ReportStateChangeObserver
import io.celeri.nok.domain.change.WatchChangeObserver

interface DomainProvider: WatchChangeObserver, ReportStateChangeObserver {

    fun watch(): Watch?
    fun reportState(): ReportState?
}