import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class app {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<SessionCompte> LC = new ArrayList<>();
            List<Patient> LP = new ArrayList<>();
            ArrayList<Dossier> LD = new ArrayList<>();
            ArrayList<Rendezvous> LR = new ArrayList<>();

         // Charger les données sauvegardées (si disponibles)
        DataHandler.loadData(LD, LR, LP, LC, "data.ser");

            String utilisateur = "";
            String mdp="";
            SessionCompte compte = null;
            Medecin medecin = null;

            System.out.println("Bonjour,");
            System.out.println("Si vous êtes un patient, entrez P");
            System.out.println("Si vous êtes un médecin, entrez M");
            System.out.println("Si vous êtes le secrétaire, entrez S");

            String choix = scanner.next();

            //Interface patient
            if (choix.equals("P")) {
                Patient patient = new Patient();
                System.out.println("Pour créer un nouveau compte, tapez S");
                System.out.println("Pour vous connecter, tapez L");

                String action = scanner.next();

                if (action.equals("S")) {
                    //Ajout d'un nom d'utilistaeur et du mot de passe correcte
                    boolean creerCompte = false;
                    while (!creerCompte) {
                        System.out.println("Entrez un nom d'utilisateur :");
                        utilisateur = scanner.next();

                        System.out.println("Entrez un mot de passe :");
                        mdp = scanner.next();

                        compte = new SessionCompte(utilisateur, mdp);
                        creerCompte = compte.creerCompte(LC); //Verification du user et mdp
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
                    //Ajouter les informations du patient au données de son compte pour ne pas qu'il les re saisisse à chaque connexion
                    compte.setPatient(patient);
                    compte.ajoutCompte(LC, compte);     // Sauvgardez le compte
                    patient.ajoutPatient(LP);   // Sauvgardez le patient
                    System.out.println("Compte créer avec succées");
                } else{
                    if (action.equals("L")) {
                        Boolean check = false;
                        while (!check) {
                            //Saisie des informations du compte
                            System.out.println("Entrez votre nom d'utilisateur ");
                            utilisateur = scanner.next();
                            System.out.println("Entrez votre mot de passe : ");
                            mdp = scanner.next();
                            compte = new SessionCompte(utilisateur, mdp); //Création de l'abojet compte pour le vérifié
                            check = compte.connexion(LC); //Vérifié si le compte est correcte
                            patient = compte.rechercherPatientCompte(LC); //Obte,ir les informations du patient depuis son compte
                        }
                    }
                }
                System.out.println("Bienvenu"+ patient.getNom() + " " + patient.getPrenom());
                //Les services :
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
                        Dossier dossier = new Dossier(patient);
                        dossier.rechercherDossier(LD);
                    }
                    if (check.equals("P")) {
                        //Saisis des informations du rendez-vous
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
                        System.out.println("Tapez P pour prendre un rendez-vous avec le docteur Principale \n Tapez R pour le docteur Remplacant");
                        String status = scanner.next();
                        medecin = new Medecin(status);
                        //Sauvgarde du rendez-vous dans la liste LR
                        Rendezvous rendezvous = new Rendezvous(patient, medecin, dateR, heure);
                        Rendezvous.ajouterRen(LR, rendezvous);
                    }
                    if (check.equals("R")) {
                        Rendezvous rendezvous = new Rendezvous(patient);
                        rendezvous.rechercherendezvous(LR);
                    }
                    if (check.equals("M")) {
                        //Saisies des nouveau informations
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
                        patient.modifinfo(LP, patient2); //Modifiez les informations du patient dans la liste du patient
                        compte.modifInfoC(LC, patient, patient2); //Modifiez les informations du patient dans la liste des compte
                        Dossier.modifPatient(LD, patient, patient2); //Modifiez les information dans le dossier
                        Rendezvous.modifPatient(LR, patient, patient2); //Modfier les informations dans les rendezvous
                    }
                    if (check.equals("MR")) {
                        //Entrez les nouvelle information du rendez vous
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
                        //Création de l'objet rendezvous à changer
                        Rendezvous rendezvous = new Rendezvous(patient, medecin, dateR1, heure1);
                        rendezvous.modifierRen(LR, dateR, heure); //Trouvez le rendezvous et le changer
                    }
                    if (check.equals("A")) {
                        //Saisir les informations du rendez vous
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
                        //Création de l'objet rendez vous, le chercher dans la liste, et le suuprimez
                        Rendezvous rendezvous = new Rendezvous(patient, medecin, dateR, heure);
                        rendezvous.supprimerRen(LR);
                    }
                    if(check.equals("S")) sortie=true;
                }  
             }
            
            //Interface médecin
            if (choix.equals("M")) {
                //le médecin rentre ces informations
                System.out.println("Bonjour docteur rentrez vos informations :");
                System.out.println("Entrez votre nom : ");
                String nom = scanner.next();
                System.out.println("Entrez votre prenom : ");
                String prenom = scanner.next();
                System.out.println("Tapez P si vous étes le médecin principale \n Taprez R si vous étes le médecin remplacant : ");
                String status = scanner.next();
                medecin = new Medecin(nom,prenom,status);
                Boolean sortie = false; //Variable pour sortie de la boucle
                while (sortie == false) {
                    //Serive à proposer
                    System.out.println("Quelle service voulez vous faire aujourd'hui ?");
                    System.out.println("Tapez R pour remplire le dossier d'un patient");
                    System.out.println("Tapez D pour chercher afficher le dossier d'un patient");
                    System.out.println("Tapez A pour ajouter une consultation au dossier d'un patient");
                    System.out.println("Tapez S pour supprimez le dossier d'un patient");
                    System.out.println("Tapez P pour chercher un patient");
                    System.out.println("Tapez V pour voir vos rendez-vous");
                    System.out.println("Tapez RR pour rechercher les rendez-vous d'une certaine date");
                    System.out.println("Tapez D si vous souhaitez vous déconnecter");
                    choix = scanner.next();

                    if (choix.equals("R")) {
                        //Chercher le patient dont le médecin veut créer un dossier pour
                        System.out.println("Donnez le nom du patient");
                        String nomP = scanner.next();
                        Patient patient = new Patient();
                        patient.obtenirImmatriculesParNom(nomP,LP);
                        System.out.println("Voila tous les patients trpuvés"); //En cas où pluisieur patient on le méme nom il choisis un matricule
                        System.out.println("Tapez le matricule du patient choisis");
                        String mat = scanner.next();
                        patient = Patient.recherchePatient(mat,LP); //obtenir les informations du patient
                        System.out.println("Donnez le groupe sanguin du patient :");
                        String gs = scanner.next();
                        System.out.println("Donnez les informations de la consultations ");
                        System.out.println("Donnez votre diagnostic : ");
                        String diag = scanner.next();
                        System.out.print("Dannez la date de la consultation : ");
                        String date = scanner.next();
                        Date dateC = null;
                        try {
                            dateC = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                        } catch (ParseException e) {
                            System.out.println("Format de date invalide. Utilisez le format dd/MM/yyyy.");
                            return;
                        }
                        Dossier dossier = new Dossier();
                        Dossier.Consultations consultation = dossier.new Consultations();
                        List<String> LM = new ArrayList<String>();
                        //Remplire la variable consultations
                        consultation.setDate(dateC);
                        consultation.setDiagnostic(diag);
                        consultation.setMedicamentsPrescrits(LM);
                        //Remplir la liste des médicaments
                        System.out.println("Donnez le nombre de médicament à préscrire");
                        int n = scanner.nextInt();
                        for(int i = 1; i<=n; i++){
                            System.out.println("Donnez le médicament "+ i);
                            String medoc = scanner.next();
                            consultation.ajouterMedicamentPrescrit(medoc);
                        }
                        //Créations de l'objet dossier et l'ajouté au tableau LD pour le sauvgarder
                        ArrayList<Dossier.Consultations> Lcons = new ArrayList<Dossier.Consultations>();
                        consultation.ajouterConsultations(Lcons, consultation);
                        dossier = new Dossier(patient, gs, Lcons);
                        dossier.ajouterDossier(LD, dossier);
                    }
                    if (choix.equals("D")) {
                        //Recherche du patient 
                        System.out.println("Donnez le nom du patient");
                        String nomP = scanner.next();
                        Patient patient = new Patient();
                        patient.obtenirImmatriculesParNom(nomP,LP);
                        System.out.println("Voila tous les patients trouvés");
                        System.out.println("Tapez le matricule du patient choisis");
                        String mat = scanner.next();
                        patient = Patient.recherchePatient(mat,LP);
                        //Création d'un dossier avec le patient pour chercher le dossier dans le tableau et l'afficher
                        Dossier dossier = new Dossier(patient);
                        dossier.rechercherDossier(LD);
                    }
                    if (choix.equals("A")) {
                        //Chercher le patient voulue
                        System.out.println("Donnez le nom du patient");
                        String nomP = scanner.next();
                        Patient patient = new Patient();
                        patient.obtenirImmatriculesParNom(nomP,LP);
                        System.out.println("Voila tous les patients avec le nom" + nomP);
                        System.out.println("Tapez le matricule du patient choisis");
                        String mat = scanner.next();
                        patient = Patient.recherchePatient(mat,LP);
                        //Creation de la nouvelle consultation
                        System.out.println("Donnez les informations de la consultations ");
                        System.out.println("Donnez votre diagnostic : ");
                        String diag = scanner.next();
                        System.out.print("Dannez la date de la consultation : ");
                        String date = scanner.next();
                        Date dateC = null;
                        try {
                           dateC = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                        } catch (ParseException e) {
                            System.out.println("Format de date invalide. Utilisez le format dd/MM/yyyy.");
                            return;
                        }
                        Dossier dossier = new Dossier();
                        Dossier.Consultations consultation = dossier.new Consultations();
                        List<String> LM = new ArrayList<String>();
                        consultation.setDate(dateC);
                        consultation.setDiagnostic(diag);
                        consultation.setMedicamentsPrescrits(LM);
                        System.out.println("Donnez le nombre de médicament à préscrire");
                        int n = scanner.nextInt();
                        for(int i = 1; i<=n; i++){
                            System.out.println("Donnez le médicament "+ i);
                            String medoc = scanner.next();
                            consultation.ajouterMedicamentPrescrit(medoc);
                        } 
                        //Ajout de la consultation dans la liste de consultation du dossier
                        dossier.ajoutConsultADossier(LD, patient, consultation);
                    }
                    if (choix.equals("S")) {
                        System.out.println("Donnez le nom du patient");
                        String nomP = scanner.next();
                        Patient patient = new Patient();
                        patient.obtenirImmatriculesParNom(nomP,LP);
                        System.out.println("Voila tous les patients avec le nom" + nomP);
                        System.out.println("Tapez le matricule du patient choisis");
                        String mat = scanner.next();
                        patient = Patient.recherchePatient(mat,LP);
                        Dossier dossier = new Dossier(patient);
                        dossier.supprimerDossier(LD);
                    }
                    if (choix.equals("P")) {
                        System.out.println("Donnez le nom du patient");
                        String nomP = scanner.next();
                        Patient patient = new Patient();
                        patient.obtenirImmatriculesParNom(nomP,LP);
                        System.out.println("Voila tous les patients avec le nom" + nomP);
                        System.out.println("Tapez le matricule du patient choisis");
                        String mat = scanner.next();
                        Patient.recherchePatient(mat, LP);
                    }
                    if (choix.equals("V")) {
                        Rendezvous rendezvous = new Rendezvous(medecin);
                        rendezvous.rechercherendezvousM(LR);
                    }
                    if (choix.equals("RR")) {
                        System.out.println("Donnez la date au format dd/mm/yyyy");
                        String date = scanner.next();
                        Date dateC = null;
                        try {
                            dateC = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                        } catch (ParseException e) {
                            System.out.println("Format de date invalide. Utilisez le format dd/MM/yyyy.");
                            return;
                        }
                        Rendezvous rendezvous = new Rendezvous(dateC);
                        rendezvous.rechercherendezvousDate(LR);
                    }
                    if (choix.equals("D")) {
                        sortie = true;
                    }
                }
            }
    
            if (choix.equals("S")) {
                Boolean sortie = false; //Variable pour sortie de la boucle
              while (sortie == false) {
                System.out.println("Bonjour");
                System.out.println("quelle service veut faire aujourd'hui ? ");
                System.out.println("Tapez R pour ajouter un rendez-vous");
                System.out.println("Tapez S pour supprimez un patient");
                System.out.println("Tapez A pour annuler une rendez-vous");
                System.out.println("Tapez MR pour modifier un rendez-vous");
                System.out.println("Tapez D si vous souhaitez vous déconnecter");

                choix = scanner.next();

                if (choix.equals("R")) {
                    System.out.println("Donnez le nom du patient");
                        String nomP = scanner.next();
                        Patient patient = new Patient();
                        patient.obtenirImmatriculesParNom(nomP,LP);
                        System.out.println("Voila tous les patients trouvés"); //En cas où pluisieur patient on le méme nom il choisis un matricule
                        System.out.println("Tapez le matricule du patient choisis");
                        String mat = scanner.next();
                        patient = Patient.recherchePatient(mat,LP); //obtenir les informations du patient

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
                        System.out.println("Tapez P pour prendre un rendez-vous avec le docteur Principale \n Tapez R pour le docteur Remplacant");
                        String status = scanner.next();
                        medecin = new Medecin(status);
                        //Sauvgarde du rendez-vous dans la liste LR
                        Rendezvous rendezvous = new Rendezvous(patient, medecin, dateR, heure);
                        Rendezvous.ajouterRen(LR, rendezvous);
                }
                if (choix.equals("S")) {
                    System.out.println("Donnez le nom du patient");
                        String nomP = scanner.next();
                        Patient patient = new Patient();
                        patient.obtenirImmatriculesParNom(nomP,LP);
                        System.out.println("Voila tous les patients trpuvés"); //En cas où pluisieur patient on le méme nom il choisis un matricule
                        System.out.println("Tapez le matricule du patient choisis");
                        String mat = scanner.next();
                        patient = Patient.recherchePatient(mat,LP); //obtenir les informations du patient
                        Patient.suppPatient(mat, LP);
                }
                if (choix.equals("A")) {
                    System.out.println("Donnez le nom du patient");
                        String nomP = scanner.next();
                        Patient patient = new Patient();
                        patient.obtenirImmatriculesParNom(nomP,LP);
                        System.out.println("Voila tous les patients trpuvés"); //En cas où pluisieur patient on le méme nom il choisis un matricule
                        System.out.println("Tapez le matricule du patient choisis");
                        String mat = scanner.next();
                        patient = Patient.recherchePatient(mat,LP); //obtenir les informations du patient
                    //Saisir les informations du rendez vous
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
                    //Création de l'objet rendez vous, le chercher dans la liste, et le suuprimez
                    Rendezvous rendezvous = new Rendezvous(patient, medecin, dateR, heure);
                    rendezvous.supprimerRen(LR);
                }
                if (choix.equals("MR")) {
                    System.out.println("Donnez le nom du patient");
                        String nomP = scanner.next();
                        Patient patient = new Patient();
                        patient.obtenirImmatriculesParNom(nomP,LP);
                        System.out.println("Voila tous les patients trpuvés"); //En cas où pluisieur patient on le méme nom il choisis un matricule
                        System.out.println("Tapez le matricule du patient choisis");
                        String mat = scanner.next();
                        patient = Patient.recherchePatient(mat,LP); //obtenir les informations du patient
                     //Entrez les nouvelle information du rendez vous
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
                     //Création de l'objet rendezvous à changer
                     Rendezvous rendezvous = new Rendezvous(patient, medecin, dateR1, heure1);
                     rendezvous.modifierRen(LR, dateR, heure); //Trouvez le rendezvous et le changer
                    }
                if(choix.equals("D")) sortie=true;
              }
            }
            
            // À la fin de votre programme, sauvegarder les données
            DataHandler.saveData(LD, LR, LP, LC, "data.ser");
            
            scanner.close();
        }
    }
}
