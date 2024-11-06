package org.blockchain;
public class Patient {
    private String id;
    private String nom;
    private SmartContract contrat;

    public Patient(String id, String nom, SmartContract contrat) {
        this.id = id;
        this.nom = nom;
        this.contrat = contrat;
    }

    // Getter pour l'ID du patient
    public String getId() {
        return id;
    }

    // Getter pour le nom du patient
    public String getNom() {
        return nom;
    }

    // Donner l'autorisation d'accès à un médecin
    public void donnerAutorisation(String medecinId) {
        contrat.donnerAutorisation(id, medecinId);
        System.out.println("Autorisation donnée au médecin " + medecinId + " pour accéder au dossier du patient " + id);
    }

    // Retirer l'autorisation d'accès d'un médecin
    public void retirerAutorisation(String medecinId) {
        contrat.retirerAutorisation(id, medecinId);
        System.out.println("Autorisation retirée pour le médecin " + medecinId + " pour le patient " + id);
    }

    // Ajouter une prescription par un médecin autorisé
    public void ajouterPrescription(String medecinId, String detailsPrescription) {
        if (contrat.verifierAcces(id, medecinId)) {
            contrat.ajouterPrescription(id, medecinId, detailsPrescription);
            System.out.println("Prescription ajoutée par le médecin " + medecinId + " pour le patient " + id);
        } else {
            System.out.println("Le médecin " + medecinId + " n'a pas l'autorisation d'ajouter une prescription pour le patient " + id);
        }
    }

    // Consulter l'historique des transactions pour ce patient
    public void afficherHistorique() {
        System.out.println("Historique des transactions pour le patient " + id + " :");
        for (Transaction transaction : contrat.getHistoriqueTransactions()) {
            if (transaction.getPatientId().equals(id)) {
                System.out.println(transaction);
            }
        }
    }
}
