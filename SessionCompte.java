import java.util.ArrayList;

public class SessionCompte {
    private String utilisateur;
    private String mdp;
    private Patient patient;
    
    //Constructeurs
    public SessionCompte(){}

    public SessionCompte(String utilisateur, String mdp, Patient patient) {
        this.utilisateur = utilisateur;
        this.mdp = mdp;
        this.patient = patient;
    }
    
    //Méthodes
    //Ajouter un compte pour le sauvgarder
    public void ajoutCompte(ArrayList<SessionCompte> listeCompte, SessionCompte compte){
        listeCompte.add(compte);
    }

    //Supprimer un compte
    public void supprimerCompte(ArrayList<SessionCompte> listeCompte, String utilisateur) {
        //Supprime le compte de l'utilisateur de la liste
        listeCompte.removeIf(compte -> compte.getUtilisateur().equals(utilisateur));
    }

    //Rechercher si un nom d'utilisateur existe
    public boolean rechercheUtil(ArrayList<SessionCompte> listeCompte, String utilisateur){
        for(SessionCompte compte : listeCompte){
            if(compte.getUtilisateur().equals(utilisateur)) {
                return true;
            }
        }
        return false;
    }

    //Verification si le mot de passe est correcte
    public boolean verifMDP(ArrayList<SessionCompte> listeCompte, String utilisateur, String mdp){
        for(SessionCompte compte : listeCompte){
            if(compte.getUtilisateur().equals(utilisateur) && compte.getMdp().equals(mdp)) {
                return true;
            }
        }
        return false;
    }

    //Connexion à un compte
    public void connexion(ArrayList<SessionCompte> listeCompte, String utilisateur, String mdp){
        if(!rechercheUtil(listeCompte, utilisateur)) {
            System.out.println("Cet utilisateur n'existe pas");
        }
        else{
            if (!verifMDP(listeCompte, utilisateur, mdp)) {
                System.out.println("Mot de passe incorrect");
            } else {
                System.out.println("Connexion réussie");
            }
        }
    }

    //Création d'un npuveau compte
    public void créerCompte(ArrayList<SessionCompte> listeCompte, String utilisateur, String mdp){
        if(rechercheUtil(listeCompte, utilisateur)){
            System.out.println("Nom d'utilisateur existe déjà");
        }
        else{
            if(mdp.length()<8){
                System.out.println("Le mot de passe doit contenir au moins 8 caractères, réessayez");
            }
            else{
                System.out.println("Compte créé, étape suivante :");
            }
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
