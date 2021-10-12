package application;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
import entities.enums.nivelTrabalhador;
import entities.ContratoHoras;
import entities.Departamento;
import entities.Trabalhadores;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		// instanciar o simpledateformat para mexer com data 
		//tem que ser dd minusculo MM maiusculo e yyyy minusculo
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Entre com o nome do departamento");
		String nomeDepartamento = sc.nextLine();
		System.out.println("Entre com os dados do trabalhador:");
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		System.out.print("Nível: ");
		String nivel = sc.nextLine();
		System.out.print("Salário Base: ");
		Double salarioBase = sc.nextDouble();
		// instancia o objeto trabalhador associando o enum nivel e o nome do
		// departamento
		// que pertence a outra Entidade
		Trabalhadores trabalhadores = new Trabalhadores(nome, nivelTrabalhador.valueOf(nivel), salarioBase,
				                     new Departamento(nomeDepartamento));

		System.out.println("Quantos contratos tem este trabalhador?");
		int n = sc.nextInt();
		
		for (int i = 1; i <= n; i++) {
			
			System.out.println("Entro com os dados do contrato " + i);
			System.out.println("Data (DD/MM/YYYY) :");
			// sdf.parse vai gerar uma exceção throws ParseException adicionar
			Date dataContrato = sdf.parse(sc.next());
			
			System.out.println("Valor por hora:");
			Double valorhora = sc.nextDouble();
			
			System.out.println("Duração do contrato: ");
			int duracao = sc.nextInt();

			// instancia o Contrato
			ContratoHoras contrato = new ContratoHoras(dataContrato, valorhora, duracao);
			
			// associar o contrato com a entidade trabalhadores daquele trabalhador
			// chamar metodo addContrato
			trabalhadores.addContrato(contrato);
		}
		System.out.print("Digite o mês e a data para calcular os ganhos(MM/YYYY): ");
		String mesano= sc.next();
		//converte para inteiro e retira da posicao 0 2 caracteres 
		//usando a função substring
		int mes = Integer.parseInt(mesano.substring(0,2));
		//retira da posição 3 até o final porque não colocou a quantidade
		int ano = Integer.parseInt(mesano.substring(3));
		
		System.out.println("Nome: " + trabalhadores.getNome());
		
		// faz a associação das tabelas até chegar no nome do departamento
		System.out.println("Departamento: " + trabalhadores.getDepartamento().getNomeDepartamento());
		 // chama a função saltotal passando o mes e ano como parametro
		// formato o valor com 2 casas depois do ponto usando o String.format
		System.out.println("Ganhos em " + mesano + ":" + String.format("%.2f", trabalhadores.salTotalMes(ano,mes)));
		sc.close();
	}

}
