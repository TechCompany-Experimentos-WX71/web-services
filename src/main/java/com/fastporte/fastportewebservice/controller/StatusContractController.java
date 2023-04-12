package com.fastporte.fastportewebservice.controller;

import com.fastporte.fastportewebservice.entities.StatusContract;
import com.fastporte.fastportewebservice.service.IStatusContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/statusContract")
public class StatusContractController {
    private final IStatusContractService statusContractService;

    public StatusContractController(IStatusContractService statusContractService) {
        this.statusContractService = statusContractService;
    }

    //Retornar todos los status
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StatusContract>> findAllStatus() {
        try {
            List<StatusContract> statusContracts = statusContractService.getAll();
            if (statusContracts.size() > 0)
                return new ResponseEntity<>(statusContracts, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
