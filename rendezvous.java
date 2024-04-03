import java.util.ArrayList;
import java.util.Date;

public class Rendezvous {

    private Patient patient;
    private Medecin medecin;
    private Date dateRendezvous;
    private String heure;

    // Constructeurs
    public Rendezvous() {
    }

    public Rendezvous(Patient patient, Medecin medecin, Date dateRendezvous, String heure) {
        this.patient = patient;
        this.medecin = medecin;
        this.dateRendezvous = dateRendezvous;
        this.heure = heure;
    }

    public Rendezvous(Patient patient) {
        this.patient = patient;
    }

    // Méthodes
    //Méthodes pour afficher un rendez-vous
    public void afficher() {
            System.out.println("Patient: " + patient.getNom() + " " + patient.getPrenom());
            System.out.println("Médecin: Docteur" + medecin.getNom() + " " + medecin.getPrenom());
            System.out.println("Date: " + dateRendezvous);
            System.out.println("Heure: " + heure);
            System.out.println("-----------------------------");
    }

    //Méthodes pour ajouter un rendez-vous à une liste
    public void ajouterRen(ArrayList<Rendezvous> listeRendezvous, Rendezvous nouveauRendezvous) {
        listeRendezvous.add(nouveauRendezvous);
    }

    //Méthodes pour supprimez un rendez-vous d'une liste
    public void supprimerRen(ArrayList<Rendezvous> listeRendezvous) {
        listeRendezvous.removeIf(rdv -> rdv.getDateRendezvous().equals(dateRendezvous) && rdv.getHeure().equals(heure)); //removeIf(ici) est une méthode qui supprime les éléments d'une liste qui suivent la condition "ici"
    }

    //Méthode pour modifier un rendez-vous d'une liste
    public void modifierRen(ArrayList<Rendezvous> listeRendezvous, Date dateRendezvous, String heure) {
        for (Rendezvous rdv : listeRendezvous) {
            if (rdv.getDateRendezvous().equals(dateRendezvous) && rdv.getHeure().equals(heure)) {
                rdv.setPatient(patient);
                rdv.setMedecin(medecin);
                rdv.setDateRendezvous(dateRendezvous);
                rdv.setHeure(heure);
                break;
            }
        }
    }

    //Méthode pour afficher une liste de rendez-vous
    public void afficherListeRen(ArrayList<Rendezvous> listeRendezvous) {
        for (Rendezvous rdv : listeRendezvous) {
            rdv.afficher();
        }
    }

    //Méthode pour chercher/afficher les rendez-vous d'un patient ou d'un médecin
    public void rechercherendezvous(ArrayList<Rendezvous> listeRendezvous) {
        ArrayList<Rendezvous> rdvTrouves = new ArrayList<>();
        for (Rendezvous rdv : listeRendezvous) {
            if (rdv.getPatient().equals(patient)) {
                rdvTrouves.add(rdv);
            }
        }
        if (rdvTrouves.isEmpty()) {
            System.out.println("Aucun rendez-vous trouvé pour le patient '" + patient.getNom() + "'.");
        } else {
            afficherListeRen(rdvTrouves);
        }
    }

    //Méthodes pour chercher/afficher les rendez-vous d'une date
    public void rechercherendezvous(ArrayList<Rendezvous> listeRendezvous, Date dateRendezvous) {
        ArrayList<Rendezvous> rdvTrouves = new ArrayList<>();
        for (Rendezvous rdv : listeRendezvous) {
            if (rdv.getDateRendezvous().equals(dateRendezvous)) {
                rdvTrouves.add(rdv);
            }
        }
        if (rdvTrouves.isEmpty()) {
            System.out.println("Aucun rendez-vous trouvé pour la date '" + dateRendezvous + "'.");
        } else {
            afficherListeRen(rdvTrouves);
        }
    }

    // Getters et Setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Date getDateRendezvous() {
        return dateRendezvous;
    }

    public void setDateRendezvous(Date dateRendezvous) {
        this.dateRendezvous = dateRendezvous;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
