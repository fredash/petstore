'use strict';
 
App.controller('PetController', ['$scope', '$timeout', 'PetService', function($scope, $timeout, PetService) {
          var self = this;
          self.pet={id:null,name:'',status:'',category:''};
          self.pets=[];
          self.showDeletePermissionWarning = false;
          self.showUpdatePermissionWarning = false;
          self.editMode = false;

          self.fetchAllPets = function(){
              PetService.fetchAllPets()
                  .then(
                               function(d) {
                                    self.pets = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Pets');
                                }
                       );
          };
            
          self.createPet = function(pet){
              PetService.createPet(pet)
                      .then(
                    		  self.fetchAllPets, 
                              function(errResponse){
		                    	  self.showCreatePermissionWarning = true;
		                    	  $timeout(function() {
		                    		  self.showCreatePermissionWarning = false;
		                    	  }, 2000);
                                   console.error('Error while creating Pet.');
                              } 
                  );
          };
 
         self.updatePet = function(pet, id){
              PetService.updatePet(pet, id)
                      .then(
                              self.fetchAllPets, 
                              function(errResponse){
                            	  self.showUpdatePermissionWarning = true;
                            	  $timeout(function() {
                            		  self.showUpdatePermissionWarning = false;
                            	  }, 2000);
                                  console.error('Error while updating Pet.');
                              } 
                  );
          };
 
         self.deletePet = function(id){
              PetService.deletePet(id)
                      .then(
                              self.fetchAllPets, 
                              function(errResponse){
                            	  self.showDeletePermissionWarning = true;
                            	  $timeout(function() {
                            		  self.showDeletePermissionWarning = false;
                            	  }, 2000);
                                   console.error('Error while deleting Pet.');
                              } 
                  );
          };
 
          self.fetchAllPets();
 
          self.submit = function() {
              if(self.editMode==false){
                  console.log('Saving New Pet', self.pet);    
                  self.createPet(self.pet);
                  self.fetchAllPets();
              } else {
                  self.updatePet(self.pet, self.pet.id);
                  console.log('Pet updated with id ', self.pet.id);
              }
              self.reset();
          };
               
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.pets.length; i++){
                  if(self.pets[i].id === id) {
                     self.pet = angular.copy(self.pets[i]);
                     self.editMode = true;
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.pet.id === id) {//clean form if the pet to be deleted is shown there.
                 self.reset();
              }
              self.deletePet(id);
          };
 
           
          self.reset = function(){
              self.pet={id:null,name:'',status:'',category:''};
              self.editMode = false;
              $scope.myForm.$setPristine(); //reset Form
          };
 
      }]);
