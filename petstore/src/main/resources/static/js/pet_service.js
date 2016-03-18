'use strict';

App.factory('PetService', ['$http', '$q', function($http, $q){
 
    return {

            fetchAllPets: function() {
                    return $http.get('http://localhost:8080/pets/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching pets');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            createPet: function(pet){
                    return $http.post('http://localhost:8080/pets/', pet)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating pet');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            updatePet: function(pet, id){
                    return $http.put('http://localhost:8080/pets/'+id, pet)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating pet');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            deletePet: function(id){
                    return $http.delete('http://localhost:8080/pets/'+id)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting pet');
                                        return $q.reject(errResponse);
                                    }
                            );
            }
         
    };
 
}]);