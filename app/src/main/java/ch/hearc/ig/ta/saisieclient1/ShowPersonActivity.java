package ch.hearc.ig.ta.saisieclient1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import business.Person;

public class ShowPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Intent intent = this.getIntent();
        Person person = (Person) this.getIntent().getExtras().getParcelable("person");
        Log.v("Nom",person.getNom().toString());
        TextView nom = (TextView) findViewById(R.id.Nom);
        TextView prenom = (TextView) findViewById(R.id.Prenom);
        TextView adresse = (TextView) findViewById(R.id.Adresse);
        nom.setText(person.getNom());
        prenom.setText(person.getPrenom());
        adresse.setText(person.getAdresse());
    }
}
