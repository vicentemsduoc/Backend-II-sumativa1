package com.petsalud.backendpetsalud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/confidencial")
    public String portalConfidencial() {
        return "Bienvenido al portal confidencial de CLÍNICA PETSALUD. Solo los administradores pueden ver esto.";
    }
}