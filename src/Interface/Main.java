package Interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dados.Doador;
import dados.Donations;
import dados.DonationsComplete;
import servico.CalcPoints;
import servico.EscreverArquivo;

public class Main {
	public static void main(String[] args) {
		String aux, tipo, estado, nome, descricao;
		int auxiliar = 0, auxiliar2 = 0, count = 0;
		CalcPoints calcPoints = new CalcPoints();
		EscreverArquivo escreverArquivo = new EscreverArquivo();

		ArrayList<Donations> donations = new ArrayList<>();
		ArrayList<Doador> doador = new ArrayList<>();
		ArrayList<DonationsComplete> baixa = new ArrayList<>();

		System.out.println("Bem vindo!\n");
	
		Scanner read = new Scanner(System.in);
		
		do{
			System.out.println("O que deseja fazer?\n"
			+ "1 - Cadastrar doador.\n"
			+ "2 - Cadastrar doacao.\n"
			+ "3 - Vincular Doador a doacao.\n"
			+ "4 - Dar baixa em doacao.\n"
			+ "5 - Ver ranking.\n"
			+ "6 - Ver doacoes disponiveis.\n"
			+ "7 - ver doacoes concluidas.\n"
			+ "0 - Sair.\n");

			aux = read.nextLine();

			switch(aux) {
				case "0":
					break;
				case "1":
					System.out.println("Insira o nome do doador:\n");
					nome = read.nextLine();
					doador.add(new Doador(nome, 0));
					break;
				case "2":
					System.out.println("Insira o tipo da doacao:\n"
							+ "1 - vestuario (1 ponto).\n"
							+ "2 - comida (2 pontos).\n"
							+ "3 - dinheiro (4 pontos).\n"
							+ "4 - brinquedo (1 pontos ).\n"
							+ "5 - remedio (2 pontos).\n"
							+ "6 - eletronico(1 ponto).\n");
	
					tipo = read.nextLine();
					
					if(tipo.charAt(0) > 54 || tipo.charAt(0) < 49) {
						System.out.println("Opcao invalida, por favor tente novamente!\n");
						break;
					}
					
					System.out.println("Insira o estado da doacao:\n"
							+ "1 - Usado e desgastado (1 ponto).\n"
							+ "2 - Usado e conservado (2 pontos).\n"
							+ "3 - Novo (3 pontos).\n");
					estado = read.nextLine();
					if(estado.charAt(0) > 51 || estado.charAt(0) < 49) {
						System.out.println("Opcao invalida, por favor tente novamente!\n");
						break;
					}
					System.out.println("Insira a descricao da doacao:\n");
					descricao = read.nextLine();
					donations.add(new Donations(tipo, estado, descricao));
					baixa.add(new DonationsComplete(donations.get(donations.size()-1).getDescricao(), false));
					break;
					
				case "3":
					System.out.println("Escolha a doacao a ser vinculada:\n");
	
					for(int i = 0; i < donations.size(); i++) {
						if(baixa.get(i).getComplete() == false) {
							if(baixa.get(i).getDoador() == null){
								System.out.println(i + " - " + baixa.get(i).getDonation() + "\n");
							}else if(baixa.get(i).getDoador() != null){
								System.out.println(i + " - " + baixa.get(i).getDonation() + " (Doador interessado: " + baixa.get(i).getDoador() + ") " + "\n");
							}
						}
					}
					
					auxiliar = read.nextInt();
					read.nextLine();
	
					if(donations.size() == 0 || baixa.get(auxiliar).getComplete() == true || auxiliar < 0 || auxiliar >= donations.size()) {
						System.out.println("Opcao invalida!\n");
						break;
					}
					
					System.out.println("Escolha o doador para creditar os pontos assim que a doacao for efetuada:\n");
					for(int i = 0; i < doador.size();i++) {
						System.out.println(i + " - " + doador.get(i).getNome() + "\n");
					}
					auxiliar2 = read.nextInt();
					read.nextLine();
					if(auxiliar2 < 0 || auxiliar2 >= doador.size()) {
						System.out.println("Opcao invalida!\n");
						break;
					}
					baixa.get(auxiliar).setDoador(doador.get(auxiliar2).getNome());
					count = 1;
					break;
					
				case "4":
					if(count == 1) {
						System.out.println("Escolha a doacao para dar baixa:");
						for(int i = 0; i < donations.size(); i++) {
							if(baixa.get(i).getComplete() == false && baixa.get(i).getDoador() != null) {
								if(baixa.get(i).getDoador() == null){
									System.out.println(i + " - " + donations.get(i).getDescricao() + "\n");
								}else if(baixa.get(i).getDoador() != null){
									System.out.println(i + " - " + donations.get(i).getDescricao() + " (Doador: " + baixa.get(i).getDoador() + ") " + "\n");
								}
							}
						}
						auxiliar = read.nextInt();
						read.nextLine();

						if(baixa.get(auxiliar).getComplete() == true) {
							System.out.println("Opcao invalida\n");
							break;
						}
						
						for(int j = 0; j < doador.size();j++) {
							if(baixa.get(auxiliar).getDoador().equals(doador.get(j).getNome())) {
								doador.get(j).setPontos(calcPoints.calcPontos(donations.get(auxiliar), doador.get(j).getPontos()));
								baixa.get(auxiliar).setComplete(true);
								break;
							}
						}
						System.out.println("Baixa realizada com sucesso!");
					}else if(count == 0) {
						System.out.println("Sem doacoes com doadores cadastradas no momento, volte mais tarde.\n");
					}
					break;
				case "5":
					System.out.println("A seguir o ranking dos doadores:\n");
					Doador doadoresTodos[] = new Doador[doador.size()];
					for(int i = 0; i < doador.size(); i++) {
						doadoresTodos[i] = new Doador(doador.get(i).getNome(), doador.get(i).getPontos());
					}
					Arrays.sort(doadoresTodos);
					for(int i = 0; i < doador.size();i++) {
						System.out.println((i+1) + " lugar - " + doadoresTodos[i].getNome() +  " com " + doadoresTodos[i].getPontos() + " Pontos!\n");
					}
					break;
					
				case "6":
					System.out.println("A seguir as doacões disponiveis:\n");
					for(int i = 0; i < baixa.size(); i++) {
						if(baixa.get(i).getComplete() == false) {
							if(baixa.get(i).getDoador() == null){
								System.out.println(i + " - " + baixa.get(i).getDonation() + "\n");
							}else if(baixa.get(i).getDoador() != null){
								System.out.println(i + " - " + baixa.get(i).getDonation() + " (Doador interessado: " + baixa.get(i).getDoador() + ") " + "\n");
							}
						}
					}
					
					break;
					
				case "7": 
					System.out.println("A seguir as doacoes concluidas:\n");
					for(int i = 0; i < baixa.size(); i++) {
						if(baixa.get(i).getComplete() == true) {
							if(baixa.get(i).getDoador() == null){
								System.out.println(i + " - " + baixa.get(i).getDonation() + "\n");
							}else if(baixa.get(i).getDoador() != null){
								System.out.println(i + " - " + baixa.get(i).getDonation() + " (Doador: " + baixa.get(i).getDoador() + ") " + "\n");
							}
						}
					}
					break;	
				default:
					System.out.println("Opcao invalida, por favor tente novamente!\n");
				break;
				}

		}while(aux.equals("0") == false);
		
		escreverArquivo.escreverArquivo(baixa, doador);
		
	}

}