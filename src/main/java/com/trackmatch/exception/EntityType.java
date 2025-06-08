package com.trackmatch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityType {
    USER("Usuário"),
    EVENT("Evento"),
    APPLICATION("Aplicação");

    private final String label;
}
