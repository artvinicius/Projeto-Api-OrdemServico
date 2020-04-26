package com.arthur.osworks.api.model;

import javax.validation.constraints.NotNull;

public class ClienteIdInput { // Class apenas para refenrenciar o Id

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}