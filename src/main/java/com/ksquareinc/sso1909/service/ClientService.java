package com.ksquareinc.sso1909.service;

import com.ksquareinc.sso1909.domain.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


public interface ClientService extends Serializable {
    List<Client> findAll();
    Client save(Client client);
    Client findById(String id);
    void delete(Client client);
    void deleteById(String id);
    long count();

}
