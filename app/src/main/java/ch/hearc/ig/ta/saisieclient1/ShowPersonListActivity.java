package ch.hearc.ig.ta.saisieclient1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.PersonAdapter;
import business.Person;
import communication.PersonneServiceInvocator;
import dao.PersonDAO;
import utilitaire.Utilitaire;

public class ShowPersonListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private PersonAdapter adapter = null;
    private PersonDAO personDAO;
    private AlertDialog alert;
    private ListView listView;
    protected InitTask initTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person_list);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Intent intent = this.getIntent();
        listView = (ListView) this.findViewById(R.id.listView1);
        personDAO = new PersonDAO();
        listView.setOnItemClickListener(this);
        initTask = new ShowPersonListActivity.InitTask();
        initTask.execute(this);

        EditText inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ShowPersonListActivity.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        adapter.setItemPositionToShowButton(i);
        Person item = (Person) adapterView.getItemAtPosition(i);
        adapter.setPersonSelected(item);
        Toast.makeText(adapterView.getContext(),item.getNom(),Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    public void onButtonEditClick(View v){
        Intent intent = new Intent(ShowPersonListActivity.this,InputPersonActivity.class);
        intent.putExtra("create",false);
        intent.putExtra("person",adapter.getPersonSelected());
        this.startActivity(intent);
    }

    public void onButtonDeleteClick(View v){
        personDAO.delete(adapter.getPersonSelected());
        Intent intent = new Intent(ShowPersonListActivity.this, ShowPersonListActivity.class);
        this.startActivity(intent);
    }

    public void onBackPressed(){
        Intent intent = new Intent(ShowPersonListActivity.this, MainMenuActivity.class);
        this.startActivity(intent);
    }

    protected class InitTask extends AsyncTask<Context, Integer, String> {

        String test2;
        @Override
        protected String doInBackground( Context... params ) {
            // personnes = PersonneServiceInvocator.serviceR(702, null, null, null, null); // 702 correspond à l'id d'un enregistrement de ma base
            Utilitaire.people = PersonneServiceInvocator.serviceRAll();
            return null;
        }

        protected void onPostExecute(String s){
            // s = personnes.get(0).getNom();
            adapter = new PersonAdapter(ShowPersonListActivity.this, Utilitaire.people);
            listView.setAdapter(adapter);
        }

    }
}
