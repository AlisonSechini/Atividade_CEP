package br.unipar.devbackend.agendaenderecos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainViaCep {
    private static List<Cliente> bancoClientes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ViaCepService viaCep = new ViaCepService();

        System.out.print("Informe o CEP: ");
        String cep = scanner.nextLine();

        Cliente clienteExistenteCep = bancoClientes.stream()
                .filter(c -> c.getCep().equals(cep))
                .findFirst()
                .orElse(null);

        if (clienteExistenteCep != null) {
            System.out.println("Já existe cliente cadastrado nesse CEP:");
            System.out.println(clienteExistenteCep);
        } else {
            try {
                ViaCepService.Endereco endApi = viaCep.buscarCep(cep);

                System.out.print("Informe o CPF do cliente: ");
                String cpf = scanner.nextLine();

                // 🔎 Verifica se CPF já existe
                Cliente clienteExistenteCpf = bancoClientes.stream()
                        .filter(c -> c.getCpf().equals(cpf))
                        .findFirst()
                        .orElse(null);

                if (clienteExistenteCpf != null) {
                    System.out.println("Já existe cliente cadastrado com esse CPF:");
                    System.out.println(clienteExistenteCpf);
                    return;
                }

                System.out.print("Informe o nome do cliente: ");
                String nome = scanner.nextLine();

                Cliente cliente = new Cliente(
                        nome,
                        cpf,
                        endApi.getCep(),
                        endApi.getLogradouro(),
                        endApi.getBairro(),
                        endApi.getLocalidade(),
                        endApi.getUf(),
                        LocalDateTime.now()
                );

                bancoClientes.add(cliente);
                System.out.println("Cliente cadastrado com sucesso!");
                System.out.println(cliente);

            } catch (Exception e) {
                System.out.println("Erro ao consultar CEP: " + e.getMessage());
            }
        }
    }
}
