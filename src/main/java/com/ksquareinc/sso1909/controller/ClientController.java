package com.ksquareinc.sso1909.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @RequestMapping("/client/me")
    public Principal showClient(Principal principal) {
        System.out.println(principal);
        return principal;
    }

}
