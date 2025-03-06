package com.example.mscreditanalyst.application;

import com.example.mscreditanalyst.application.ex.ClientDataNotFoundException;
import com.example.mscreditanalyst.application.ex.MicroservicesComunicationErrorException;
import com.example.mscreditanalyst.domain.model.ClientSituation;
import com.example.mscreditanalyst.domain.model.EvaluationData;
import com.example.mscreditanalyst.domain.model.ReturnClientEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credit-analyst")
@RequiredArgsConstructor
public class CreditAnalystController {
    private final CreditEvaluatorService creditEvaluatorService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "client-situation", params = "cpf")
    public ResponseEntity ClientSituationQuery(@RequestParam("cpf") String cpf) {
        try {
            ClientSituation clientSituation = creditEvaluatorService.getClientSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesComunicationErrorException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity PerformEvaluation(@RequestBody EvaluationData data) {
        try {
            ReturnClientEvaluation returnEvaluationClient = creditEvaluatorService.performEvaluation(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(returnEvaluationClient);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesComunicationErrorException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
