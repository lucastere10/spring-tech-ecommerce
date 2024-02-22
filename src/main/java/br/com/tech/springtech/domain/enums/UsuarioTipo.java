package br.com.tech.springtech.domain.enums;

public enum UsuarioTipo {
    
    ADMIN("admin"),

    CLIENTE("cliente");

    private String tipo;

    UsuarioTipo(String tipo){
        this.tipo = tipo;
    }

    public String getRole(){
        return tipo;
    }

}
