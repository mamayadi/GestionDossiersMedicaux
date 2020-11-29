package gestionDossierMedical;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Medecin extends Remote {
	public String getNom() throws RemoteException;

	public void setNom(String nom) throws RemoteException;

	public DossierMedical recupererDossier(String nom, ArrayList<Patient> patientList)
			throws RemoteException, Introuvable;

}
