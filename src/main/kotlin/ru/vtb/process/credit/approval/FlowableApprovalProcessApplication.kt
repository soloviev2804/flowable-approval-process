package ru.vtb.process.credit.approval

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlowableApprovalProcessApplication

fun main(args: Array<String>) {
    runApplication<FlowableApprovalProcessApplication>(*args)
}
