package model;

public class Terceirizado extends Funcionario {
    private String empresaOrigem;
    private int duracaoContratoMeses;

    public Terceirizado(String nome, String matricula, double salario, String empresaOrigem, int duracaoContratoMeses) {
        super(nome, matricula, salario);
        this.empresaOrigem = empresaOrigem;
        this.duracaoContratoMeses = duracaoContratoMeses;
    }

    @Override
    public double calcularSalario() {
        return salario * 0.9; 
    }

    @Override
    public void exibir() {
        System.out.println("Tipo: Terceirizado");
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário Base: " + salario);
        System.out.println("Salário Líquido: " + calcularSalario());
        System.out.println("Empresa Origem: " + empresaOrigem);
        System.out.println("Duração Contrato (meses): " + duracaoContratoMeses);
    }

    // Getters e Setters específicos
    public String getEmpresaOrigem() {
        return empresaOrigem;
    }

    public void setEmpresaOrigem(String empresaOrigem) {
        this.empresaOrigem = empresaOrigem;
    }

    public int getDuracaoContratoMeses() {
        return duracaoContratoMeses;
    }

    public void setDuracaoContratoMeses(int duracaoContratoMeses) {
        this.duracaoContratoMeses = duracaoContratoMeses;
    }
}