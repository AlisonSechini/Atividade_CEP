package br.unipar.devbackend.agendaenderecos.model;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.StringReader;

@XmlRootElement(name = "xmlcep") // <-- precisa bater com a tag raiz do XML
public class Endereco {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    @XmlElement
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    @XmlElement
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    @XmlElement
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    @XmlElement
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    @XmlElement
    public String getLocalidade() { return localidade; }
    public void setLocalidade(String localidade) { this.localidade = localidade; }

    @XmlElement
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }

    // Conversão do XML em objeto Endereco
    public static Endereco unmarshalFromString(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(Endereco.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Endereco) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException("Erro ao converter XML para Endereco", e);
        }
    }
}
