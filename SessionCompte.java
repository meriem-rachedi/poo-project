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
        if (listeCompte.isEmpty()) {
            System.out.println("Liste vide : aucun compte n'a été ajouté.");
            return;
        }
        listeCompte.add(compte);
    }

    //Supprimer un compte
    public void supprimerCompte(List<SessionCompte> listeCompte, String utilisateur) {
        //Supprime le compte de l'utilisateur de la liste
        if (listeCompte.isEmpty()) {
            System.out.println("Liste vide : aucun compte à supprimer.");
            return;
        }
        listeCompte.removeIf(compte -> compte.getUtilisateur().equals(utilisateur));
    }

    //Modifier les informations du patient d'un compte
    public void modifInfoC(List<SessionCompte> listeCompte, Patient patientToReplace, Patient newPatient) {
        if (listeCompte.isEmpty()) {
            System.out.println("Liste vide : aucun compte à modifier.");
            return;
        }
        for (SessionCompte compte : listeCompte) {
            if (compte.getPatient() == patientToReplace) {
                compte.setPatient(newPatient);
                return;
            }
        }
        System.out.println("Le patient à remplacer n'a pas été trouvé dans la liste de comptes.");
    }
    

    //Retourner le patient associé à un compte
    public Patient rechercherPatientCompte(List<SessionCompte> listeCompte) {
        if (listeCompte.isEmpty()) {
            System.out.println("Liste vide : aucun compte à rechercher.");
            return null;
        }
        for(SessionCompte compte : listeCompte){
            if(compte.getUtilisateur().equals(utilisateur)) {
                return compte.getPatient(); // Retourne le patient associé à l'utilisateur
            }
        }
        return null; // Retourne null si aucun patient trouvé pour cet utilisateur
    }
    

    //Rechercher si un nom d'utilisateur existe
    public boolean rechercheUtil(List<SessionCompte> listeCompte, String utilisateur){
        if (listeCompte.isEmpty()) {
            System.out.println("Liste vide : aucun compte à rechercher.");
            return false;
        }
        for(SessionCompte compte : listeCompte){
            if(compte.getUtilisateur().equals(utilisateur)) { //Chercher le nom d'utilisateur
                return true; //nom d'utilisateur trouvé
            }
        }
        return false;
    }

    //Vérifier si le mot de passe du compte est correct
    public boolean verifMDP(List<SessionCompte> listeCompte, String utilisateur, String mdp){
        if (listeCompte.isEmpty()) {
            System.out.println("Liste vide : aucun compte à vérifier.");
            return false;
        }
        for(SessionCompte compte : listeCompte){
            if(compte.getUtilisateur().equals(utilisateur) && compte.getMdp().equals(mdp)) { //si le mot de passe est correcte pour un certain nom d'utilisateur
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
        } else if(!rechercheUtil(listeCompte, utilisateur)) { //vérifier si le nom d'utilisateur entrer est correcte
            System.out.println("Cet utilisateur n'existe pas");
            return false;
        } else {
            if (!verifMDP(listeCompte, utilisateur, mdp)) { //vérifier si le mdp entrer est correcte
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
        if(rechercheUtil(listeCompte, utilisateur)){ //voir si le nom d'utilisateur est valable
            System.out.println("Nom d'utilisateur existe déjà");
            return false;
        } else if(mdp.length() < 8){ //verif mdp
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
