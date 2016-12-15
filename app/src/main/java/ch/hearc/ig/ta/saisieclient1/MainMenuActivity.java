package ch.hearc.ig.ta.saisieclient1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import utilitaire.Utilitaire;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        this.startActivity(intent);
    }

    /**
     * Démarrer l'activité après avoir cliqué sur le bouton ShowList
     * @param v
     */
    public void onButtonClickShowPersonList(View v){
        Intent intent = new Intent(MainMenuActivity.this,ShowPersonActivity.class);
        this.startActivity(intent);
    }
}
