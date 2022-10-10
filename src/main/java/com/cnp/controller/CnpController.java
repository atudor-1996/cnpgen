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
    public String getCnp() {
        Cnpgen cnp = new Cnpgen();
        return cnp.toString();
    }

    @GetMapping("/random/getList")
    @ResponseBody
    public ResponseEntity<Object> getList(@RequestParam int number,
                                          @RequestParam(required = false) Integer gender,
                                          @RequestParam(required = false) Integer beginYear,
                                          @RequestParam(required = false) Integer endYear,
                                          @RequestParam(required = false) Integer county) {
        try {
            LinkedHashSet<Cnpgen> set;
            if (gender != null || beginYear != null || endYear != null || county != null) {
                set = new LinkedHashSet<>(Cnpgen.getCnpList(number, gender, beginYear, endYear, county));
            } else {
                set = new LinkedHashSet<>(Cnpgen.getCnpList(number));
            }
            return new ResponseEntity<>(set, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
