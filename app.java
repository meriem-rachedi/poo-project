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
        Medecin medecin = null;

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
                System.out.println("Pour vous déconnectez tapez S");
                String check = scanner.nextLine();
                if (check.equals("I")) {
                    patient.afficher();
                }
                if (check.equals("D")) {
                    Dossier dossier = new Dossier();
                    dossier.rechercherDossier(LD);
                }
                if (check.equals("P")) {
                    System.out.println("Donnez le jour du rendez-vous format dd/mm//yyyy ");
                    String date = scanner.next();
                    Date dateR = null;
                    try {
                        dateR = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                    } catch (ParseException e) {
                        System.out.println("Format de date invalide. Utilisez le format dd/MM/yyyy.");
                        return;
                    }
                    System.out.println("Donnez l'heure du rendez-vous entre 8h et 16h");
                    String heure = scanner.next();
                    Rendezvous rendezvous = new Rendezvous(patient, medecin, dateR, heure);
                    rendezvous.ajouterRen(LR, rendezvous);
                }
                if (check.equals("R")) {
                    Rendezvous rendezvous = new Rendezvous(patient);
                    rendezvous.rechercherendezvous(LR);
                }
                if (check.equals("M")) {
                    System.out.println("Veuillez saisir vos nouveaux informations :");

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

                    Patient patient2 = new Patient(nom, prenom, email, genre, maladies, numtel, dateNaissance, adresse);
                    patient.modifinfo(LP, patient2);
                    compte.modifInfoC(LC, patient, patient2);
                }
                if (check.equals("MR")) {
                    System.out.println("Donnez le jour du rendez-vous que vous souhaitez modfier format dd/mm//yyyy ");
                    String date = scanner.next();
                    Date dateR = null;
                    try {
                        dateR = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                    } catch (ParseException e) {
                        System.out.println("Format de date invalide. Utilisez le format dd/MM/yyyy.");
                        return;
                    }
                    System.out.println("Donnez l'heure du rendez-vous");
                    String heure = scanner.next();
                    System.out.println("Donnez la nouvelle date format dd/mm//yyyy ");
                    String date1 = scanner.next();
                    Date dateR1 = null;
                    try {
                        dateR1 = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
                    } catch (ParseException e) {
                        System.out.println("Format de date invalide. Utilisez le format dd/MM/yyyy.");
                        return;
                    }
                    System.out.println("Donnez l'heure du rendez-vous");
                    String heure1 = scanner.next();
                    Rendezvous rendezvous = new Rendezvous(patient, medecin, dateR1, heure1);
                    rendezvous.modifierRen(LR, dateR, heure);
                }
                if (check.equals("A")) {
                    System.out.println("Donnez le jour du rendez-vous a annuler format dd/mm//yyyy ");
                    String date = scanner.next();
                    Date dateR = null;
                    try {
                        dateR = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                    } catch (ParseException e) {
                        System.out.println("Format de date invalide. Utilisez le format dd/MM/yyyy.");
                        return;
                    }
                    System.out.println("Donnez l'heure du rendez-vous");
                    String heure = scanner.next();
                    Rendezvous rendezvous = new Rendezvous(patient, medecin, dateR, heure);
                    rendezvous.supprimerRen(LR);
                }
                if(check.equals("S")) sortie=true;
            }  
         }
        scanner.close();
    }
}
