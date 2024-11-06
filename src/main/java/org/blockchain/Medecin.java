package org.blockchain;

import java.util.List;

public class Medecin {
    private String id;
    private String nom;
    private SmartContract contrat;
    private Blockchain blockchain;

    public Medecin(String id, String nom, SmartContract contrat, Blockchain blockchain) {
        this.id = id;
        this.nom = nom;
        this.contrat = contrat;
        this.blockchain = blockchain;
    }

    public void ajouterPrescription(Patient patient, String detailsPrescription) {
        if (contrat.verifierAcces(patient.getId())) {
            Transaction transaction = new Transaction("Prescription", id, patient.getId(), detailsPrescription);
            ajouterTransaction(transaction);
            System.out.println("Prescription ajoutée pour le patient " + patient.getNom());
        } else {
            System.out.println("Accès refusé pour " + nom);
        }
    }

    private void ajouterTransaction(Transaction transaction) {
        Block newBlock = new Block(blockchain.getLatestBlock().getHash(), List.of(transaction));
        blockchain.addBlock(newBlock);
    }
}
