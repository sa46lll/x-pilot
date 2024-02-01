package org.xangle.xpilot.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoAuditing
@SpringBootApplication
@EnableMongoRepositories
public class XPilotSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XPilotSchedulerApplication.class, args);
    }

}
