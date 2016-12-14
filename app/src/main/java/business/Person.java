package business;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by dimitri.mella on 07.12.2016.
 */

public class Person implements Parcelable {

    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;

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

    public Person(Parcel in){
        this.nom = in.readString();
        this.prenom = in.readString();
        this.adresse = in.readString();
    }

    public Person(String nom, String prenom, String adresse){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeString(adresse);
    }
}
