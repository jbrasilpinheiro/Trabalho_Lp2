package servico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import dados.Doador;
import dados.DonationsComplete;

public class EscreverArquivo {

    public void escreverArquivo(ArrayList<DonationsComplete> baixa, ArrayList<Doador> doador){
        try{
			BufferedWriter relatorio = new BufferedWriter(new FileWriter("relatorio.html"));
			relatorio.write("Relatório:<br><br>");
			relatorio.write("Doacoes em aberto:<br><br>");
			for(int i = 0; i < baixa.size(); i++){
				if(baixa.get(i).getComplete() == false) {
					if(baixa.get(i).getDoador() == null){
						relatorio.write(i + " - " + baixa.get(i).getDonation() + "<br>");
					}else if(baixa.get(i).getDoador() != null){
						relatorio.write(i + " - " + baixa.get(i).getDonation() + " (Doador interessado: " + baixa.get(i).getDoador() + ") " + "<br>");
					}
				}
			}
			relatorio.write("<br>");
			relatorio.write("Doacoes concluidas:<br><br>");
			for(int i = 0; i < baixa.size(); i++) {
				if(baixa.get(i).getComplete() == true) {
                    if(baixa.get(i).getDoador() == null){
                        relatorio.write(i + " - " + baixa.get(i).getDonation() + "<br>");
                    }else if(baixa.get(i).getDoador() != null){
                        relatorio.write(i + " - " + baixa.get(i).getDonation() + " (Doador: " + baixa.get(i).getDoador() + ") " + "<br>");
                    }
				}
			}
			relatorio.write("<br>");
			relatorio.write("Ranking de doadores:<br><br>");
			Doador doadoresTodos[] = new Doador[doador.size()];
			for(int i = 0; i < doador.size(); i++) {
				doadoresTodos[i] = new Doador(doador.get(i).getNome(), doador.get(i).getPontos());
			}
			Arrays.sort(doadoresTodos);
			for(int i = 0; i < doador.size();i++) {
				relatorio.write((i+1) + " lugar - " + doadoresTodos[i].getNome() +  " com " + doadoresTodos[i].getPontos() + " Pontos!<br>");
			}
			relatorio.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
    }
    
}
