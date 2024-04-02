import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class app {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<SessionCompte> LC = new ArrayList<>();
        ArrayList<Patient> LP = new ArrayList<>();
        ArrayList<Dossier> LD = new ArrayList<>();
        ArrayList<Rendezvous> LR = new ArrayList<>();
        String utilisateur = "";
        String mdp="";
        SessionCompte compte = null;

        System.out.println("Bonjour,");
        System.out.println("Si vous êtes un patient, entrez P");
        System.out.println("Si vous êtes un médecin, entrez M");
        System.out.println("Si vous êtes le secrétaire, entrez S");

        String choix = scanner.next();

        if (choix.equals("P")) {
            Patient patient = new Patient();
            System.out.println("Pour créer un nouveau compte, tapez S");
            System.out.println("Pour vous connecter, tapez L");

            String action = scanner.next();

            if (action.equals("S")) {
                boolean creerCompte = false;
                while (!creerCompte) {
                    System.out.println("Entrez un nom d'utilisateur :");
                    utilisateur = scanner.next();

                    System.out.println("Entrez un mot de passe :");
                    mdp = scanner.next();

                    compte = new SessionCompte(utilisateur, mdp);
                    creerCompte = compte.creerCompte(LC);
                }

                // Demander au patient de saisir ses informations
                System.out.println("Veuillez saisir vos informations :");

                System.out.print("Nom : ");
                String nom = scanner.nextLine();

                System.out.print("Prénom : ");
                String prenom = scanner.nextLine();

                System.out.print("Email : ");
                String email = scanner.nextLine();

                System.out.print("Genre : ");
                String genre = scanner.nextLine();

                System.out.print("Maladies : ");
                String maladies = scanner.nextLine();

                System.out.print("Adresse : ");
                String adresse = scanner.nextLine();

                System.out.print("Numéro de téléphone : ");
                int numtel = scanner.nextInt();

                System.out.print("Date de naissance (au format dd/MM/yyyy) : ");
                String dateNaissanceStr = scanner.next();
                Date dateNaissance = null;
                try {
                    dateNaissance = new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissanceStr);
                } catch (ParseException e) {
                    System.out.println("Format de date invalide. Utilisez le format dd/MM/yyyy.");
                    return;
                }

                // Créer l'objet Patient avec les informations saisies
                patient = new Patient(nom, prenom, email, genre, maladies, numtel, dateNaissance, adresse);
                compte.setPatient(patient);
                compte.ajoutCompte(LC, compte);
                patient.ajoutPatient(LP);
                System.out.println("Compte créer avec succées");
            } else{
                if (action.equals("L")) {
                    Boolean check = false;
                    while (!check) {
                        System.out.println("Entrez votre nom d'utilisateur ");
                        utilisateur = scanner.next();
                        System.out.println("Entrez votre mot de passe : ");
                        mdp = scanner.next();
                        compte = new SessionCompte(utilisateur, mdp);
                        check = compte.connexion(LC);
                        patient = compte.rechercherPatientCompte(LC);
                    }
                }
            }
            System.out.println("Bienvenu"+ patient.getNom() + " " + patient.getPrenom());
            Boolean sortie = false;
            while(!sortie){
                System.out.println("Quelle service voulez vous avoir ?");
                System.out.println("Tapez I pour afficher vos informations");
                System.out.println("Tapez D pour afficher votre dossier médicale");
                System.out.println("Tapez P pour prendre un nouveau rendez-vous");
                System.out.println("Tapez R pour voir tous vos rendez-vous");
                System.out.println("Tapez M pour modifier vos information");
                System.out.println("Tapez MR pour modifier un rendez-vous");
                System.out.println("Tapez A pour annuler un rendez-vous");
                System.out.println("Tapez RR pour chercher un rendez vous précis");
                //To see the out put without the infinit loop i added this
                sortie = true;
            }
        }
        scanner.close();
    }
}
