package gestionDossierMedical;
import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable{
	private String auteur;
	private Date dateRedaction;
	private String description;
	
	public Note(String auteur, Date dateRedaction, String description) {
		this.auteur = auteur;
		this.dateRedaction = dateRedaction;
		this.description = description;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Date getDateRedaction() {
		return dateRedaction;
	}

	public void setDateRedaction(Date dateRedaction) {
		this.dateRedaction = dateRedaction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
