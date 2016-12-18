package ch.hearc.ig.ta.saisieclient1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import business.Person;
import dao.PersonDAO;
import utilitaire.Utilitaire;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PersonDAO personDAO = new PersonDAO();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        TextView editNbPeople =  (TextView) this.findViewById(R.id.nbPeople);
        editNbPeople.setText("Nombre de personnes : " + Utilitaire.people.size());
    }

    /**
     * Démarrer l'activité InputPersonActivity après avoir cliqué sur le bouton CreatePerson
     * @param v
     */
    public void onButtonClickCreatePerson(View v){
        Intent intent = new Intent(MainMenuActivity.this,InputPersonActivity.class);
        intent.putExtra("create",true);
        this.startActivity(intent);
    }

    /**
     * Démarrer l'activité de l'affichage des personnes après avoir cliqué sur le bouton ShowList
     * @param v
     */
    public void onButtonClickShowPersonList(View v){
        Intent intent = new Intent(MainMenuActivity.this,ShowPersonListActivity.class);
        this.startActivity(intent);
    }
}
