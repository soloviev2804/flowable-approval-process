package ru.vtb.process.credit.approval.task.listener

import org.flowable.engine.ProcessEngine
import org.flowable.engine.delegate.TaskListener
import org.flowable.task.service.delegate.DelegateTask
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Suppress("unused")
@Component("prepareDocTaskListener")
class PrepareDocTaskListener : TaskListener {

    override fun notify(delegateTask: DelegateTask?) {
        delegateTask?.apply {
            val approvers = getVariable("approvers") as String
            setVariable("approverList", approvers.split("\\s*,\\s*".toRegex()))
        }
    }
}
