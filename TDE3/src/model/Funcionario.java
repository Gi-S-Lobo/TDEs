package model;

public abstract class Funcionario implements Exibivel{
		protected String matricula;
		protected String nome; 
		protected double salario;
		
		 public Funcionario(String nome, String matricula, double salario) {
		        this.nome = nome;
		        this.matricula = matricula;
		        this.salario = salario;
		 }
		  public String getNome() {
		        return nome;
		    }

		    public void setNome(String nome) {
		        this.nome = nome;
		    }

		    public String getMatricula() {
		        return matricula;
		    }

		    public void setMatricula(String matricula) {
		        this.matricula = matricula;
		    }

		    public double getSalario() {
		        return salario;
		    }

		    public void setSalario(double salario) {
		        this.salario = salario;
		    }

		    public abstract double calcularSalario();
			public void exibir() {};
			
		
}
