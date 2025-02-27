package com.example.msclients.application;

import com.example.msclients.domain.Client;
import com.example.msclients.infra.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;

    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }

    public Optional<Client> getyByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}
