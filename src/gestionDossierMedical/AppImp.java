package gestionDossierMedical;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AppImp extends UnicastRemoteObject implements App {

	ArrayList<Patient> patientList;
	ArrayList<Medecin> medecinList;
	ArrayList<Administratif> administratifList;

	public AppImp() throws RemoteException {
		patientList = new ArrayList<Patient>();
		medecinList = new ArrayList<Medecin>();
		administratifList = new ArrayList<Administratif>();
	}

	public Patient findPatient(String nom) throws RemoteException {
		Patient foundedPatient = null;
		for (Patient patient : patientList) {
			if (patient.getNom().equalsIgnoreCase(nom)) {
				foundedPatient = patient;
				break;
			}
		}
		if (foundedPatient != null) {
			return foundedPatient;
		}
		return null;
	}

	public void addPatient(String nom, int numeroSerial) throws RemoteException, ExisteDeja {
		Patient patient = new PatientImpl(nom, numeroSerial);
		Patient cherchePatient = findPatient(nom);
		if (cherchePatient == null) {
		patientList.add(patient);
		 } else {
		 throw new ExisteDeja();
		 }
	}

	public Medecin findMedecin(String nom) throws RemoteException {
		Medecin foundedMedecin = null;
		for (Medecin medecin : medecinList) {
			if (medecin.getNom().equalsIgnoreCase(nom)) {
				foundedMedecin = medecin;
				break;
			}
		}
		if (foundedMedecin != null) {
			return foundedMedecin;
		}
		return null;
	}

	public void addMedecin(String nom) throws ExisteDeja, RemoteException {
		Medecin medecin = new MedecinImpl(nom);
		Medecin chercheMedecin = findMedecin(nom);
		if (chercheMedecin == null) {
			medecinList.add(medecin);
		} else {
			throw new ExisteDeja();
		}
	}

	public void addAdministratif() throws RemoteException {
		Administratif admin = new Administratif();
		this.administratifList.add(admin);
	}

}
