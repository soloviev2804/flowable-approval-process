package ru.vtb.process.credit.approval

import org.flowable.engine.ProcessEngine
import org.flowable.engine.test.Deployment
import org.flowable.engine.test.FlowableTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInstance


//@RunWith(SpringRunner::class)
//@SpringBootTest
@FlowableTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FlowableApprovalProcessApplicationTests {


    @org.junit.jupiter.api.Test
    @Deployment(resources = ["processes/CreditApprovalProcess.bpmn"])
    fun testSimpleProcess(processEngine: ProcessEngine) {
        val runtimeService = processEngine.runtimeService
        val taskService = processEngine.taskService

        // Запускаем процесс
        runtimeService.startProcessInstanceByKey("creditApprovalProcess")
        val processInstance = runtimeService.createProcessInstanceQuery().singleResult()

        // Менеджер берет таск и назначает на Васю
        val assigmentTask = taskService.createTaskQuery().taskCandidateGroup("Managers").singleResult()
        taskService.claim(assigmentTask.id, "Boss")
        runtimeService.setVariable(processInstance.id, "worker", "Vasya")
        taskService.complete(assigmentTask.id)

        // Вася готовит документ и выбирает согласующих
        val prepareDocTask = taskService.createTaskQuery().taskAssignee("Vasya").singleResult()
        runtimeService.setVariable(processInstance.id, "approvers", "Petya, Lena, Vera")
        taskService.complete(prepareDocTask.id)

        // Petya, Lena, согласуют документ
        listOf("Petya", "Lena").forEach{
            val approveTask = taskService.createTaskQuery().taskAssignee(it).singleResult()
            taskService.complete(approveTask.id)
        }

        // Вспомнили что Аганеса забыли
        val execution = runtimeService.addMultiInstanceExecution("documentApproval", processInstance.id, mapOf("user" to "Aganes"))

        // Vera, Aganes согласуют документ
        listOf("Vera", "Aganes").forEach{
            val approveTask = taskService.createTaskQuery().taskAssignee(it).singleResult()
            taskService.complete(approveTask.id)
        }

        Assertions.assertEquals(0, runtimeService.createProcessInstanceQuery().count())
    }

}
