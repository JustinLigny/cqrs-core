package com.justinligny.cqrs.handlers.queries;

import com.justinligny.cqrs.entities.DbEntity;
import com.justinligny.cqrs.exceptions.EntityNotFoundException;
import com.justinligny.cqrs.interfaces.IEntityRepository;
import com.justinligny.cqrs.interfaces.IQueryHandler;
import com.justinligny.cqrs.outputs.EntityOutput;
import com.justinligny.cqrs.queries.EntityQuery;
import org.modelmapper.ModelMapper;

/**
 *  <h1>GenericGetByIdHandler Class</h1> <br>
 *
 *  <p>The {@code GenericGetByIdHandler} class is an abstract handler designed
 *  to retrieve an entity by its identifier. It is part of the query handling
 *  layer and provides a generic implementation for fetching entities based on
 *  their unique {@code id}.</p> <br>
 *
 *  <p>This class extends {@code GenericQueryHandler} and implements the
 *  {@code IQueryHandler} interface, which means it is specifically tailored for
 *  handling queries that retrieve entities by ID.</p> <br>
 *
 *  <p>It leverages the {@code IEntityRepository} to perform the database query
 *  for retrieving the entity by its ID. If the entity is not found, an
 *  {@code EntityNotFoundException} is thrown.</p> <br>
 *
 *  <p>The {@code handle} method in this class processes the query of type
 *  {@code TQuery}, which contains the ID of the entity to be retrieved. If
 *  the entity is found, it is then mapped to the output DTO {@code TOutput}
 *  using {@code ModelMapper}.</p> <br>
 *
 *  <p>In addition to the core functionality, this class provides an optional
 *  method {@code beforeEntityReturn}, which can be overridden by subclasses to
 *  perform any additional actions before the entity is returned.</p> <br>
 *
 *  <p>This class reduces duplication of code across different query handlers
 *  by providing a standard structure for fetching an entity by ID and mapping
 *  it to a DTO.</p> <br>
 *
 *  @param <TDbEntity> The type of the database entity, which must extend {@code DbEntity}.
 *  @param <TQuery> The type of the query that contains the ID to retrieve the entity.
 *  @param <TOutput> The type of the output DTO to which the entity will be mapped.
 *
 *  @author Justin Ligny
 *  @since 1.0.1
 */
public abstract class GenericGetByIdHandler<
        TDbEntity extends DbEntity,
        TQuery extends EntityQuery,
        TOutput extends EntityOutput>
        extends GenericQueryHandler<TDbEntity, TOutput>
        implements IQueryHandler<TQuery, TOutput> {

    /**
     * Constructor to initialize {@code GenericGetByIdHandler} with required dependencies.
     *
     * @param repository  The repository instance for accessing the database entities.
     * @param modelMapper The ModelMapper instance for mapping entities to output DTOs.
     * @param outputClass The class type of the output DTO.
     *
     * @since 1.0.1
     */
    public GenericGetByIdHandler(
            IEntityRepository<TDbEntity> repository,
            ModelMapper modelMapper,
            Class<TOutput> outputClass) {
        super(repository, modelMapper, outputClass);
    }

    /**
     * Handles the query to retrieve an entity by ID.
     *
     * @param input The query containing the ID of the entity to retrieve.
     * @return The mapped output DTO containing the entity data.
     * @throws EntityNotFoundException if no entity is found with the provided ID.
     *
     * @since 1.0.1
     */
    @Override
    public final TOutput handle(TQuery input) {

        TDbEntity entityDatabase = repository.findById(input.id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with ID " + input.id));

        beforeEntityReturn(entityDatabase);

        return modelMapper.map(entityDatabase, outputClass);
    }
    /**
     * Hook method that can be overridden by subclasses to perform additional actions
     * before returning the entity.
     *
     * @param entityDatabase The entity that has been retrieved from the database.
     *
     * @since 1.0.1
     */
    public void beforeEntityReturn(TDbEntity entityDatabase) {}
}
