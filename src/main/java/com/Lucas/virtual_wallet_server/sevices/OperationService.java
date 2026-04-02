package com.Lucas.virtual_wallet_server.sevices;

import com.Lucas.virtual_wallet_server.exceptions.OperationNotFoundException;
import com.Lucas.virtual_wallet_server.models.Operation;
import com.Lucas.virtual_wallet_server.repository.OperationRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation getOperationById(String operationId) {
        return operationRepository.findById(operationId).orElseThrow(
                () -> new OperationNotFoundException(operationId)
        );
    }

 }
