package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import business.Person;
import ch.hearc.ig.ta.saisieclient1.R;
import ch.hearc.ig.ta.saisieclient1.ShowPersonListActivity;
import utilitaire.Utilitaire;

/**
 * Created by dimitri.mella on 12.12.2016.
 */

public class PersonAdapter extends ArrayAdapter<Person> implements Filterable {

    private int itemPositionToShowButton;
    private Person personSelected;
    private List<Person> filteredData;


    public PersonAdapter(Context context, List<Person> people) {
        super(context, 0,people);
        this.itemPositionToShowButton = - 1;
        this.filteredData = people;
    }

    public void setItemPositionToShowButton(int i){
        this.itemPositionToShowButton = i;
    }

    public void setPersonSelected(Person personSelected) { this.personSelected = personSelected;}

    public Person getPersonSelected(){ return this.personSelected;}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_person,parent, false);
        }

        ImageButton editPerson = (ImageButton)convertView.findViewById(R.id.buttonEditPerson);
        ImageButton deletePerson = (ImageButton)convertView.findViewById(R.id.buttonDeletePerson);

        editPerson.setFocusable(false);
        editPerson.setFocusableInTouchMode(false);
        editPerson.setClickable(true);

        deletePerson.setFocusable(false);
        deletePerson.setFocusable(false);
        deletePerson.setClickable(true);

        if(filteredData.size() != 0) {

            Person p = filteredData.get(position);

            ((TextView) convertView.findViewById(R.id.viewId)).setText(p.getId().toString());
            ((TextView) convertView.findViewById(R.id.viewNom)).setText(p.getNom());
            ((TextView) convertView.findViewById(R.id.viewPrenom)).setText(p.getPrenom());
            ((TextView) convertView.findViewById(R.id.viewAddress)).setText(p.getAdresse());
            ((TextView) convertView.findViewById(R.id.viewCity)).setText(p.getVille());
            ((TextView) convertView.findViewById(R.id.viewDateNaissance)).setText(Utilitaire.convertDateToString(p.getDateNaissance()));

            if (this.itemPositionToShowButton == position) {
                if (editPerson.getVisibility() == View.VISIBLE) {
                    editPerson.setVisibility(View.GONE);
                    deletePerson.setVisibility(View.GONE);
                } else {
                    editPerson.setVisibility(View.VISIBLE);
                    deletePerson.setVisibility(View.VISIBLE);
                }
            } else {
                editPerson.setVisibility(View.GONE);
                deletePerson.setVisibility(View.GONE);
            }
        }
        return convertView;
    }
    @Override
    public Filter getFilter(){
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence charSequence){
                FilterResults results = new FilterResults();

                if(charSequence == null || charSequence.length() == 0){
                    results.values = Utilitaire.people;
                    results.count = Utilitaire.people.size();
                } else {
                    ArrayList<Person> filterResultData = new ArrayList<>();
                    for(Person person : Utilitaire.people){
                        if(person.getNom().contains(charSequence)){
                            filterResultData.add(person);
                        }
                    }
                    results.values = filterResultData;
                    results.count = filterResultData.size();
                }
                return results;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults){
                filteredData = (List<Person>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
