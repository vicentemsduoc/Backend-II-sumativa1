package com.petsalud.backendpetsalud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ldap")
public class LdapTestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Endpoint público LDAP - sin autenticación";
    }

    @GetMapping("/protegido")
    public String protectedEndpoint() {
        return "Acceso concedido mediante autenticación LDAP";
    }
}