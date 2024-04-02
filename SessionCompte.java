import java.util.List;

public class SessionCompte {
    private String utilisateur;
    private String mdp;
    private Patient patient;
    
    //Constructeurs
    public SessionCompte(){}
    public SessionCompte(String utilisateur, String mdp) {
        this.utilisateur = utilisateur;
        this.mdp = mdp;
    }
    
    public SessionCompte(String utilisateur, String mdp, Patient patient) {
        this.utilisateur = utilisateur;
        this.mdp = mdp;
        this.patient = patient;
    }
    
    //Méthodes
    //Ajouter un compte pour le sauvegarder
    public void ajoutCompte(List<SessionCompte> listeCompte, SessionCompte compte){
        listeCompte.add(compte);
    }

    //Supprimer un compte
    public void supprimerCompte(List<SessionCompte> listeCompte, String utilisateur) {
        //Supprime le compte de l'utilisateur de la liste
        listeCompte.removeIf(compte -> compte.getUtilisateur().equals(utilisateur));
    }

    public Patient rechercherPatientCompte(List<SessionCompte> listeCompte) {
        for(SessionCompte compte : listeCompte){
            if(compte.getUtilisateur().equals(utilisateur)) {
                return compte.getPatient(); // Retourne le patient associé à l'utilisateur
            }
        }
        return null; // Retourne null si aucun patient trouvé pour cet utilisateur
    }
    

    //Rechercher si un nom d'utilisateur existe
    public boolean rechercheUtil(List<SessionCompte> listeCompte, String utilisateur){
        for(SessionCompte compte : listeCompte){
            if(compte.getUtilisateur().equals(utilisateur)) {
                return true;
            }
        }
        return false;
    }

    //Vérifier si le mot de passe est correct
    public boolean verifMDP(List<SessionCompte> listeCompte, String utilisateur, String mdp){
        for(SessionCompte compte : listeCompte){
            if(compte.getUtilisateur().equals(utilisateur) && compte.getMdp().equals(mdp)) {
                return true;
            }
        }
        return false;
    }

    //Connexion à un compte
    public boolean connexion(List<SessionCompte> listeCompte){
        if(listeCompte.isEmpty()) {
            System.out.println("Aucun compte n'a été créé.");
            return false;
        } else if(!rechercheUtil(listeCompte, utilisateur)) {
            System.out.println("Cet utilisateur n'existe pas");
            return false;
        } else {
            if (!verifMDP(listeCompte, utilisateur, mdp)) {
                System.out.println("Mot de passe incorrect");
                return false;
            } else {
                System.out.println("Connexion réussie");
                return true;
            }
        }
    }

    //Création d'un nouveau compte
    public boolean creerCompte(List<SessionCompte> listeCompte){
        if(rechercheUtil(listeCompte, utilisateur)){
            System.out.println("Nom d'utilisateur existe déjà");
            return false;
        } else if(mdp.length() < 8){
            System.out.println("Le mot de passe doit contenir au moins 8 caractères, réessayez");
            return false;
        } else {
            System.out.println("Compte créé, étape suivante :");
            return true;
        }
    }

    //Getteurs et Setteurs
    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
