package ch.hearc.ig.ta.saisieclient1;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import business.Person;
import dao.PersonDAO;
import utilitaire.Utilitaire;

public class InputPersonActivity extends AppCompatActivity {

    private PersonDAO personDAO;
    private Boolean create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_person);
        this.create = this.getIntent().getExtras().getBoolean("create");
        this.personDAO = new PersonDAO();

        if(!this.create){
            Person person = (Person) this.getIntent().getExtras().getParcelable("person");
            personDAO.searchById(person.getId());
            TextView id = (TextView) findViewById(R.id.id);
            id.setVisibility(View.VISIBLE);
            id.setText(String.valueOf(person.getId()));

            EditText nom =(EditText) findViewById(R.id.editNom);
            EditText prenom =(EditText) findViewById(R.id.editPrenom);
            EditText adresse =(EditText) findViewById(R.id.editAdresse);
            EditText ville =(EditText) findViewById(R.id.editVille);
            EditText dateNaissance =(EditText) findViewById(R.id.editDateNaissance);

            nom.setText(person.getNom());
            prenom.setText(person.getPrenom());
            adresse.setText(person.getAdresse());
            ville.setText(person.getVille());
            dateNaissance.setText(Utilitaire.convertDateToString(person.getDateNaissance()));


            Button createButton = (Button) findViewById(R.id.buttonCreate);
            Button editButton = (Button) findViewById(R.id.buttonupdate);

            createButton.setVisibility(View.GONE);
            editButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Apelle du DAO de saisie ou update personne et retour sur le menu d'acceuil
     * @param v
     */
    public void onButtonClicked(View v){
        EditText nom = (EditText) findViewById(R.id.editNom);
        EditText prenom = (EditText) findViewById(R.id.editPrenom);
        EditText adresse = (EditText) findViewById(R.id.editAdresse);
        EditText ville = (EditText) findViewById(R.id.editVille);
        EditText dateNaissance = (EditText) findViewById(R.id.editDateNaissance);
        Person person = new Person(nom.getText().toString(),prenom.getText().toString(),adresse.getText().toString(),ville.getText().toString(),Utilitaire.convertStringToDate(dateNaissance.getText().toString()));
        if(this.create){
            this.personDAO.create(person);
            Intent intent = new Intent(InputPersonActivity.this,MainMenuActivity.class);
            startActivity(intent);
        } else {
            TextView id = (TextView) findViewById(R.id.id);
            person.setId(new Long (id.getText().toString()));
            this.personDAO.update(person);
            Intent intent2 = new Intent(InputPersonActivity.this,ShowPersonListActivity.class);
            startActivity(intent2);
        }

    }

}
