package com.trackmatch.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final EntityType type;

    public NotFoundException(EntityType type, long id) {
        super(String.format("%s com ID %s não encontrado", type.getLabel(), id));
        this.type = type;
    }
}
