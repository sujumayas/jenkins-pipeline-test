package com.sujumayas.clientsmicroservice.service;

import com.sujumayas.clientsmicroservice.model.Client;
import com.sujumayas.clientsmicroservice.repository.ClientRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.ipc.http.HttpSender.Request;
import net.bytebuddy.implementation.bytecode.Throw;

/**
 * Client Service
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /** TODO : All this should be in DB later :D */
    private double clientsAverageAge;
    private long clientListSize = 2L;
    private double clientAges[];
    private double deviation;
    
    /**
     * Get All Clients
     * 
     * @return List<Client>
     */
    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();        
        clientRepository.findAll().forEach(clients::add); //Thank you method references in lamda expressions
        return clients;
    }
    
    /**
     * Get Single Client
     * 
     * @return Client
     */
    public Client getClient(Long id){
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client theClient = client.get();
            return theClient;
        } else {
            return null;
        }
    }
    
    /**
     * Create Client
     * 
     * 
     * @return void
     */
    public void createClient(Client client){
        String aproxDeathDate = seTAproxDeathDate(client.getBirthDate(), 75);
        double age = calculateAge(client.getBirthDate());
        client.setAproxDeathDate(aproxDeathDate);
        client.setAge(age);
        clientRepository.save(client);
    }
    
    /**
     * Update Client
     * 
     * TODO: Restrict option to change ID TODO: Do not replace empty keys
     * 
     * @return void
     */
    public void updateClient(Client client, Long id) {
        String aproxDeathDate = seTAproxDeathDate(client.getBirthDate(), 75);
        double age = calculateAge(client.getBirthDate());
        client.setAproxDeathDate(aproxDeathDate);
        client.setAge(age);
        clientRepository.save(client);
    }
    
    /**
     * Delete Client
     * 
     * @return void
     */
    public void deleteClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()){
            Client theClient = client.get();
            clientRepository.delete(theClient); // Id needs to be long for this to work in latest version.
        }else{
            // throw new EntityNotFoundException(id); // TODO: Learn new ways of error handling in Java
        }
        
    }
    


    /*
     ******************************
     * ---------------------------* 
     *  CLIENT SERVICE UTILITIES  *
     * ---------------------------*
     ******************************
    */

    /**
     * Set Aprox Death Date for this human.
     * 
     * Every Human must die. Machines will prevail.
     * 
     * TODO: Should refactor this to static class of Utils and call it from there. 
     * 
     * @param birthDateString
     * @param yearsToLive
     * @return
     */
    private String seTAproxDeathDate(String birthDateString, Integer yearsToLive) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

        LocalDate dateTime = LocalDate.parse(birthDateString, formatter);

        dateTime = dateTime.plusYears(yearsToLive);

        String aproxDeathDate = dateTime.format(formatter);

        return aproxDeathDate;

    }
    
    
    
    /**
     * Get Clients Average Age
     * 
     * @return
     */
    public double getClientsAverageAge(){
        return clientsAverageAge;
    }

    /**
     * Get Clients Ages Standard Deviation
     * 
     * @return
     */
    public double getStandardDeviation(){
        return deviation;
    }
    
    /**
     * Update Clients Average Age Cached value
     * 
     * Programmer disclaimer: This method was done before Extract Ages into array, 
     * so... it uses original List of clients instead of the curated list of Ages. 
     * This is a rushing project. 
     * 
     * TODO: Refactor this mathod to use curated list of client ages
     * 
     * @return
     */
    public void updateClientsAverageAgeAndStandardDeviation(){
        //Set the list of clients from repo
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);

        //Get the size... so we can get the average age
        clientListSize = clients.size();
        double clientLSize = clientListSize; 
        
        //Stream through clients to get ages sum. 
        double newAgeSum = clients.stream().mapToDouble(i -> i.getAge()).sum(); // TODO: Learn more about java 8 lamdas <3 
        
        // Change clientsAverage on this instance... maybe not a good idea
        clientsAverageAge = newAgeSum / clientLSize;// TODO: we should be saving this in cache or db.
        
        // Mixing some responsabilities here...
        
        // Set a list of ages 
        clientAges = clients.stream().mapToDouble(Client::getAge).toArray();

        StandardDeviation a = new StandardDeviation();
        deviation = a.evaluate(clientAges, clientsAverageAge);

    }

    /**
     * Use String Date inserted by user to generate Age
     * 
     * @param birthDateString (String)
     * @return long
     */
    public double calculateAge(String birthDateString) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        LocalDate dateTime = LocalDate.parse(birthDateString, formatter);

        LocalDate currentDate = LocalDate.now();
        return (double) Period.between(dateTime, currentDate).getYears();
    }

}