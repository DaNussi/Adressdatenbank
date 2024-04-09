package at.ac.htlstp.et.adresstadenbank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

    @GetMapping("/ajax/query1")
    public ResponseEntity<String> toUppercaseGet() {
        return ResponseEntity.ok("68");

    }

}
