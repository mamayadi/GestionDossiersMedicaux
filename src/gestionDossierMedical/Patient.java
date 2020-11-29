package gestionDossierMedical;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Patient extends Remote {
	public String getNom()throws RemoteException;
	public void setNom(String nom)throws RemoteException;
	public int getNumeroSocial()throws RemoteException;
	public void setNumeroSocial(int numeroSocial)throws RemoteException;
	public DossierMedical getDossierMedical() throws RemoteException, Introuvable;
	public void setDossierMedical(DossierMedical dossierMedical) throws RemoteException;
	public ArrayList<Note> getNotes() throws RemoteException, Introuvable;
	public void addDossierMedical() throws RemoteException;
	public void addNote(String auteur, String description) throws RemoteException, Introuvable;
}
