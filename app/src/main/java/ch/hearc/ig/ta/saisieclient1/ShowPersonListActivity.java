package ch.hearc.ig.ta.saisieclient1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.PersonAdapter;
import business.Person;
import dao.PersonDAO;
import utilitaire.Utilitaire;

public class ShowPersonListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private PersonAdapter adapter = null;
    private PersonDAO personDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person_list);
        Intent intent = this.getIntent();
        ListView listView = (ListView) this.findViewById(R.id.listView1);
        adapter = new PersonAdapter(ShowPersonListActivity.this, Utilitaire.people);
        personDAO = new PersonDAO();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
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
        Intent intent = new Intent(ShowPersonListActivity.this,ShowPersonListActivity.class);
        this.startActivity(intent);
    }

    public void onBackPressed(){
        Intent intent = new Intent(ShowPersonListActivity.this, MainMenuActivity.class);
        this.startActivity(intent);
    }
}
