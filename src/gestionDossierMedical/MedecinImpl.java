package gestionDossierMedical;

import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MedecinImpl extends UnicastRemoteObject implements Medecin {

	private String nom;

	public MedecinImpl(String nom) throws RemoteException {
		this.nom = nom;
	}

	public String getNom() throws RemoteException {
		return nom;
	}

	public void setNom(String nom) throws RemoteException {
		this.nom = nom;
	}

	public DossierMedical recupererDossier(String nom, ArrayList<Patient> patientList)
			throws RemoteException, Introuvable {
		DossierMedical dossierMedical = null;
		for (Patient patient : patientList) {
			if (patient.getNom().equals(nom)) {
				dossierMedical = patient.getDossierMedical();
				break;
			}
		}
		if (dossierMedical != null) {
			return dossierMedical;
		} else {
			throw new Introuvable();
		}
	}

}
