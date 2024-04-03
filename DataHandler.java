import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandler implements Serializable {
    // Méthode pour sauvegarder les données
    public static void saveData(ArrayList<Dossier> LD, ArrayList<Rendezvous> LR, List<Patient> LP, List<SessionCompte> LC, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(LD);
            oos.writeObject(LR);
            oos.writeObject(LP);
            oos.writeObject(LC);
            System.out.println("Données sauvegardées avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }

    // Méthode pour charger les données
    @SuppressWarnings("unchecked")
    public static void loadData(ArrayList<Dossier> LD, ArrayList<Rendezvous> LR, List<Patient> LP, List<SessionCompte> LC, String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            LD.addAll((List<Dossier>) ois.readObject());
            LR.addAll((List<Rendezvous>) ois.readObject());
            LP.addAll((List<Patient>) ois.readObject());
            LC.addAll((List<SessionCompte>) ois.readObject());
            System.out.println("Données chargées avec succès.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
        }
    }
}
