package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("worker")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkerEntity {

    @Id
    private ObjectId id;
    private String email;
    private String name;
    private String password;

    public WorkerEntity(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
