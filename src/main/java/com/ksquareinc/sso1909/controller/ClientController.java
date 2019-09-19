package com.ksquareinc.sso1909.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ksquareinc.sso1909.domain.Client;
import com.ksquareinc.sso1909.domain.ServiceResponse;
import com.ksquareinc.sso1909.domain.dto.ClientDTO;
import com.ksquareinc.sso1909.service.ClientService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private static Logger logger = LoggerFactory.getLogger(ClientController.class);


    @Autowired
    ClientService clientService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/me")
    public Principal showClient(Principal principal) {
        System.out.println(principal);
        return principal;
    }

    @RequestMapping(method = RequestMethod.PUT, value =  "/changeSecret", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeSecret(OAuth2Authentication auth,
                                          @RequestBody   Map<String, String> secrets){
        String oldSecret = secrets.get("oldSecret");
        String newSecret = secrets.get("newSecret");
        String repeatNewSecret = secrets.get("repeatNewSecret");
        String id = auth.getOAuth2Request().getClientId();
        if (id != null && clientService.findById(id) != null){
            Client client = clientService.findById(id);
            if (newSecret != null && oldSecret != null && !newSecret.equals(oldSecret) && newSecret.equals(repeatNewSecret)){
                if (passwordEncoder.matches(oldSecret, client.getSecret())){
                    client.setSecret(passwordEncoder.encode(newSecret));
                    clientService.save(client);
                    return ResponseEntity.ok().body(new ServiceResponse("Your client's secret has been updated."));
                }else {
                    return ResponseEntity.badRequest().body(new ServiceResponse("Old Secret didn't match for client "+ client.getId()));
                }
            }else{
                return ResponseEntity.badRequest().body(new ServiceResponse("New Secrets didn't match or are invalid"));
            }
        }
        return ResponseEntity.badRequest().body(new ServiceResponse("Couldn't find current client"));
    }


    /**
     * Returns the list of all API clients registered on the server.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "getAllClientAPI (admin only)",
            notes = "Returns the list of all API clients registered on the server",
            response = Client.class,
            responseContainer = "List")
    public ResponseEntity<?> getAllClients(){
        List<ClientDTO> clientsDTO = new ArrayList<>();
            clientService.findAll().forEach(client -> clientsDTO.add(new ClientDTO(client)));
        logger.info("Listing all API clients");
        return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
    }

    /**
     * Returns the API client information of the client with the id provided
     * @param clientid
     * @return
     */
    @RequestMapping(value = "/{clientid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "getClientAPI (admin only)",
            notes = "Returns the API client information of the client with the id provided",
            response = Client.class)
    public ResponseEntity<?> getClients(@PathVariable String clientid){
        ClientDTO clientDTO = new ClientDTO(clientService.findById(clientid));
        logger.info("Returning API client of " + clientid);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    /**
     * Adds a new API client with the API client information provided.
     * Returns an HTTP CREATED status code.
     * @param client
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "addClientAPI (admin only)",
            notes = "Adds a new API client with the API client information provided. Returns an HTTP CREATED status code",
            response = Client.class)
    public ResponseEntity<?> saveClient(@RequestBody Client client){
        logger.info("Adding APLI client " + client.getId());
        client.setSecret(passwordEncoder.encode(client.getSecret()));
        try{
            ClientDTO clientDTO = new ClientDTO(clientService.save(client));
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        }catch (NullPointerException npe){
            return ResponseEntity.badRequest().body(new ServiceResponse("The user provided was invalid."));
        }
    }


    /**
     * Updates the API client info of the client with the clientid provided
     * @param client
     * @return
     */
    @RequestMapping( method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "updateUser (admin only)",
            notes = "Updates an API client info. Receives the clientid in the path and a Client object with the updated user info in the request body")
    public ResponseEntity<?> updateClient(@RequestBody Client client){
        logger.info("Updating API client " + client.getId());
        client.setSecret(passwordEncoder.encode(client.getSecret()));
        try{
            ClientDTO clientDTO = new ClientDTO(clientService.save(client));
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        }catch (NullPointerException npe){
            return ResponseEntity.badRequest().body(new ServiceResponse("The user provided was invalid."));
        }
    }

    /**
     * Deletes the API client with the client id provided and returns an HTTP OK status code.
     * @param clientid
     * @return
     */
    @RequestMapping(value = "/{clientid}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "deleteAPIclient (admin only)",
            notes = "Deletes the API client with the client id provided and returns an HTTP OK status code.")
    public ResponseEntity<?> deleteClient(@PathVariable String clientid) {
        clientService.deleteById(clientid);
        logger.info("Delete API client " + clientid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
