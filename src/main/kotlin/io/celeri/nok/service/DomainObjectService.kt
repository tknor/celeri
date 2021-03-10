package io.celeri.nok.service

import io.celeri.nok.domain.Watch
import io.celeri.nok.domain.change.ReportStateChangeObserver
import io.celeri.nok.domain.change.WatchChangeObserver

interface DomainObjectService: WatchChangeObserver, ReportStateChangeObserver {

    fun watch(): Watch
}