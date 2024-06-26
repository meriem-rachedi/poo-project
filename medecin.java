import java.io.Serializable;

public class Medecin implements Serializable {
    private static final long serialVersionUID = 1L;

    // Déclaration des attributs :
    private String nom;
    private String prenom;
    private String status;

    //Constructeurs :
    public Medecin() {
    }

    public Medecin(String nom, String prenom, String status) {
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
    }
    
    public Medecin(String status) {
        this.status = status;
    }

    //Getteurs et Setteurs :
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
