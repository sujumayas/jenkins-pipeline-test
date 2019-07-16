package com.sujumayas.clientsmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository; // So we can use pagingAndSorting later (it extends CrudRepository)
import org.springframework.stereotype.Repository;

import io.micrometer.core.ipc.http.HttpSender.Request;

import javax.persistence.EntityNotFoundException;

import com.sujumayas.clientsmicroservice.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
}