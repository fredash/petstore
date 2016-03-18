package org.petstore.sample1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Integer> {
    public Iterable<Pet> findPetsByName(@Param("name") String name);
} 