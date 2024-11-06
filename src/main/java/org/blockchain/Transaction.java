package org.blockchain;

import java.util.Date;

import java.util.Date;

public class Transaction {
    private String type; // Type de transaction (ex : "Consultation", "Ajout de prescription")
    private String medecinId;
    private String patientId;
    private long timeStamp;

    public Transaction(String type, String medecinId, String patientId) {
        this.type = type;
        this.medecinId = medecinId;
        this.patientId = patientId;
        this.timeStamp = new Date().getTime();
    }

    public String getType() {
        return type;
    }

    public String getMedecinId() {
        return medecinId;
    }

    public String getPatientId() {
        return patientId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", medecinId='" + medecinId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}

