package gestionDossierMedical;
import java.rmi.Naming;

public class Serveur {

	public static void main(String[] args) {
		try {
			Naming.rebind("app", new AppImp());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
