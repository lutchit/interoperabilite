package program;

public class Triplet {
	
	private String sujet;
	
	private String verbe;
	
	private String complement;
	
	public Triplet() {
		
	}
	
	public Triplet(String sujet, String verbe, String complement) {
		this.sujet = sujet;
		this.verbe = verbe;
		this.complement = complement;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getVerbe() {
		return verbe;
	}

	public void setVerbe(String verbe) {
		this.verbe = verbe;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	
}
