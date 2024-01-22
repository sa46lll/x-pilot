package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("workers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkerEntity {

    @Id
    private String email;
    private String name;
    private String password;

    public WorkerEntity(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
