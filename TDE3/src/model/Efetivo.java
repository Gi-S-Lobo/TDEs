package model;


public class Efetivo extends Funcionario {
    private double bonusAnual;
    private int anosNaEmpresa;

    public Efetivo(String nome, String matricula, double salario, double bonusAnual, int anosNaEmpresa) {
        super(nome, matricula, salario);
        this.bonusAnual = bonusAnual;
        this.anosNaEmpresa = anosNaEmpresa;
    }

    @Override
    public double calcularSalario() {
        return salario + (bonusAnual / 12) + (anosNaEmpresa * 100);
    }

    @Override
    public void exibir() {
        System.out.println("Tipo: Efetivo");
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário Base: " + salario);
        System.out.println("Bônus Anual: " + bonusAnual);
        System.out.println("Anos na Empresa: " + anosNaEmpresa);
        System.out.println("Salário Total: " + calcularSalario());
    }

   
    public double getBonusAnual() {
        return bonusAnual;
    }

    public void setBonusAnual(double bonusAnual) {
        this.bonusAnual = bonusAnual;
    }

    public int getAnosNaEmpresa() {
        return anosNaEmpresa;
    }

    public void setAnosNaEmpresa(int anosNaEmpresa) {
        this.anosNaEmpresa = anosNaEmpresa;
    }
}