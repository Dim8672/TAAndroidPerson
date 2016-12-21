package ch.hearc.ig.ta.saisieclient1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import business.Person;
import communication.PersonneServiceInvocator;

public class ShowPersonActivity extends AppCompatActivity {

    protected TextView test;
    protected InitTask initTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);

        test = (TextView) findViewById(R.id.Nom);
        initTask = new InitTask();
        initTask.execute(this);

        /*this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Intent intent = this.getIntent();
        Person person = (Person) this.getIntent().getExtras().getParcelable("person");
        Log.v("Nom",person.getNom().toString());
        TextView nom = (TextView) findViewById(R.id.Nom);
        TextView prenom = (TextView) findViewById(R.id.Prenom);
        TextView adresse = (TextView) findViewById(R.id.Adresse);
        nom.setText(person.getNom());
        prenom.setText(person.getPrenom());
        adresse.setText(person.getAdresse());*/
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_affichage, menu);
        return true;
    }*/

    // Utilisation d'une classe interne qui hérite de AsyncTask
    protected class InitTask extends AsyncTask<Context, Integer, String> {

        List<Person> personnes = new ArrayList<Person>();
        String test2;
        @Override
        protected String doInBackground( Context... params ) {

            // personnes = PersonneServiceInvocator.serviceR(702, null, null, null, null); // 702 correspond à l'id d'un enregistrement de ma base
            test2 = PersonneServiceInvocator.sendHttpGet(null,null);
            return null;
        }

        protected void onPostExecute(String s){
            // s = personnes.get(0).getNom();
            s = test2;
            test.setText(s);
        }

    }
}
