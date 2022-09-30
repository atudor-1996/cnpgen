package com.cnp.controller;

import com.cnp.cnpgen.Cnpgen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cnp")
public class CnpController {
    @GetMapping("/random")
    public String getCnp()
    {
        Cnpgen cnp = new Cnpgen();
        return cnp.toString();
    }
}
