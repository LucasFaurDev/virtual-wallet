package com.Lucas.virtual_wallet_server.controllers;

import com.Lucas.virtual_wallet_server.models.Operation;
import com.Lucas.virtual_wallet_server.sevices.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/{operationId}")
    public ResponseEntity<Operation> getOperationById(@PathVariable String operationId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                operationService.getOperationById(operationId)
        );
    }
}
