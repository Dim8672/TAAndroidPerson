package ch.hearc.ig.ta.saisieclient1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.PersonAdapter;
import business.Person;

public class ShowPersonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person_list);
        Intent intent = this.getIntent();
        ListView listView = (ListView) this.findViewById(R.id.listView1);
        ArrayList<Person> people = this.getIntent().getParcelableArrayListExtra("people");
        PersonAdapter adapter = new PersonAdapter(ShowPersonListActivity.this, people);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person item = (Person) adapterView.getItemAtPosition(i);
                Toast.makeText(adapterView.getContext(),item.getNom(),Toast.LENGTH_SHORT).show();
            }
            });

       /* String[] values = new String[] {"Android", "iphone", "WindowsMobile"};
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.row_person,R.id.viewNom,values);
        listView.setAdapter(adapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v(getResources().getString(R.string.app_name),"position " + String.valueOf(i));
                Log.v(getResources().getString(R.string.app_name),"row_id " + String.valueOf(l));

                String item = (String) adapterView.getItemAtPosition(i);
                Log.v(getResources().getString(R.string.app_name),"Item " + item);

                Toast.makeText(adapterView.getContext(),item,Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
