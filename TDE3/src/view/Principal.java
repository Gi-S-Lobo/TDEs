package view;

import controller.FuncionarioController;
import model.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Principal {
    private static FuncionarioController controller = new FuncionarioController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Digite sua opção: ");
            try {
                switch (opcao) {
                    case 1:
                        cadastrarFuncionario();
                        break;
                    case 2:
                        listarFuncionarios();
                        break;
                    case 3:
                        buscarFuncionario();
                        break;
                    case 4:
                        atualizarFuncionario();
                        break;
                    case 5:
                        removerFuncionario();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Sistema de Cadastro de Funcionários ===");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Listar Funcionários");
        System.out.println("3. Buscar Funcionário");
        System.out.println("4. Atualizar Funcionário");
        System.out.println("5. Remover Funcionário");
        System.out.println("0. Sair");
    }

    private static void cadastrarFuncionario() throws Exception {
        System.out.println("\n=== Cadastro de Funcionário ===");
        System.out.println("1. Efetivo");
        System.out.println("2. Temporário");
        System.out.println("3. Terceirizado");
        int tipo = lerInteiro("Escolha o tipo: ");
        
        String nome = lerString("Nome: ");
        String matricula = lerString("Matrícula: ");
        double salario = lerDouble("Salário: ");
        
        switch (tipo) {
            case 1:
                double bonus = lerDouble("Bônus Anual: ");
                int anos = lerInteiro("Anos na Empresa: ");
                controller.cadastrarEfetivo(nome, matricula, salario, bonus, anos);
                break;
            case 2:
                String data = lerString("Data Fim Contrato (AAAA-MM-DD): ");
                controller.cadastrarTemporario(nome, matricula, salario, data);
                break;
            case 3:
                String empresa = lerString("Empresa Origem: ");
                int duracao = lerInteiro("Duração Contrato (meses): ");
                controller.cadastrarTerceirizado(nome, matricula, salario, empresa, duracao);
                break;
            default:
                throw new Exception("Tipo de funcionário inválido!");
        }
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private static void listarFuncionarios() {
        List<Funcionario> lista = controller.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            System.out.println("\n=== Lista de Funcionários ===");
            for (Funcionario f : lista) {
                f.exibir();
                System.out.println("----------------------");
            }
        }
    }

    private static void buscarFuncionario() throws Exception {
        System.out.println("\n=== Buscar Funcionário ===");
        System.out.println("1. Por Matrícula");
        System.out.println("2. Por Nome");
        int opcao = lerInteiro("Escolha: ");
        
        if (opcao == 1) {
            String matricula = lerString("Matrícula: ");
            Funcionario f = controller.buscarPorMatricula(matricula);
            f.exibir();
        } else if (opcao == 2) {
            String nome = lerString("Nome: ");
            List<Funcionario> encontrados = controller.buscarPorNome(nome);
            if (encontrados.isEmpty()) {
                System.out.println("Nenhum funcionário encontrado com esse nome.");
            } else {
                System.out.println("\n=== Resultados da Busca ===");
                for (Funcionario f : encontrados) {
                    f.exibir();
                    System.out.println("----------------------");
                }
            }
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static void atualizarFuncionario() throws Exception {
        String matricula = lerString("Matrícula do funcionário a atualizar: ");
        Funcionario existente = controller.buscarPorMatricula(matricula);
        
        System.out.println("\nDados atuais:");
        existente.exibir();
        
        System.out.println("\nNovos dados:");
        String nome = lerString("Nome (" + existente.getNome() + "): ");
        String novaMatricula = lerString("Matrícula (" + existente.getMatricula() + "): ");
        double salario = lerDouble("Salário (" + existente.getSalario() + "): ");
        
        Funcionario novosDados;
        if (existente instanceof Efetivo) {
            Efetivo efetivo = (Efetivo) existente;
            double bonus = lerDouble("Bônus Anual (" + efetivo.getBonusAnual() + "): ");
            int anos = lerInteiro("Anos na Empresa (" + efetivo.getAnosNaEmpresa() + "): ");
            novosDados = new Efetivo(nome, novaMatricula, salario, bonus, anos);
        } else if (existente instanceof Temporario) {
            Temporario temp = (Temporario) existente;
            String data = lerString("Data Fim Contrato (" + temp.getDataFimContrato() + "): ");
            novosDados = new Temporario(nome, novaMatricula, salario, LocalDate.parse(data));
        } else if (existente instanceof Terceirizado) {
            Terceirizado terc = (Terceirizado) existente;
            String empresa = lerString("Empresa Origem (" + terc.getEmpresaOrigem() + "): ");
            int duracao = lerInteiro("Duração Contrato (" + terc.getDuracaoContratoMeses() + " meses): ");
            novosDados = new Terceirizado(nome, novaMatricula, salario, empresa, duracao);
        } else {
            throw new Exception("Tipo de funcionário desconhecido.");
        }
        
        controller.atualizarFuncionario(matricula, novosDados);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    private static void removerFuncionario() throws Exception {
        String matricula = lerString("Matrícula do funcionário a remover: ");
        controller.removerFuncionario(matricula);
        System.out.println("Funcionário removido com sucesso!");
    }

    // Métodos auxiliares para entrada de dados
    private static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número inteiro válido.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }
}