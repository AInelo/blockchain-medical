package org.blockchain;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class SmartContract {
    // Liste des autorisations pour chaque patient
    private Map<String, List<String>> autorisations; // patientId -> liste des medecinIds
    private List<Transaction> historiqueTransactions; // historique des actions

    public SmartContract() {
        autorisations = new HashMap<>();
        historiqueTransactions = new ArrayList<>();
    }

    // Méthode pour donner l'autorisation d'accès à un médecin pour un patient
    public void donnerAutorisation(String patientId, String medecinId) {
        autorisations.computeIfAbsent(patientId, k -> new ArrayList<>()).add(medecinId);
        enregistrerTransaction("Autorisation accordée", patientId, medecinId);
    }

    // Méthode pour retirer l'autorisation d'accès
    public void retirerAutorisation(String patientId, String medecinId) {
        List<String> medecinsAutorises = autorisations.getOrDefault(patientId, new ArrayList<>());
        medecinsAutorises.remove(medecinId);
        enregistrerTransaction("Autorisation retirée", patientId, medecinId);
    }

    // Vérifie si un médecin a accès au dossier d'un patient
    public boolean verifierAcces(String patientId, String medecinId) {
        List<String> medecinsAutorises = autorisations.get(patientId);
        return medecinsAutorises != null && medecinsAutorises.contains(medecinId);
    }

    // Enregistre les transactions pour un historique immuable
    private void enregistrerTransaction(String type, String patientId, String medecinId) {
        Transaction transaction = new Transaction(type, medecinId, patientId);
        historiqueTransactions.add(transaction);
    }

    // Méthode pour voir l'historique des transactions
    public List<Transaction> getHistoriqueTransactions() {
        return historiqueTransactions;
    }

    // Ajoute une prescription si le médecin est autorisé
    public void ajouterPrescription(String patientId, String medecinId, String detailsPrescription) {
        if (verifierAcces(patientId, medecinId)) {
            enregistrerTransaction("Ajout de prescription", patientId, medecinId);
            System.out.println("Prescription ajoutée pour le patient " + patientId + " par le médecin " + medecinId);
        } else {
            System.out.println("Accès refusé pour le médecin " + medecinId);
        }
    }
}
