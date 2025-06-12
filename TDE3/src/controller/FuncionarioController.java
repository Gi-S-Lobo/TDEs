package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FuncionarioController {
    private List<Funcionario> funcionarios;

    public FuncionarioController() {
        this.funcionarios = new ArrayList<>();
    }

    public void cadastrarEfetivo(String nome, String matricula, double salario, 
                               double bonusAnual, int anosNaEmpresa) throws EntradaInvalidaException {
        validarDados(nome, matricula, salario);
        funcionarios.add(new Efetivo(nome, matricula, salario, bonusAnual, anosNaEmpresa));
    }

    public void cadastrarTemporario(String nome, String matricula, double salario, 
                                  String dataFimContrato) throws EntradaInvalidaException {
        validarDados(nome, matricula, salario);
        try {
            LocalDate data = LocalDate.parse(dataFimContrato);
            funcionarios.add(new Temporario(nome, matricula, salario, data));
        } catch (DateTimeParseException e) {
            throw new EntradaInvalidaException("Data inválida. Use o formato AAAA-MM-DD.");
        }
    }

    public void cadastrarTerceirizado(String nome, String matricula, double salario, 
                                    String empresaOrigem, int duracaoContrato) throws EntradaInvalidaException {
        validarDados(nome, matricula, salario);
        if (empresaOrigem == null || empresaOrigem.trim().isEmpty()) {
            throw new EntradaInvalidaException("Empresa origem não pode ser vazia.");
        }
        if (duracaoContrato <= 0) {
            throw new EntradaInvalidaException("Duração do contrato deve ser positiva.");
        }
        funcionarios.add(new Terceirizado(nome, matricula, salario, empresaOrigem, duracaoContrato));
    }

    private void validarDados(String nome, String matricula, double salario) throws EntradaInvalidaException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new EntradaInvalidaException("Nome não pode ser vazio.");
        }
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new EntradaInvalidaException("Matrícula não pode ser vazia.");
        }
        if (salario <= 0) {
            throw new EntradaInvalidaException("Salário deve ser positivo.");
        }
    }

    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios);
    }

    public Funcionario buscarPorMatricula(String matricula) throws FuncionarioNaoEncontradoException {
        for (Funcionario f : funcionarios) {
            if (f.getMatricula().equals(matricula)) {
                return f;
            }
        }
        throw new FuncionarioNaoEncontradoException("Funcionário com matrícula " + matricula + " não encontrado.");
    }

    public List<Funcionario> buscarPorNome(String nome) {
        List<Funcionario> encontrados = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (f.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(f);
            }
        }
        return encontrados;
    }

    public void atualizarFuncionario(String matricula, Funcionario novosDados) 
            throws FuncionarioNaoEncontradoException, EntradaInvalidaException {
        Funcionario funcionario = buscarPorMatricula(matricula);
        validarDados(novosDados.getNome(), novosDados.getMatricula(), novosDados.getSalario());
        
        funcionario.setNome(novosDados.getNome());
        funcionario.setMatricula(novosDados.getMatricula());
        funcionario.setSalario(novosDados.getSalario());
        
        // Atualizações específicas para cada tipo
        if (funcionario instanceof Efetivo && novosDados instanceof Efetivo) {
            Efetivo efetivo = (Efetivo) funcionario;
            Efetivo novosDadosEfetivo = (Efetivo) novosDados;
            efetivo.setBonusAnual(novosDadosEfetivo.getBonusAnual());
            efetivo.setAnosNaEmpresa(novosDadosEfetivo.getAnosNaEmpresa());
        } else if (funcionario instanceof Temporario && novosDados instanceof Temporario) {
            Temporario temporario = (Temporario) funcionario;
            Temporario novosDadosTemporario = (Temporario) novosDados;
            temporario.setDataFimContrato(novosDadosTemporario.getDataFimContrato());
        } else if (funcionario instanceof Terceirizado && novosDados instanceof Terceirizado) {
            Terceirizado terceirizado = (Terceirizado) funcionario;
            Terceirizado novosDadosTerceirizado = (Terceirizado) novosDados;
            terceirizado.setEmpresaOrigem(novosDadosTerceirizado.getEmpresaOrigem());
            terceirizado.setDuracaoContratoMeses(novosDadosTerceirizado.getDuracaoContratoMeses());
        }
    }

    public void removerFuncionario(String matricula) throws FuncionarioNaoEncontradoException {
        Funcionario funcionario = buscarPorMatricula(matricula);
        funcionarios.remove(funcionario);
    }
}