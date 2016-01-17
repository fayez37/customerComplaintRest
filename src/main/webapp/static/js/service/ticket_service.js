'use strict';
App.factory('TicketService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllTickets: function() {
					return $http.get('http://localhost:8080/CustomerComplaintSystemRest/tickets/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching tickets');
										return $q.reject(errResponse);
									}
							);
			},
			fetchTicketWithId: function(id) {
				return $http.get('http://localhost:8080/CustomerComplaintSystemRest/ticket/'+id)
						.then(
								function(response){
									/*$http.get('http://localhost:8080/CustomerComplaintSystemRest/comments/'+id).then( function ( comments ) {
										response.data.comments = comments;
									},function(errResponse){
										console.error('Error while fetching comments');
										return $q.reject(errResponse);
									});*/
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while fetching ticket.');
									return $q.reject(errResponse);
								}
						);
		},
		    createTicket: function(ticket){
					return $http.post('http://localhost:8080/CustomerComplaintSystemRest/ticket/', ticket)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating ticket');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    addComment: function(newComment,id){
				return $http.post('http://localhost:8080/CustomerComplaintSystemRest/addComment/'+id, newComment)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while creating comment');
									return $q.reject(errResponse);
								}
						);
		    },
		    
		    updateTicket: function(ticket, id){
					return $http.put('http://localhost:8080/CustomerComplaintSystemRest/ticket/'+id, ticket)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating ticket');
										return $q.reject(errResponse);
									}
							);
			}
	};

}]);
