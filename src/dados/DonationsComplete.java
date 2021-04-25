package dados;

public class DonationsComplete {
	
	String doador;
	String donation;
	boolean complete;
	
	public DonationsComplete(String donation, boolean complete) {
		this.complete = complete;
		this.donation = donation;
	}
	public String getDoador() {
		return this.doador;
	}
	public boolean getComplete() {
		return this.complete;
	}
	
	public String getDonation() {
		return this.donation;
	}
	
	public void setDoador(String doador) {
		this.doador = doador;
	}
	
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
}
