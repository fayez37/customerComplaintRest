'use strict';

App.controller('TicketController', ['$scope', '$window','TicketService', function($scope, $window, TicketService) {
          var self = this;
          self.ticket={id:null,createdBy:'',assignedTo:'',status:'NEW',complaintArea:'ACCOUNT',comments:[]};
          self.newComment={id:null,comments:''};
          self.tickets=[];
              
          self.fetchAllTickets = function(){
              TicketService.fetchAllTickets()
                  .then(
      					       function(d) {
      						        self.tickets = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Tickets');
            					}
      			       );
          };
           
          self.fetchTicketWithId = function(id){
              TicketService.fetchTicketWithId(id)
                  .then(
      					       function(d) {
      						        self.ticket = d;
      						        $scope.statusValue = self.ticket.status;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Ticket.');
            					}
      			       );
          };
          
          self.createTicket = function(ticket){
              TicketService.createTicket(ticket)
		              .then(
                      self.fetchAllTickets, 
				              function(errResponse){
					               console.error('Error while creating Ticket');
				              }	
                  );
          };
          
          self.addComment = function(){
              TicketService.addComment(self.newComment,self.ticket.id)
		              .then(
                      self.fetchTicketWithId(self.ticket.id), 
				              function(errResponse){
					               console.error('Error while creating Comment');
				              }	
                  );
          };
          
         self.updateTicket = function(ticket, id){
              TicketService.updateTicket(ticket, id)
		              .then(
				              self.fetchAllTickets, 
				              function(errResponse){
					               console.error('Error while updating Ticket.');
				              }	
                  );
          };

         self.deleteTicket = function(id){
              TicketService.deleteTicket(id)
		              .then(
				              self.fetchAllTickets, 
				              function(errResponse){
					               console.error('Error while deleting Ticket.');
				              }	
                  );
          };

          self.fetchAllTickets();

          self.submit = function() {
              if(self.ticket.id==null){
                  console.log('Saving New Ticket', self.ticket);    
                  self.createTicket(self.ticket);
              }else{
                  self.updateTicket(self.ticket, self.ticket.id);
                  console.log('Ticket updated with id ', self.ticket.id);
              }
              self.reset();
          };
          $scope.checkIsEditable =  function() {
        	  if($scope.statusValue == "CLOSED")
        	  {
        		  return true;
        	  }
        	  return false;
          }
          
          $scope.checkIsCommentAddable =  function() {
        	  if($scope.statusValue == "CLOSED" || !self.ticket.id)
        	  {
        		  return true;
        	  }
        	  return false;
          }
          
          $scope.status = ['NEW','OPEN','CLOSED'];
          
          $scope.compalintArea = ['ACCOUNT','DELIVERY','PAYMENT','ENHANCEMENTS','ITEMS'];
              
          self.reset = function(){
        	  self.ticket={id:null,createdBy:'',assignedTo:'',status:'NEW',complaintArea:'ACCOUNT'};
        	  self.newComment={id:null,comments:''};
        	  $scope.statusValue = "";
          };

      }]);
