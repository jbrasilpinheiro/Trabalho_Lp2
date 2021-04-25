package servico;

import dados.Donations;

public class CalcPoints {

    public int calcPontos(Donations donations, int pontosTotal){

		if(donations.getTipo().equals("1")) {
			pontosTotal+=1;;
		}else if(donations.getTipo().equals("2")) {
			pontosTotal+=2;
		}else if(donations.getTipo().equals("3")) {
			pontosTotal+=4;
		}else if(donations.getTipo().equals("4")) {
			pontosTotal+=1;
		}else if(donations.getTipo().equals("5")) {
			pontosTotal+=2;
		}else if(donations.getTipo().equals("6")) {
			pontosTotal+=1;
		}
		
		if(donations.getEstado().equals("1")) {
			pontosTotal+=1;
		}else if(donations.getEstado().equals("2")) {
			pontosTotal+=2;
		}else if(donations.getEstado().equals("3")) {
			pontosTotal+=3;
		}
		
		return pontosTotal;
	}
    
}
