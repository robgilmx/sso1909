package com.ksquareinc.sso1909.service;

import com.ksquareinc.sso1909.domain.Client;
import com.ksquareinc.sso1909.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("clientsService")
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findById(String id) {
        Optional<Client> clientOptional;
        clientOptional = clientRepository.findById(String.valueOf(id));
        return clientOptional.get();
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public void deleteById(String id) {
        clientRepository.deleteById(id);
    }

    @Override
    public long count() {
        return clientRepository.count();
    }
}