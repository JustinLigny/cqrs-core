package com.justinligny.cqrs.queries;

import org.springframework.data.domain.Pageable;

/**
 * <h1>EntityGetAllQuery Class</h1><br>
 *
 * <p>The {@code EntityGetAllQuery} class is used to represent a query for retrieving
 * multiple entities with pagination support.</p><br>
 *
 * <p>This class is typically used to fetch a list of entities with specific pagination parameters.</p><br>
 *
 * @since 1.0.0
 */
public class EntityGetAllQuery {

    /**
     * <h2>pageable</h2>
     * <p>Pagination information for the query, including the page number, page size,
     * and sorting options.</p><br>
     * <p>This field uses the {@link Pageable} interface, which is part of Spring Data's
     * pagination system.</p><br>
     *
     * @since 1.0.0
     */
    public Pageable pageable;
}
