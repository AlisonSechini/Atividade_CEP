package br.unipar.devbackend.agendaenderecos;

import java.time.LocalDateTime;

public class Cliente {
    private String nome;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String cpf;
    private LocalDateTime dataCadastro;


    public Cliente(String nome, String cep, String logradouro, String bairro,
                   String localidade, String uf, String endApiUf, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getCep() {
        return cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public String getBairro() {
        return bairro;
    }
    public String getLocalidade() {
        return localidade;
    }
    public String getUf() { return uf;
    }
    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome +
                " | CEP: " + cep +
                " | Endereço: " + logradouro + ", " + bairro + ", " + localidade + "-" + uf +
                " | Cadastrado em: " + dataCadastro;
    }
}
