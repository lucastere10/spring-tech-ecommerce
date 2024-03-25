package br.com.tech.springtech.infrastructure.websocket.model;

import lombok.Data;

@Data
public class Credentials {
    private String email;
    private String nome;
    private String room;

    public Credentials() {
    }

    public Credentials(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }
    
}
