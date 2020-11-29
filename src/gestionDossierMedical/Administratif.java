package gestionDossierMedical;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Administratif extends UnicastRemoteObject implements Remote {

	public Administratif() throws RemoteException {

	}

	private void creerDossier(Patient patient, DossierMedical dossierMedical) throws RemoteException {
		patient.setDossierMedical(dossierMedical);
	}

	private DossierMedical recupererDossier(String nom, ArrayList<Patient> patientList)
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

	private void modifierNomPatient(String nom, Patient patient) throws RemoteException {
		patient.setNom(nom);
	}

}
