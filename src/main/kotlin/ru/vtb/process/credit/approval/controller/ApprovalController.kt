package ru.vtb.process.credit.approval.controller

import io.swagger.annotations.ApiParam
import org.flowable.engine.RuntimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ApprovalController {

    @Autowired
    lateinit var runtimeService: RuntimeService

    @PostMapping(value = ["/approval-process/{processId}/approvers"])
    @ResponseStatus(value = HttpStatus.OK)
    fun addApprover(@ApiParam(name = "processId") @PathVariable processId: String, @RequestBody body: AddApproverRequest?) {
        val userId = body?.userId
        runtimeService.addMultiInstanceExecution("documentApproval", processId, mapOf("user" to userId))
    }
}
