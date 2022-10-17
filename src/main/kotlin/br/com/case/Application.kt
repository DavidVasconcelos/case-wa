package br.com.case

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application> {
        if (args.isNotEmpty() && args[0] == "migrate") {
            this.setAdditionalProfiles("dbmigration")
        }
    }
}
