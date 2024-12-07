package com.justinligny.cqrs.outputs;

import java.sql.Timestamp;

/**
 * <h1>EntityOutput Class</h1><br>
 *
 * <p>The {@code EntityOutput} class serves as a simple DTO (Data Transfer Object)
 * for representing basic entity details that are typically returned by query handlers.</p><br>
 *
 * <p>This class is used to store the common attributes of an entity, such as its ID,
 * and timestamps for creation and last update. It is meant to be extended or used as
 * part of the output in various handler responses.</p><br>
 *
 * @since 1.0.0
 */
public class EntityOutput {

    /**
     * <h2>id</h2>
     * <p>The unique identifier of the entity.</p><br>
     * <p>This field represents the primary key of the entity and is used
     * to distinguish different instances of the entity.</p><br>
     * @since 1.0.0
     */
    public Long id;

    /**
     * <h2>updatedAt</h2>
     * <p>The timestamp when the entity was last updated.</p><br>
     * <p>This field is automatically updated whenever the entity is modified.</p><br>
     * @since 1.0.0
     */
    public Timestamp updatedAt;

    /**
     * <h2>createdAt</h2>
     * <p>The timestamp when the entity was created.</p><br>
     * <p>This field is set when the entity is first created in the system.</p><br>
     * @since 1.0.0
     */
    public Timestamp createdAt;
}
