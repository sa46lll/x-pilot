package org.xangle.xpilot.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Document("worker")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkerEntity {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field("email")
    private String email;

    @Field("name")
    private String name;

    @Field("password")
    private String password;

    @CreatedDate
    @Field("created_time")
    private Instant createdTime;

    @LastModifiedDate
    @Field("updated_time")
    private Instant updatedTime;

    public WorkerEntity(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void encryptPassword(String password) {
        this.password = password;
    }
}
