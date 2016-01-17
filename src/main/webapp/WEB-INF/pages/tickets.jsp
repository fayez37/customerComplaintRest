<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Tickets</title>
	</head>
	<body ng-app="myApp">
    	<div ng-controller="TicketController as ctrl">
      		<div style="float:left">
                  		<form ng-submit="ctrl.submit()" name="myForm">
                      		<input type="hidden" ng-model="ctrl.ticket.id" />
                       		<div style="padding-top:20px;padding-bottom:5px">
                           		<label style="padding-right:50px">Created By</label>
                               	<input type="text" ng-model="ctrl.ticket.createdBy" name="createdBy" placeholder="Enter creator name" required ng-disabled="checkIsEditable()"/>
                               	<div ng-show="myForm.$dirty">
                                  	<span ng-show="myForm.createdBy.$error.required">This is a required field</span>
                               	</div>
                       		</div>
			                <div style="padding-top:5px;padding-bottom:5px">
                            	<label style="padding-right:41px">Assigned To</label>
                                <input type="text" ng-model="ctrl.ticket.assignedTo" name="assignedTo" placeholder="Enter assignee name" required ng-disabled="checkIsEditable()"/>
                                <div ng-show="myForm.$dirty">
                                	<span ng-show="myForm.assignedTo.$error.required">This is a required field</span>
                               	</div>
		                    </div>
                      		<div style="padding-top:5px;padding-bottom:5px">
	                      		<label style="padding-right:84px">Status</label>
                      	  		<select ng-model="ctrl.ticket.status" ng-options="opt as opt for opt in status" ng-init="selected='ctrl.ticket.status'" ng-disabled="checkIsEditable()"></select>
                      		</div>
	                        <div style="padding-top:5px;padding-bottom:5px">
                         		<label style="padding-right:20px">Complaint Area</label>
	                   	  		<select ng-model="ctrl.ticket.complaintArea" ng-options="opt as opt for opt in compalintArea" ng-init="selected='ctrl.ticket.complaintArea'" ng-disabled="checkIsEditable()"></select>
                      		</div>
                          	<div style="padding-left: 87px;padding-top: 5px;">
                              <input type="submit"  value="{{!ctrl.ticket.id ? 'Add' : 'Update'}}" ng-disabled="checkIsEditable()">
                              <button type="button" ng-click="ctrl.reset()">Reset Form</button>
                          	</div>
                  		</form>
          		<div style="padding-top:20px">
              		<div><span>Comments</span></div>
              		<form ng-submit="ctrl.addComment()" name="myCommentForm">
                    	<div style="padding-left:5px;padding-top: 5px;;padding-bottom: 5px;">	
                        	<label style="padding-right:10px">New Comment</label>
                            <input type="text" ng-model="ctrl.newComment.comments" name="comment" placeholder="Enter Comment" required ng-disabled="checkIsCommentAddable()"/>
                        </div>
                    	<div style="padding-left: 87px;padding-top: 5px;">
                      		<input type="submit"  value="Add" ng-disabled="checkIsCommentAddable()">
                    	</div>
                 	</form>
	              	<div style="padding-top:20px">
                  		<table>
                        	<tr ng-repeat="commentObj in ctrl.ticket.comments">
                              <td><span ng-bind="commentObj.comments"></span></td>
                              <td><span ng-bind="commentObj.commentCreateTime | date:'yyyy-MM-dd HH:mm:ss Z'"></span></td>
	                        </tr>
                  		</table>
              		</div>
          		</div>
          	</div>
       		<div style="float:rightfloat: right;padding-left: 410px">
              <div style="padding-top:20px;padding-bottom:5px"><span style="font-size: 20px">List of Tickets </span></div>
              <div>
                  <table>
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Complaint Area</th>
                              <th>Status</th>
                              <th>Created By</th>
                              <th>Assigned To</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="ticketObj in ctrl.tickets">
                              <td><span ng-bind="ticketObj.id"></span></td>
                              <td><span ng-bind="ticketObj.complaintArea"></span></td>
                              <td><span ng-bind="ticketObj.status"></span></td>
                              <td><span ng-bind="ticketObj.createdBy"></span></td>
                              <td><span ng-bind="ticketObj.assignedTo"></span></td>
                              <td>
                              	<button type="button" ng-click="ctrl.fetchTicketWithId(ticketObj.id)">View</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js'/>"></script>
      <script src="<c:url value='/static/js/service/ticket_service.js'/>"></script>
      <script src="<c:url value='/static/js/controller/ticket_controller.js'/>"></script>
	</body>
</html>