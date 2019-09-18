package com.ksquareinc.sso1909.controller;

import java.security.Principal;
import java.util.Map;

import com.ksquareinc.sso1909.domain.Client;
import com.ksquareinc.sso1909.domain.ServiceResponse;
import com.ksquareinc.sso1909.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

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

}
