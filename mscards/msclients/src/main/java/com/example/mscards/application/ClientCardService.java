package com.example.mscards.application;

import com.example.mscards.domain.ClientCard;
import com.example.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {
    private final ClientCardRepository repository;

    public List<ClientCard> listCardByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
