package br.unipar.devbackend.agendaenderecos;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.StringReader;

public class ViaCepService {

    @XmlRootElement(name = "xmlcep")
    public static class Endereco {
        private String cep;
        private String logradouro;
        private String bairro;
        private String localidade;
        private String uf;

        @XmlElement public String getCep() { return cep; }
        public void setCep(String cep) { this.cep = cep; }

        @XmlElement public String getLogradouro() { return logradouro; }
        public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

        @XmlElement public String getBairro() { return bairro; }
        public void setBairro(String bairro) { this.bairro = bairro; }

        @XmlElement public String getLocalidade() { return localidade; }
        public void setLocalidade(String localidade) { this.localidade = localidade; }

        @XmlElement public String getUf() { return uf; }
        public void setUf(String uf) { this.uf = uf; }
    }

    public Endereco buscarCep(String cep) throws Exception {
        URL url = new URL("https://viacep.com.br/ws/" + cep + "/xml/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();

        return fromXml(sb.toString());
    }

    private Endereco fromXml(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(Endereco.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Endereco) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException("Erro ao converter XML", e);
        }
    }
}
