package business;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import utilitaire.Utilitaire;

/**
 * Created by dimitri.mella on 07.12.2016.
 */

public class Person implements Parcelable {

    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private Date dateNaissance;

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>()
    {
        @Override
        public Person createFromParcel(Parcel source)
        {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size)
        {
            return new Person[size];
        }
    };

    public Person(){

    }

    public Person(Parcel in){
        this.id = in.readLong();
        this.nom = in.readString();
        this.prenom = in.readString();
        this.adresse = in.readString();
        this.ville = in.readString();
        this.dateNaissance = Utilitaire.convertStringToDate(in.readString());
    }

    public Person(String nom, String prenom, String adresse, String ville, Date dateNaissance){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.dateNaissance = dateNaissance;
    }

    public Person(Long id, String nom, String prenom, String adresse, String ville, Date dateNaissance){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeString(adresse);
        parcel.writeString(ville);
        parcel.writeString(Utilitaire.convertDateToString(dateNaissance));
    }
}
