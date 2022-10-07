package com.cnp.controller;

import com.cnp.cnpgen.Cnpgen;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cnp")
public class CnpController {
    @GetMapping("/random")
    public String getCnp()
    {
        Cnpgen cnp = new Cnpgen();
        return cnp.toString();
    }
    @GetMapping("/random/getList")
    @ResponseBody
    public ResponseEntity<Object> getList(@RequestParam int number)
    {
        LinkedHashSet<Cnpgen> set = new LinkedHashSet<>(Cnpgen.getCnpList(number));

        return new ResponseEntity<Object>(set, HttpStatus.OK);
    }
}
