package gestionDossierMedical;

import java.util.ArrayList;
import java.util.Scanner;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Client {
	static App app;

	public static void main(String[] args) {
		try {
			app = (App) Naming.lookup("app");
			// app.addPatient("med", 111);
			System.out.println("Bonjour,\n" + "1) Si vous �tes un patient taper: [patient]\n"
					+ "2) Si vous �tes un medecin taper: [medecin]\n"
					+ "3) Si vous �tes un administratif taper: [administratif]");
			Scanner in = new Scanner(System.in);
			String choix = in.next().toLowerCase();
			switch (choix) {
			case "patient": {
				patient();
				break;
			}
			case "medecin": {
				medecin();
				break;
			}
			case "administratif": {
				administratif();
				break;
			}
			default:
				System.out.println("Parametre invalide!");
				break;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public static void patient() {
		Scanner in = new Scanner(System.in);
		System.out.println("Donner votre nom: ");
		String nomPatient = in.next();

		try {
			Patient patient = app.findPatient(nomPatient);
			if (patient != null) {
				// - R�cup�rer un dossier en donnant la clef
				// - R�cup�rer la liste des notes d�un dossier
				// - Lire le descriptif d�une note?
				System.out.println("1) R�cup�rer un dossier en donnant la clef [1]\n"
						+ "2) R�cup�rer la liste des notes d�un dossier [2]");
				int choixPatient = in.nextInt();
				switch (choixPatient) {
				case 1: {
					System.out.println("Votre dossier:");
					System.out.println("Nom et prenom: " + patient.getNom());
					System.out.println("Numero Social: " + patient.getNumeroSocial());
					DossierMedical dossier = patient.getDossierMedical();
					System.out.println("Vous avez " + dossier.getNoteList().size() + " notes dans votre dossier");

					break;
				}
				case 2: {
					DossierMedical dossier = patient.getDossierMedical();
					for (Note note : dossier.getNoteList()) {
						System.out.println("Auteur " + note.getAuteur() + " \n" + "Description: "
								+ note.getDescription() + " \n" + "Date redaction: " + note.getDateRedaction() + "\n");
					}
					break;
				}
				default: {
					System.out.println("Parametre invalide!");
					break;
				}
				}
			} else {
				System.out.println("Patient introuvable");
			}
		} catch (RemoteException e) {
			System.out.println(e.toString());
		} catch (Introuvable e) {
			System.out.println(e.toString());
		}
	}

	public static void administratif() {
		// - Cr�er un dossier
		// - R�cup�rer un dossier � partir d�un nom de patient
		// - Modifier le nom du patient
		try {
			Scanner in = new Scanner(System.in);
			System.out
					.println("1) Cr�er un dossier [1]\n" + "2) R�cup�rer un dossier � partir d�un nom de patient [2]\n"
							+ "3) Modifier le nom du patient [3]\n" + "4) Cr�er patient [4]\n"
									+ "5) Cr�er medecin [5]");
			int choixAdmin = in.nextInt();
			switch (choixAdmin) {
			case 1: {
				System.out.println("Donner le nom de patience");
				String nomPatient = in.next();
				Patient patient = app.findPatient(nomPatient);
				if (patient != null) {
					patient.addDossierMedical();
					System.out.println("Dossier medical est cr�er pour le patient " + patient.getNom());
				} else {
					throw new Introuvable();
				}
				break;
			}
			case 2: {
				System.out.println("Donner le nom de patience");
				String nomPatient = in.next();
				Patient patient = app.findPatient(nomPatient);
				if (patient != null) {
					DossierMedical dossier = patient.getDossierMedical();
					for (Note note : dossier.getNoteList()) {
						System.out.println("Auteur " + note.getAuteur() + " \n" + "Description: "
								+ note.getDescription() + " \n" + "Date redaction: " + note.getDateRedaction() + "\n");
					}
				} else {
					throw new Introuvable();
				}
				break;
			}
			case 3: {
				System.out.println("Donner le nom de patience");
				String nomPatient = in.next();
				Patient patient = app.findPatient(nomPatient);
				if (patient != null) {
					String nouveauNom = in.next();
					patient.setNom(nouveauNom);
					System.out.println("Nouveau nom est modifier avec succ�es");
				} else {
					throw new Introuvable();
				}
				break;
			}
			case 4: {
				System.out.println("Donner le nom de patience");
				String nomPatient = in.next();
				System.out.println("Donner le numero Social du patience");
				int numeroSocial = in.nextInt();
				app.addPatient(nomPatient, numeroSocial);
				System.out.println("Nouveau patient ajout�es avec succ�es");
				break;
			}
			case 5: {
				System.out.println("Donner le nom de medecin");
				String nomMedecin = in.next();
				app.addMedecin(nomMedecin);
				System.out.println("Nouveau medecin ajout�es avec succ�es");
				break;
			}
			default: {
				System.out.println("Parametre invalide!");
				break;
			}

			}
		} catch (RemoteException e) {
			System.out.println(e.toString());
		} catch (Introuvable e) {
			System.out.println(e.toString());
		} catch (ExisteDeja e) {
			System.out.println(e.toString());
		}
	}

	public static void medecin() {
		// - R�cup�rer un dossier � partir d�un nom de patient
		// - Cr�er une note
		// - Ajouter une note � un dossier
		try {
			Scanner in = new Scanner(System.in);
			System.out.println("Donner votre nom: ");
			String nomPatient = in.next();
			Medecin medecin = app.findMedecin(nomPatient);
			if (medecin != null) {
				System.out.println("1) R�cup�rer un dossier � partir d�un nom de patient [1]\n"
						+ "2) Ajouter une note � un dossier [2]");
				int choix = in.nextInt();
				System.out.println("Donner le nom du patient");
				String nomClient = in.next();
				Patient patient = app.findPatient(nomClient);
				if (patient != null) {
					switch (choix) {
					case 1: {
						ArrayList<Note> notes = patient.getDossierMedical().getNoteList();
						for (Note note : notes) {
							System.out.println(
									"Auteur " + note.getAuteur() + " \n" + "Description: " + note.getDescription()
											+ " \n" + "Date redaction: " + note.getDateRedaction() + "\n");
						}
						break;
					}
					case 2: {
						System.out.println("Donner la description du note");
						String description = in.next();
						patient.addNote(medecin.getNom(), description);
						System.out.println("Note ajout�e avec succ�es");
						break;
					}
					default: {
						System.out.println("Parametre invalide!");
						break;
					}
					}
				} else {
					System.out.println("Patient introuvable");
				}
			}

		} catch (RemoteException e) {
			System.out.println(e.toString());
		} catch (Introuvable e) {
			System.out.println(e.toString());
		}
	}

}
