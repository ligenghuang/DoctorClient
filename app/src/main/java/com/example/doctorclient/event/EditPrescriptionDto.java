package com.example.doctorclient.event;

public class EditPrescriptionDto {

    private int depart;
    private String departName;
    private String prescriptionName;
    private String note;

    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public String getDepartName() {
        return departName == null ? "" : departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getPrescriptionName() {
        return prescriptionName == null ? "" : prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"depart\":")
                .append(depart);
        sb.append(",\"departName\":\"")
                .append(departName).append('\"');
        sb.append(",\"prescriptionName\":\"")
                .append(prescriptionName).append('\"');
        sb.append(",\"note\":\"")
                .append(note).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
