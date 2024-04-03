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

        //Afficher les informations d'une consultations
        public void afficher() {
            System.out.println("Le diagnostic de la consultation du " + date + " est " + diagnostic);
            System.out.println("Médicaments prescrits:");
            if (medicamentsPrescrits.isEmpty()) {
                System.out.println("Aucun médicament prescrit.");
                return;
            }
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

    //Ajouer une nouvelle consulatation au dossier d'un patient dans la liste des consultations
    public void ajoutConsultADossier(ArrayList<Dossier> LD, Patient patient, Consultations consultation) {
        for (Dossier dossier : LD) {
            if (dossier.getPatient().equals(patient)) {
                dossier.getConsultation().add(consultation);
                System.out.println("Consultation ajoutée au dossier du patient immatriculé " + patient.getImmatricule());
                return;
            }
        }
        System.out.println("Aucun dossier trouvé pour le patient immatriculé " + patient.getImmatricule());
    }  

    // Méthode pour afficher les détails du dossier
    public void afficher() {
        System.out.println("Groupe sanguin : " + groupeSanguin);
        System.out.println("Consultations : ");
        if (consultation.isEmpty()) {
            System.out.println("Aucune consultation disponible.");
            return;
        }
        for (Consultations consultation : consultation) {
            consultation.afficher();
        }
    }
        // Méthode pour ajouter un dossier à LD
        public  void ajouterDossier(ArrayList<Dossier> LD, Dossier dossier) {
            LD.add(dossier);
        }
    
        // Méthode pour supprimer un dossier de LD
        public void supprimerDossier(ArrayList<Dossier> LD) {
            if (LD.isEmpty()) {
                System.out.println("La liste des dossiers est vide. Aucun dossier à supprimer.");
                return;
            }
            LD.removeIf(dossier -> dossier.getPatient().getImmatricule().equals(patient.getImmatricule()));
        }
    
        // Méthode pour afficher le dossier d'un patient donné 
        public void rechercherDossier(ArrayList<Dossier> LD) {
            if (LD.isEmpty()) {
                System.out.println("La liste des dossiers est vide. Aucun dossier à rechercher.");
                return;
            }
            boolean found = false;
            for (Dossier dossier : LD) {
                if (dossier.getPatient().getImmatricule().equals(patient.getImmatricule())) {
                    System.out.println("Dossier du patient immatriculé " + patient.getImmatricule());
                    dossier.afficher();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Aucun dossier trouvé pour le patient immatriculé " + patient.getImmatricule());
            }
        }

        //Modifier les information du patient dans le dossier 
        public static void modifPatient(ArrayList<Dossier> LD, Patient patient1, Patient patient2) {
            if (LD.isEmpty()) {
                System.out.println("La liste des dossiers est vide. Aucune modification effectuée.");
                return;
            }
            for (Dossier dossier : LD) {
                if (dossier.getPatient().equals(patient1)) {
                    dossier.setPatient(patient2);
                    System.out.println("Informations du patient modifiées dans le dossier.");
                    return;
                }
            }
            System.out.println("Aucun dossier trouvé pour le patient " + patient1.getImmatricule());
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
