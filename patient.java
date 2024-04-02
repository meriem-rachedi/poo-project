import java.util.Date;
import java.util.List;
import java.util.Iterator;

public class Patient {
    
    // Déclaration des attributs
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

         //Déclaration des constructeurs
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
        
        //Méthods
         //Méthode : afficher les informations du patient
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
    

        public static void ajoutPatient(String nom, String prenom, String email, String gender, String maladies, int numtel, Date birthdate, String addresse, List<Patient> patientList) {
            Patient patient = new Patient(nom, prenom, email, gender, maladies, numtel, birthdate, addresse);
            patientList.add(patient); // Ajouter les informations des patients dans une liste
            System.out.println("Patient ajouté avec succès. Matricule : " + patient.getImmatricule());
        }
        

// Méthode pour obtenir les immatricules des patients avec un nom donné
    public static void obtenirImmatriculesParNom(String nomRecherche, List<Patient> patientList) {
        boolean found = false; 
        System.out.println("Immatricules des patients avec le nom '" + nomRecherche + "':");
        for (Patient patient : patientList) {
            if (patient.getNom().equalsIgnoreCase(nomRecherche)) {
                System.out.println(patient.getImmatricule());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Aucun patient avec le nom '" + nomRecherche + "' trouvé.");
        }
    }

        //Méthode : rechercher patient 
        public static void recherchePatient (String immatricule,List<Patient> patientList) {
              for (Patient patient : patientList) {
                      if (patient.getImmatricule().equalsIgnoreCase(immatricule)) //equalsIgnoreCase permet de vérifier si l'immatricule qu'on cherche existe dans notre liste
                      {
                             patient.afficher();
                      }
                }
        }

         //Méthode : supprimer patient
        public static void suppPatient (String immatricule, List<Patient> patientList) {
           Iterator <Patient> iterator = patientList.iterator(); //Itérateur de la liste 'patietList'
           while (iterator.hasNext()) // hasnext() est une méthode de Iterator qui permet de parcourir une liste 
           {
              Patient patient = iterator.next();
              if (patient.getImmatricule().equalsIgnoreCase(immatricule)) //test si l'immatricule qu'on cherche existe dans la liste pour pouvoir le supprimer
              {
                iterator.remove();//remove() est une méthode de Iterator qui permet de supprimer des élement d'une liste
                System.out.println("Patient " + immatricule + " supprimé avec succès.");
                return; // Sortie de la méthode après la suppression du patient
              }
           }
           System.out.println("Patient " + immatricule + " not found in the list."); //Patient not found
        }
       
         //Méthode : modifier les informations du patient
        public static void modifinfo (String immatricule,String nom, String prenom, String email, String gender, String maladies, int numtel,Date birthdate,String addresse,List<Patient> patientList) {
          for (Patient patient : patientList) {
            if (patient.getImmatricule().equalsIgnoreCase(immatricule)) //test si l'immatricule qu'on cherche existe dans la liste pour pouvoir modifier ses informations
            { //insertion des nouvelles informations modifiées
              patient.setNom(nom);
              patient.setPrenom(prenom);
              patient.setEmail(email);
              patient.setGender(gender);
              patient.setAddresse(addresse);
              patient.setBirthdate(birthdate);
              patient.setNumtel(numtel);
              patient.setMaladies(maladies);

                System.out.println("Patient information modified successfully.");
                return; // Exit method after modifying the patient's information
            }
          }
          System.out.println("Patient " + immatricule + " not found in the list."); //Patient not found
        }
       
        //Getters and Setters 
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
        public void setAddresse(String addresse) {
            this.adresse = addresse;
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


