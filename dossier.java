import java.util.List;        // est une interface qui définit les comportements attendus d'une liste.
import java.util.Date;
import java.util.ArrayList;

public class Dossier {
	
	private Patient patient;
    private String groupeSanguin;    

    //Classe de consultation pour créer une liste de touts les consultation d'un patient
    public class Consultations{
        private String diagnostic;
        private Date date;
        private List<String> medicamentsPrescrits;

        //Constructeurs
        Consultations(){}
        public Consultations(String diagnostic, Date date, List<String> medicamentsPrescrits) {
            this.diagnostic = diagnostic;
            this.date = date;
            this.medicamentsPrescrits = medicamentsPrescrits;
        }

        //Méthodes :
        public void ajouterMedicamentPrescrit(String medicament) {        //Cette méthode ajoute un nouveau médicament prescrit à la liste
            medicamentsPrescrits.add(medicament);
        }

        public void afficher() {            //Cette méthode affiche la consultation
            System.out.println("Le diagnostic de la consultation du " + date + " est " + diagnostic); // Ajouter le signe "+" après date
            System.out.println("Médicaments prescrits:");
            for (String medicament : medicamentsPrescrits) {
                System.out.println("- " + medicament);
            }
        }

        //Méthode pour ajouter une consultation à une liste
        public void ajouterConsultations(ArrayList<Consultations> LC, Consultations consultation) {
            LC.add(consultation);
        }

        //Méthode pour supprimer une consultation à une liste
        public  void supprimerConsultations(ArrayList<Consultations> LC, Date dateConsultation) {
            LC.removeIf(consultation -> consultation.getDate().equals(dateConsultation));
        }

        //Méthode pour chercher les consultation d'une date donnée
        public  void rechercherConsultationsParDate(ArrayList<Consultations> LC, Date dateConsultation) {
            boolean found = false;
            for (Consultations consultation : LC) {
                if (consultation.getDate().equals(dateConsultation)) {
                    consultation.afficher();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Aucune consultation trouvée pour la date spécifiée.");
            }
        }

        //Getteur et Setteur
        public String getDiagnostic() {
            return diagnostic;
        }
        public void setDiagnostic(String diagnostic) {
            this.diagnostic = diagnostic;
        }
        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }
        public List<String> getMedicamentsPrescrits() {
            return medicamentsPrescrits;
        }
        public void setMedicamentsPrescrits(List<String> medicamentsPrescrits) {
            this.medicamentsPrescrits = medicamentsPrescrits;
        }
    }
    private ArrayList<Consultations> consultation;
    
    //Constructeurs du dossier 
    Dossier(){}

    public Dossier(Patient patient, String groupeSanguin, ArrayList<Dossier.Consultations> consultation) {
        this.patient = patient;
        this.groupeSanguin = groupeSanguin;
        this.consultation = consultation;
    }
    
    public Dossier(Patient patient) {
        this.patient = patient;
    }

    public void ajoutConsultADossier(ArrayList<Dossier> LD, Patient patient, Consultations consultation) {
        // Rechercher le dossier du patient
        for (Dossier dossier : LD) {
            if (dossier.getPatient().equals(patient)) {
                // Ajouter la consultation à la liste des consultations du dossier du patient
                dossier.getConsultation().add(consultation);
                System.out.println("Consultation ajoutée au dossier du patient immatriculé " + patient.getImmatricule());
                return; // Sortir de la boucle une fois que la consultation a été ajoutée
            }
        }
        // Si aucun dossier n'est trouvé pour le patient
        System.out.println("Aucun dossier trouvé pour le patient immatriculé " + patient.getImmatricule());
    }    

    // Méthode pour afficher les détails du dossier
    public void afficher() {
        System.out.println("Groupe sanguin : " + groupeSanguin);
        System.out.println("Consultations : ");
        for (Consultations consultation : consultation) { 
            consultation.afficher();
        }
    }
        // Méthode pour ajouter un dossier à LD
        public  void ajouterDossier(ArrayList<Dossier> LD, Dossier dossier) {
            LD.add(dossier);
        }
    
        // Méthode pour supprimer un dossier de LD
        public  void supprimerDossier(ArrayList<Dossier> LD) {
            LD.removeIf(dossier -> dossier.getPatient().getImmatricule().equals(patient.getImmatricule()));
        }
    
        // Méthode pour afficher le dossier d'un patient donné 
        public void rechercherDossier(ArrayList<Dossier> LD) {
            boolean found = false;
            for (Dossier dossier : LD) {
                if (dossier.getPatient().getImmatricule().equals(patient.getImmatricule())) {
                    System.out.println("Dossier du patient immatriculé " + patient.getImmatricule());
                    // Afficher les détails du dossier sans les consultations
                    dossier.afficher();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Aucun dossier trouvé pour le patient immatriculé " + patient.getImmatricule());
            }
        }

        //Getteur et Setteur
        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        public String getGroupeSanguin() {
            return groupeSanguin;
        }

        public void setGroupeSanguin(String groupeSanguin) {
            this.groupeSanguin = groupeSanguin;
        }

        public ArrayList<Consultations> getConsultation() {
            return consultation;
        }

        public void setConsultation(ArrayList<Consultations> consultation) {
            this.consultation = consultation;
        }
}
