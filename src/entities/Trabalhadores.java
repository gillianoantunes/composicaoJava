package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import entities.enums.nivelTrabalhador;

public class Trabalhadores {

	private String nome;
	private nivelTrabalhador nivel;
	private Double salarioBase;

	// criando a relaçao com entidade Departamento
	// Todo trabalahdor é de um departamento
	private Departamento departamento;

	// relação um trabalhador tem vários contratos
	// usa entao listas
	private List<ContratoHoras> contratos = new ArrayList<>();

	// construtores só nao cria o tipo lista
	public Trabalhadores() {

	}

	public Trabalhadores(String nome, nivelTrabalhador nivel, Double salarioBase, Departamento departamento) {

		this.nome = nome;
		this.nivel = nivel;
		this.salarioBase = salarioBase;
		this.departamento = departamento;
	}

	// gets e sets tira o set do atributo contratos do tipo lista
	// adiciona 2 metodos add e remove contratos
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public nivelTrabalhador getNivel() {
		return nivel;
	}

	public void setNivel(nivelTrabalhador nivel) {
		this.nivel = nivel;
	}

	public Double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<ContratoHoras> getContratos() {
		return contratos;
	}
	// exclui este set abaixo
	// public void setContratos(List<ContratoHoras> contratos) {
	// this.contratos = contratos;
	// }

	// no lugar do set adiciona 2 metodos para adicionar e remover

	public void addContrato(ContratoHoras contrato) {
		// nomedalista.add(contrato)
		contratos.add(contrato);
	}

	public void removeContrato(ContratoHoras contrato) {
		// nomedalista.remove(contrato)
		contratos.remove(contrato);
	}

	public Double salTotalMes(int ano, int mes) {
		double soma = salarioBase;
		// percorrer os contatos deste trabalhador
		Calendar cal = Calendar.getInstance();

		for (ContratoHoras c : contratos) {
			// pega a data como sendo do cal
			cal.setTime(c.getData());
			int c_ano = cal.get(Calendar.YEAR);
			// adiciona + 1 porque Calendar m~es de 0 a 11
			int c_mes = 1 + cal.get(Calendar.MONTH);
			if (c_ano == ano && c_mes == mes) {
				// adiciona o salário base com a camada do metodo total da casse ContratoHoras
				soma += c.total();
			}
		}
		return soma;
	}

}
