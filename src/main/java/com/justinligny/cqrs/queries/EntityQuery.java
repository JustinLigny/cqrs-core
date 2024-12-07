package com.justinligny.cqrs.queries;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <h1>EntityQuery Class</h1><br>
 *
 * <p>The {@code EntityQuery} class is used to represent a query that can be
 * used for retrieving entities, typically by their identifier or other criteria.</p><br>
 *
 * <p>This class extends the concept of {@code EntityCommand} but is intended
 * specifically for query operations.</p><br>
 *
 * @since 1.0.0
 */
public class EntityQuery {

    /**
     * <h2>id</h2>
     * <p>The identifier of the entity for which the query is made.</p><br>
     * <p>This field is ignored during JSON serialization/deserialization
     * using the {@link JsonIgnore} annotation.</p><br>
     * @since 1.0.0
     */
    @JsonIgnore
    public Long id;
}

