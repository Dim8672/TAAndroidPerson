package ch.hearc.ig.ta.saisieclient1;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import business.Person;
import utilitaire.Utilitaire;

public class InputPersonActivity extends AppCompatActivity {

    private ArrayList<Parcelable> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Object temp = this.getIntent().getParcelableExtra("people");
        if(temp != null){
            people = this.getIntent().getParcelableArrayListExtra("people");
        } else {
            people = new ArrayList<Parcelable>();
        }
        setContentView(R.layout.activity_input_person);
        TextView nbPeople = (TextView) this.findViewById(R.id.nbPeople);
        nbPeople.setText("Nombre de personnes : " + this.people.size());

    }

    public void onButtonClicked(View v){
        Log.v(getResources().getString(R.string.app_name),"Bouton ajout et afficher personne clicked");
        EditText nom = (EditText) findViewById(R.id.editNom);
        EditText prenom = (EditText) findViewById(R.id.editPrenom);
        EditText adresse = (EditText) findViewById(R.id.editAdresse);
        Person person = new Person(nom.getText().toString(),prenom.getText().toString(),adresse.getText().toString());
        Log.v("Nom", person.getNom());
        Utilitaire.people.add(person);
        this.people.add(person);
        Intent intent = new Intent(InputPersonActivity.this,ShowPersonActivity.class);
        intent.putExtra("person",person);
        startActivity(intent);
    }

    public void onButtonClickedShowList(View v){
        Log.v(getResources().getString(R.string.app_name),"Bouton ajout et afficher la liste des personnes clicked");
        EditText nom = (EditText) findViewById(R.id.editNom);
        EditText prenom = (EditText) findViewById(R.id.editPrenom);
        EditText adresse = (EditText) findViewById(R.id.editAdresse);
        Person person = new Person(nom.getText().toString(),prenom.getText().toString(),adresse.getText().toString());
        Utilitaire.people.add(person);
        this.people.add(person);
        Intent intent = new Intent(InputPersonActivity.this,ShowPersonListActivity.class);
        intent.putParcelableArrayListExtra("people",this.people);
        startActivity(intent);
    }
}
