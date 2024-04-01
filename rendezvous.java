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

    // Méthodes
    public void ajouterRen(ArrayList<Rendezvous> listeRendezvous, Rendezvous nouveauRendezvous) {
        listeRendezvous.add(nouveauRendezvous);
    }

    public void supprimerRen(ArrayList<Rendezvous> listeRendezvous, Date dateRendezvous, String heure) {
        listeRendezvous.removeIf(rdv -> rdv.getDateRendezvous().equals(dateRendezvous) && rdv.getHeure().equals(heure));
    }

    public void modifierRen(ArrayList<Rendezvous> listeRendezvous, Date dateRendezvous, String heure, Rendezvous nouveauRendezvous) {
        for (Rendezvous rdv : listeRendezvous) {
            if (rdv.getDateRendezvous().equals(dateRendezvous) && rdv.getHeure().equals(heure)) {
                rdv.setPatient(nouveauRendezvous.getPatient());
                rdv.setMedecin(nouveauRendezvous.getMedecin());
                rdv.setDateRendezvous(nouveauRendezvous.getDateRendezvous());
                rdv.setHeure(nouveauRendezvous.getHeure());
                break;
            }
        }
    }

    public void afficherRen(ArrayList<Rendezvous> listeRendezvous) {
        for (Rendezvous rdv : listeRendezvous) {
            System.out.println("Patient: " + rdv.getPatient().getNom() + " " + rdv.getPatient().getPrenom());
            System.out.println("Médecin: " + rdv.getMedecin().getNom() + " " + rdv.getMedecin().getPrenom());
            System.out.println("Date: " + rdv.getDateRendezvous());
            System.out.println("Heure: " + rdv.getHeure());
            System.out.println("-----------------------------");
        }
    }

    public void rechercherendezvous(ArrayList<Rendezvous> listeRendezvous, String nom, String type) {
        ArrayList<Rendezvous> rdvTrouves = new ArrayList<>();
        for (Rendezvous rdv : listeRendezvous) {
            if ((type.equals("patient") && rdv.getPatient().getNom().equalsIgnoreCase(nom)) ||
                (type.equals("medecin") && rdv.getMedecin().getNom().equalsIgnoreCase(nom))) {
                rdvTrouves.add(rdv);
            }
        }
        afficherRen(rdvTrouves);
    }    

    public void rechercherendezvous(ArrayList<Rendezvous> listeRendezvous, Date dateRendezvous) {
        ArrayList<Rendezvous> rdvTrouves = new ArrayList<>();
        for (Rendezvous rdv : listeRendezvous) {
            if (rdv.getDateRendezvous().equals(dateRendezvous)) {
                rdvTrouves.add(rdv);
            }
        }
        afficherRen(rdvTrouves); 
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
