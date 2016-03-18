package org.petstore.sample1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value = "/pets", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Api(value = "pets", description = "Pets API")
public class PetRestController {

    @Autowired
    protected PetRepository petRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve all pets", notes = "Retrieve all pets of the collection.")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Iterable<Pet> pets() {
    	// findAll method inherited from CrudRepository
        return petRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Find a pet based on its id", notes = "Find a pet based on its id number.")
    public Pet pet(@PathVariable("id") Integer id) {
    	// findOne method inherited from CrudRepository
    	return petRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a pet", notes = "Create a new pet if it does not already exist.")
    public Pet createPet(@RequestBody Pet pet) {
    	// check pet existence
    	// findOne method inherited from CrudRepository
    	if (pet.getId() != null){
	    	Pet checkPet = petRepository.findOne(pet.getId());
	    	if (checkPet != null)
	    		return checkPet;
    	}

    	return petRepository.save(pet);
    }
 
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update a pet", notes = "Update an existing pet.")
    public ResponseEntity<?> updatePet(@PathVariable("id") String id, @RequestBody Pet pet) {
    	// findOne method inherited from CrudRepository
    	Pet checkPet = petRepository.findOne(pet.getId());
    	if (checkPet != null)
    		return new ResponseEntity<>(petRepository.save(pet), HttpStatus.OK);
    	else return new ResponseEntity<>(checkPet, HttpStatus.OK);
    }

    @RestResource(exported = false)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete a pet based on its id", notes = "Delete a pet based on its id number.")
    public ResponseEntity<String> deletePet(@PathVariable("id") Integer id) {
        try {
        	// delete method inherited from CrudRepository
            petRepository.delete(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } 
    }

}
 