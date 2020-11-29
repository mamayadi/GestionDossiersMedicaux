package gestionDossierMedical;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class PatientImpl extends UnicastRemoteObject implements Patient {
	private String nom;
	private int numeroSocial;
	private DossierMedical dossierMedical;

	public PatientImpl(String nom, int numeroSocial) throws RemoteException {
		this.nom = nom;
		this.numeroSocial = numeroSocial;
	}

	public String getNom() throws RemoteException {
		return nom;
	}

	public void setNom(String nom) throws RemoteException {
		this.nom = nom;
	}

	public int getNumeroSocial() throws RemoteException {
		return numeroSocial;
	}

	public void setNumeroSocial(int numeroSocial) throws RemoteException {
		this.numeroSocial = numeroSocial;
	}

	public DossierMedical getDossierMedical() throws RemoteException, Introuvable {
		if (dossierMedical != null) {
			return dossierMedical;
		} else {
			throw new Introuvable();
		}

	}

	public void setDossierMedical(DossierMedical dossierMedical) throws RemoteException {
		this.dossierMedical = dossierMedical;
	}

	public void addDossierMedical() throws RemoteException {
		DossierMedical dossier = new DossierMedical();
		this.dossierMedical = dossier;
	}

	public ArrayList<Note> getNotes() throws RemoteException, Introuvable {
		return this.getDossierMedical().getNoteList();
	}

	public void addNote(String auteur, String description) throws RemoteException, Introuvable {
		Date date = new Date();
		Note note = new Note(auteur, date, description);
		if (dossierMedical != null) {
			dossierMedical.getNoteList().add(note);
		} else {
			throw new Introuvable();
		}

	}

}
