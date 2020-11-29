package gestionDossierMedical;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.rmi.*;

public class DossierMedical implements Serializable {

	private ArrayList<Note> noteList;

	public DossierMedical() {
		noteList = new ArrayList<Note>();
	}

	public ArrayList<Note> getNoteList() throws Introuvable {
		if (noteList != null) {
			return noteList;
		} else {
			throw new Introuvable();
		}

	}

}
