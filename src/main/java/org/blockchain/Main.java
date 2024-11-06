package org.blockchain;

public class Main {
    public static void main(String[] args) {
        SmartContract contrat = new SmartContract();

        // Identifiants fictifs pour les patients et médecins
        String patientId = "P001";
        String medecinId1 = "M001";
        String medecinId2 = "M002";

        // Donne accès à un médecin
        contrat.donnerAutorisation(patientId, medecinId1);

        // Essai d'ajout de prescription par un médecin autorisé
        contrat.ajouterPrescription(patientId, medecinId1, "Prescription d'antibiotiques");

        // Tentative d'ajout par un médecin non autorisé
        contrat.ajouterPrescription(patientId, medecinId2, "Prescription de vitamines");

        // Historique des transactions
        System.out.println("\nHistorique des transactions :");
        for (Transaction transaction : contrat.getHistoriqueTransactions()) {
            System.out.println(transaction);
        }

        // Retrait de l'autorisation pour le médecin 1
        contrat.retirerAutorisation(patientId, medecinId1);

        // Nouvelle tentative d'ajout de prescription par le médecin 1
        contrat.ajouterPrescription(patientId, medecinId1, "Nouvelle prescription d'analgésiques");
    }
}
