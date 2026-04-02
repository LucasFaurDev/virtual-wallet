package com.Lucas.virtual_wallet_server.repository;

import com.Lucas.virtual_wallet_server.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, String> {
}
