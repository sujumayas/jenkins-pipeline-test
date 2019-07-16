package com.sujumayas.clientsmicroservice.controller;

import com.sujumayas.clientsmicroservice.model.Client;
import com.sujumayas.clientsmicroservice.service.ClientService;
import com.sujumayas.clientsmicroservice.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Request;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * ClientController
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "Client Microservice", description = "A simple API to get Clients List, know when they are probably going to die, and some other stuff")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * Get All Clients
     * 
     * Shows all clients
     * 
     * @return List<Client> toJSON
     */
    @ApiOperation(value = "View a list of all Clients + Possible Death Date", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(method = RequestMethod.GET, value ="clients/list")
    public List<Client> getAllClients(){
        return clientService.getAllClients(); 
    }

    /**
     * Get a single Client
     * 
     * Shows a Client
     * 
     * @return Client toJSON
     */
    @ApiOperation(value = "Get a single Client by Id")
    @RequestMapping(method = RequestMethod.GET, value ="clients/{id}")
    public Client getClient(
            @ApiParam(value = "Id of the Client you want to see.", required = true) @PathVariable Long id){
        return clientService.getClient(id);
    }

    /**
     * Get KPIS 
     * 
     * Shows KPIs: Average Age and Standard deviation 
     * 
     * @return String 
     */
    @ApiOperation(value = "Get Average Age and Standard Deviation")
    @RequestMapping(method = RequestMethod.GET, value="clients/kpis")
    public String getKPIs() {
        clientService.updateClientsAverageAgeAndStandardDeviation(); // This should be a CRON job!!
        Number average = clientService.getClientsAverageAge();
        Number standardDeviation = clientService.getStandardDeviation();
        return "Average: " + average + ", and the Standard Deviation is: " + standardDeviation;
    }
    
    /**
     * Create Client
     * 
     * POST Method that lets you create a client
     * 
     * @return void
     */
    @ApiOperation(value = "Add a client.")
    @RequestMapping(method=RequestMethod.POST, value="clients/create")
    public void createClient(
            @ApiParam(value = "Client object you want to add. Please do not add an ID nor AGE they will be autogenerated by the database.", required = true) @RequestBody Client client){
        clientService.createClient(client);
    }
    
    /**
     * Update a Client
     * 
     * PUT Method that lets you update a client by id
     * 
     * @return void
     */
    @ApiOperation(value = "Update a client by Id")
    @RequestMapping(method = RequestMethod.PUT, value = "clients/update/{id}")
    public void updateClient(
            @ApiParam(value = "Client object with properties you want to update. Please do not add an AGE it will be autogenerated by the database if the BirthDate changes", required = true) @RequestBody Client client, 
            @ApiParam(value = "The Id of the Client you want to update", required = true) @PathVariable Long id) {
        clientService.updateClient(client, id);
    }

    /**
     * Delete a Client
     * 
     * DELETE Method that lets you delete a client by id
     * 
     * @return void
     */
    @ApiOperation(value = "Delete a Client by Id")
    @RequestMapping(method = RequestMethod.DELETE, value = "clients/delete/{id}")
    public void deleteClient(
            @ApiParam(value = "The Id of the Client you want to delete", required = true) @PathVariable Long id) {
        clientService.deleteClient(id);
    }
   
}