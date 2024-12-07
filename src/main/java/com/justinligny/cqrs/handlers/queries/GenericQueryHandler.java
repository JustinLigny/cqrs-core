package com.justinligny.cqrs.handlers.queries;

import com.justinligny.cqrs.entities.DbEntity;
import com.justinligny.cqrs.interfaces.IEntityRepository;
import org.modelmapper.ModelMapper;

/**
 *  <h1>GenericQueryHandler Class</h1> <br>
 *
 *  <p>The {@code GenericQueryHandler} class is an abstract base class designed
 *  to handle query operations for entities in a generic and reusable manner. It
 *  is intended to be extended by specific query handler classes and provides a
 *  standard structure for interacting with a data repository and mapping entities
 *  to output DTOs.</p> <br>
 *
 *  <p>This class leverages the {@code IEntityRepository} for performing data
 *  access operations on entities of type {@code TDbEntity}, which must extend
 *  {@code DbEntity}. It also uses {@code ModelMapper} to map entities to their
 *  corresponding output class {@code TOutput}.</p> <br>
 *
 *  <p>This class is not intended to be used directly but serves as a base for
 *  more specific query handler implementations.</p> <br>
 *
 *  <p>This class ensures consistency and reduces boilerplate code across different
 *  query handler implementations.</p> <br>
 *
 *  @param <TDbEntity> The type of the database entity that extends {@code DbEntity}.
 *  @param <TOutput> The type of the output DTO to which the entity will be mapped.
 *
 *  @author Justin Ligny
 *  @since 1.0.1
 */
public abstract class GenericQueryHandler<
        TDbEntity extends DbEntity,
        TOutput> {

    /**
     * Repository for performing CRUD operations on {@code TDbEntity}.
     */
    protected final IEntityRepository<TDbEntity> repository;

    /**
     * ModelMapper instance for mapping {@code TDbEntity} to {@code TOutput}.
     */
    protected final ModelMapper modelMapper;

    /**
     * The class type of the output DTO {@code TOutput}.
     */
    protected final Class<TOutput> outputClass;

    /**
     * Constructor to initialize {@code GenericQueryHandler} with required dependencies.
     *
     * @param repository  The repository instance for accessing database entities.
     * @param modelMapper The ModelMapper instance for mapping entities to output DTOs.
     * @param outputClass The class type of the output DTO.
     *
     * @since 1.0.1
     */
    public GenericQueryHandler(IEntityRepository<TDbEntity> repository, ModelMapper modelMapper, Class<TOutput> outputClass) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.outputClass = outputClass;
    }
}
