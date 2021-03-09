package io.celeri.nok.service

import io.celeri.nok.domain.Watch
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DefaultDomainObjectService: DomainObjectService {

    @Value("\${lab.beansAtStart}")
    private val beansAtStart: Boolean = false // default value is not used, it is there to satisfy compiler

    override fun watch(): Watch {
        TODO("not implemented")
    }
}