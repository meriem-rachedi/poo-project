import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Déclaration des attributs :
    private String nom;
    private String prenom;
    private String email;
    private String gender;
    private String maladies; // L'historique médical du patient
    private String adresse;
    private int numtel;
    private String immatricule; // Matricule unique généré automatiquement
    private Date birthdate;

    // Compteur statique pour générer un identifiant unique
    private static int compteurMatricule = 0;

    // Constructeurs :
    public Patient() {
        // Générer automatiquement un matricule unique
        this.immatricule = genererMatricule();
    }

    public Patient(String nom, String prenom, String email, String gender, String maladies, int numtel, Date birthdate, String adresse) {
        this(); // Appeler le constructeur par défaut pour générer automatiquement un matricule
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.gender = gender;
        this.maladies = maladies;
        this.numtel = numtel;
        this.birthdate = birthdate;
        this.adresse = adresse;
    }

    // Méthode pour générer automatiquement un matricule unique avec le même nombre de chiffres
    private String genererMatricule() {
        compteurMatricule++;
        // Formater le compteur avec des zéros à gauche pour assurer un nombre constant de chiffres
        String formatCompteur = String.format("%04d", compteurMatricule); // Par exemple, "%04d" garantit 4 chiffres
        return "MAT" + formatCompteur;
    }
        
    // Méthode : afficher les informations du patient
    public void afficher () {
        System.out.println("******Vos Informations******");
        System.out.println("Immatricule : "+immatricule);
        System.out.println("Nom : "+nom);
        System.out.println("Prenom : "+prenom);
        System.out.println("Birth date : "+birthdate);
        System.out.println("Gender : "+gender);
        System.out.println("Maladies : "+maladies);
        System.out.println("Numéro de téléphone : "+numtel);
        System.out.println("Email : "+email);
    }
    
    // Méthode pour ajouter un nouveau patient à la liste des patients
    public void ajoutPatient(List<Patient> patientList) {
        // Créer un nouveau patient en utilisant les attributs de la classe actuelle
        Patient patient = new Patient(this.nom, this.prenom, this.email, this.gender, this.maladies, this.numtel, this.birthdate, this.adresse);
        patientList.add(patient); // Ajouter le patient à la liste
        System.out.println("Patient ajouté avec succès. Matricule : " + patient.getImmatricule());
    }

    // Méthode pour obtenir les immatricules des patients avec un nom donné
    public static boolean obtenirImmatriculesParNom(String nomRecherche, List<Patient> patientList) {
        boolean found = false; 
        // Vérifier si la liste des patients est vide
        if (patientList.isEmpty()) {
            System.out.println("La liste des patients est vide.");
            return found;
        }
        System.out.println("Immatricules des patients avec le nom '" + nomRecherche + "':");
        for (Patient patient : patientList) { //Boucle pour afficher tous les patients avec le méme noms et leur matricule
            if (patient.getNom().equalsIgnoreCase(nomRecherche)) { //Chercher le patient dans la liste
                System.out.println("Nom : " + patient.getNom() + " Prénom : " + patient.getPrenom()); //Accessing patient's name and prenom
                System.out.println(patient.getImmatricule());
                found = true;
                return found;
            }
        }
        if (!found) {
            System.out.println("Aucun patient avec le nom '" + nomRecherche + "' trouvé.");
            return found;
        }
        return found;
    }

    // Méthode : rechercher patient (retourner le patient avec un matricule donné)
    public static Patient recherchePatient (String immatricule,List<Patient> patientList) {
        // Vérifier si la liste des patients est vide
        if (patientList.isEmpty()) {
            System.out.println("La liste des patients est vide.");
            return null;
        }
        for (Patient patient : patientList) {
            if (patient.getImmatricule().equalsIgnoreCase(immatricule)) { //Chercher le patient avec le matricule donné
                patient.afficher();
                return patient;
            }
        }
        return null;
    }

    // Méthode : supprimer patient
    public static boolean  suppPatient (String immatricule, List<Patient> patientList) {
        // Vérifier si la liste des patients est vide
        if (patientList.isEmpty()) {
            System.out.println("La liste des patients est vide.");
            return false;
        }
        Iterator <Patient> iterator = patientList.iterator(); //Itérateur de la liste 'patientList'
        while (iterator.hasNext()) { // hasnext() est une méthode de Iterator qui permet de parcourir une liste 
            Patient patient = iterator.next();
            if (patient.getImmatricule().equalsIgnoreCase(immatricule)) { //test si l'immatricule qu'on cherche existe dans la liste pour pouvoir le supprimer
                iterator.remove(); //remove() est une méthode de Iterator qui permet de supprimer des élement d'une liste
                System.out.println("Patient " + immatricule + " supprimé avec succès.");
                return true; // Sortie de la méthode après la suppression du patient
            }
        }
        System.out.println("Patient " + immatricule + " not found in the list."); //Patient not found
        return  false;
    }
    
    // Méthode : modifier les informations du patient
    public  boolean modifinfo (List<Patient> patientList, Patient patient2) {
        // Vérifier si la liste des patients est vide
        if (patientList.isEmpty()) {
            System.out.println("La liste des patients est vide.");
            return false;
        }
        for (Patient patient1 : patientList) {
            if (patient1.getImmatricule().equals(immatricule)) { //test si l'immatricule qu'on cherche existe dans la liste pour pouvoir modifier ses informations
                //insertion des nouvelles informations modifiées
                patient1.setNom(patient2.getNom());
                patient1.setPrenom(patient2.getPrenom());
                patient1.setEmail(patient2.getEmail());
                patient1.setGender(patient2.getGender());
                patient1.setAddresse(patient2.getAddresse());
                patient1.setBirthdate(patient2.getBirthdate());
                patient1.setNumtel(patient2.getNumtel());
                patient1.setMaladies(patient2.getMaladies());
                System.out.println("Patient information modified successfully.");
                return true; // Exit method after modifying the patient's information
            }
        }
        System.out.println("Patient " + immatricule + " not found in the list."); //Patient not found
        return false;
    }

    // Getters and Setters :
    //Nom
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    //Prenom
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    //Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //Gender
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    //Maladies
    public String getMaladies() {
        return maladies;
    }
    public void setMaladies(String maladies) {
        this.maladies = maladies;
    }
    //Addresse
    public String getAddresse() {
        return adresse;
    }
    public void setAddresse(String adresse) {
        this.adresse = adresse;
    }
    //Numéro de téléphone
    public int getNumtel() {
        return numtel;
    }
    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }
    //Immatricule
    public String getImmatricule() {
        return immatricule;
    }
    public void setImmatricule(String immatricule) {
        this.immatricule = immatricule;
    }
    //Birth date
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
