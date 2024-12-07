package com.justinligny.cqrs.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <h1>EntityCommand Class</h1><br>
 *
 * <p>The {@code EntityCommand} class is a base class representing a command
 * that can be used in various handler classes for entity operations.</p><br>
 *
 * <p>This class contains the identifier of the entity (id) and is typically used
 * as the base class for various command classes, such as those for creating, updating,
 * or deleting entities.</p><br>
 *
 * @since 1.0.0
 */
public class EntityCommand {

    /**
     * <h2>id</h2>
     * <p>The identifier of the entity.</p><br>
     * <p>This field is ignored during JSON serialization/deserialization
     * using the {@link JsonIgnore} annotation.</p><br>
     * @since 1.0.0
     */
    @JsonIgnore
    public Long id;
}
