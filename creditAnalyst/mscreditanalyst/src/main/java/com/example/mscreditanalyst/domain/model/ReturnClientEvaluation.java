package com.example.mscreditanalyst.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnClientEvaluation {
    private List<ApprovedCard> card;
}
