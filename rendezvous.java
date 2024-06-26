import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Rendezvous implements Serializable{
    private static final long serialVersionUID = 1L; //Enregistrer la liste des rensez-vous dans un fichier

    // Déclaration des attributs :
    private Patient patient;
    private Medecin medecin;
    private Date dateRendezvous;
    private String heure;

    // Constructeurs :
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

    public Rendezvous(Medecin medecin) {
        this.medecin = medecin;
    }

    public Rendezvous(Date dateRendezvous) {
        this.dateRendezvous = dateRendezvous;
    }

    // Méthodes :
    // Méthodes pour afficher un rendez-vous
    public void afficher() {
        System.out.println("Patient: " + patient.getNom() + " " + patient.getPrenom());
        System.out.println("Médecin: Docteur" + medecin.getNom() + " " + medecin.getPrenom());
        System.out.println("Date: " + dateRendezvous);
        System.out.println("Heure: " + heure);
        System.out.println("-----------------------------");
    }

    // Méthodes pour ajouter un rendez-vous à une liste
    public static void ajouterRen(ArrayList<Rendezvous> listeRendezvous, Rendezvous nouveauRendezvous) {
        listeRendezvous.add(nouveauRendezvous);
    }

    // Méthodes pour supprimer un rendez-vous d'une liste
public boolean supprimerRen(ArrayList<Rendezvous> listeRendezvous) {
    if (listeRendezvous.isEmpty()) {
        System.out.println("La liste des rendez-vous est vide.");
        return false;
    }
    
    try {
        listeRendezvous.removeIf(rdv -> rdv.getDateRendezvous().equals(dateRendezvous) && rdv.getHeure().equals(heure));
    } catch (Exception e) {
        System.out.println("erreur");
        return false;
    }
    
    return true;
}

    // Méthode pour modifier un rendez-vous d'une liste
    public boolean modifierRen(ArrayList<Rendezvous> listeRendezvous, Date dateRendezvous, String heure) { //les argument représente les information du rendezvous qu'on veut changé
        if (listeRendezvous.isEmpty()) {
            System.out.println("La liste des rendez-vous est vide.");
            return false;
        }
        for (Rendezvous rdv : listeRendezvous) {
            if (rdv.getDateRendezvous().equals(dateRendezvous) && rdv.getHeure().equals(heure)) { //recherche de notre rendez vous
                rdv.setPatient(patient);
                rdv.setMedecin(medecin);
                rdv.setDateRendezvous(dateRendezvous);
                rdv.setHeure(heure);
                return true;
            }
        }
        return false;
    }

    // Méthode pour afficher une liste de rendez-vous
    public boolean afficherListeRen(ArrayList<Rendezvous> listeRendezvous) {
        if (listeRendezvous.isEmpty()) {
            System.out.println("La liste des rendez-vous est vide.");
            return false;
        }
        for (Rendezvous rdv : listeRendezvous) {
            rdv.afficher();
            return true;
        }
        return false;
    }

    // Méthode pour chercher/afficher les rendez-vous d'un patient
    public boolean rechercherendezvous(ArrayList<Rendezvous> listeRendezvous) {
        if (listeRendezvous.isEmpty()) {
            System.out.println("La liste des rendez-vous est vide.");
            return false;
        }
        ArrayList<Rendezvous> rdvTrouves = new ArrayList<>(); //Création d'une liste qui contienderas tous les rendezvous du patient
        for (Rendezvous rdv : listeRendezvous) {
            if (rdv.getPatient().equals(patient)) { //chercher tous les rendezvous du patient
                rdvTrouves.add(rdv); //les ajoutait à la liste
            }
        }
        if (rdvTrouves.isEmpty()) {
            System.out.println("Aucun rendez-vous trouvé pour le patient '" + patient.getNom() + "'.");
            return false;
        } else {
            afficherListeRen(rdvTrouves); //afficher la liste
            return true;
        }
    }

    // Méthode pour chercher/afficher les rendez-vous d'un médecin
    public boolean  rechercherendezvousM(ArrayList<Rendezvous> listeRendezvous) {
        if (listeRendezvous.isEmpty()) {
            System.out.println("La liste des rendez-vous est vide.");
            return false;
        }
        ArrayList<Rendezvous> rdvTrouves = new ArrayList<>();
        for (Rendezvous rdv : listeRendezvous) {
            if (rdv.getMedecin().equals(medecin)) {
                rdvTrouves.add(rdv);
            }
        }
        if (rdvTrouves.isEmpty()) {
            System.out.println("Aucun rendez-vous trouvé pour le médecin '" + medecin.getNom() + "'.");
            return false;
        } else {
            afficherListeRen(rdvTrouves);
            return true;
        }
    }

    // Méthodes pour chercher/afficher les rendez-vous d'une date
    public boolean rechercherendezvousDate(ArrayList<Rendezvous> listeRendezvous) {
        if (listeRendezvous.isEmpty()) {
            System.out.println("La liste des rendez-vous est vide.");
            return false;
        }
        ArrayList<Rendezvous> rdvTrouves = new ArrayList<>();
        for (Rendezvous rdv : listeRendezvous) {
            if (rdv.getDateRendezvous().equals(dateRendezvous)) {
                rdvTrouves.add(rdv);
            }
        }
        if (rdvTrouves.isEmpty()) {
            System.out.println("Aucun rendez-vous trouvé pour la date '" + dateRendezvous + "'.");
            return false;
        } else {
            afficherListeRen(rdvTrouves);
            return true;
        }
    }

    // Méthode pour modifier le patient d'un rendez-vous
public static boolean modifPatient(ArrayList<Rendezvous> listeRendezvous, Patient patient1, Patient patient2) {
    if (listeRendezvous.isEmpty()) {
        System.out.println("La liste des rendez-vous est vide.");
        return false;
    }
    
    try {
        // Parcourir la liste des rendez-vous
        for (Rendezvous rdv : listeRendezvous) {
            // Vérifier si le rendez-vous concerne le patient1
            if (rdv.getPatient().equals(patient1)) {
                // Modifier les informations du patient dans ce rendez-vous
                rdv.setPatient(patient2);
            }
        }
    } catch (Exception e) {
        System.out.println("erreur");
        return false;
    }
    
    return true;
}


    // Getters et Setters :
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
