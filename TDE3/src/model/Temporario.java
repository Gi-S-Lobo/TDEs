package model;

import java.time.LocalDate;

public class Temporario extends Funcionario {
    private LocalDate dataFimContrato;

    public Temporario(String nome, String matricula, double salario, LocalDate dataFimContrato) {
        super(nome, matricula, salario);
        this.dataFimContrato = dataFimContrato;
    }

    @Override
    public double calcularSalario() {
        return salario; 
    }

    @Override
    public void exibir() {
        System.out.println("Tipo: Temporário");
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário: " + salario);
        System.out.println("Data Fim Contrato: " + dataFimContrato);
    }


    public LocalDate getDataFimContrato() {
        return dataFimContrato;
    }

    public void setDataFimContrato(LocalDate dataFimContrato) {
        this.dataFimContrato = dataFimContrato;
    }
}