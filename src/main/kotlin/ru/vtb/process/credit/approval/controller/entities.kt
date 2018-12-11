package ru.vtb.process.credit.approval.controller

import io.swagger.annotations.ApiModelProperty

data class AddApproverRequest(
        @ApiModelProperty(example = "someUser")
        val userId: String
)
