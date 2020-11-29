package gestionDossierMedical;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface App extends Remote {
	public void addPatient(String nom, int numeroSerial) throws RemoteException, ExisteDeja;
	public Patient findPatient(String nom) throws RemoteException;
	public void addMedecin(String nom) throws ExisteDeja, RemoteException;
	public Medecin findMedecin(String nom) throws RemoteException;
	public void addAdministratif() throws RemoteException;

}
