package ch.hearc.ig.ta.saisieclient1;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import business.Person;
import dao.PersonDAO;
import utilitaire.Utilitaire;

public class InputPersonActivity extends AppCompatActivity {

    private PersonDAO personDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_person);
        this.personDAO = new PersonDAO();
    }

    /**
     * Apelle du DAO de saisie personne et retour sur le menu d'acceuil
     * @param v
     */
    public void onButtonClicked(View v){
        Log.v(getResources().getString(R.string.app_name),"Bouton ajout d'une personne clicked");
        EditText nom = (EditText) findViewById(R.id.editNom);
        EditText prenom = (EditText) findViewById(R.id.editPrenom);
        EditText adresse = (EditText) findViewById(R.id.editAdresse);
        EditText ville = (EditText) findViewById(R.id.editVille);
        EditText dateNaissance = (EditText) findViewById(R.id.editDateNaissance);
        Person person = new Person(nom.getText().toString(),prenom.getText().toString(),adresse.getText().toString(),ville.getText().toString(),Utilitaire.convertStringToDate(dateNaissance.getText().toString()));
        this.personDAO.create(person);
        Intent intent = new Intent(InputPersonActivity.this,MainMenuActivity.class);
        startActivity(intent);
    }

}
