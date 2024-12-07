package com.justinligny.cqrs.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>EntityGetAllOutput Class</h1><br>
 *
 * <p>The {@code EntityGetAllOutput} class is a container used for handling a list
 * of entities in response to a "get all" type of query.</p><br>
 *
 * <p>This class holds a list of entities, where each entity extends {@code EntityOutput}.
 * It provides methods for adding entities to the list, typically used in paginated responses.</p><br>
 *
 * @param <TDbEntity> the type of entity that extends {@link EntityOutput}.
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
public class EntityGetAllOutput<TDbEntity extends EntityOutput> {

    /**
     * <h2>entities</h2>
     * <p>A list holding entities returned by a query handler.</p><br>
     * <p>This list is used to hold all entities that match a "get all" query.</p><br>
     * @since 1.0.0
     */
    public List<TDbEntity> entities = new ArrayList<>();

    /**
     * <h2>addAll</h2>
     * <p>Method to add a list of entities to the {@code entities} list.</p><br>
     * <p>This method is used to populate the list of entities when handling a query.</p><br>
     *
     * @param entities a list of entities to be added to the {@code entities} list.
     * @since 1.0.0
     */
    public void addAll(List<TDbEntity> entities){
        this.entities.addAll(entities);
    }

}
