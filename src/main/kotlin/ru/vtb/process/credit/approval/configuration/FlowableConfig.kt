package ru.vtb.process.credit.approval.configuration

import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.vtb.process.credit.approval.task.listener.PrepareDocTaskListener
import javax.sql.DataSource

@Configuration
class FlowableConfig {

//    @Bean
//    fun dataSource(): DataSource {
//        val jdbcUrl = "jdbc:postgresql://127.0.0.1:5433/flowable"
//        val jdbcDriver = "org.postgresql.Driver"
//        val jdbcUsername = "flowable"
//        val jdbcPassword = "flowable"
//
//        val dataSource = HikariDataSource()
//        dataSource.jdbcUrl = jdbcUrl
//        dataSource.driverClassName = jdbcDriver
//        dataSource.username = jdbcUsername
//        dataSource.password = jdbcPassword
//        dataSource.maximumPoolSize = 50
//
//        return dataSource
//    }

//    @Bean
//    fun prepareDocTaskListener(): PrepareDocTaskListener {
//        return PrepareDocTaskListener()
//    }
}
