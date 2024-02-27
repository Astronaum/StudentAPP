package Entity;

public class Student {

    public String nom;
    public String prenom;
    public String cne;
    public String telephone;

    public Student(String nom, String prenom, String cne, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.telephone = telephone;
    }

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

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cne='" + cne + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
