package adapter;

import android.app.Activity;
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

    private Activity activity;
    private static LayoutInflater inflater;
    private int itemPositionToShowButton;
    private Person personSelected;
    private List<Person> filteredData;
    private List<Person> originalData;


    public PersonAdapter(Activity a, List<Person> people) {
        super(a, 0,people);
        activity = a;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemPositionToShowButton = - 1;
        this.filteredData = people;
        this.originalData = people;
    }

    public void setItemPositionToShowButton(int i){
        this.itemPositionToShowButton = i;
    }

    public void setPersonSelected(Person personSelected) { this.personSelected = personSelected;}

    public Person getPersonSelected(){ return this.personSelected;}

    /**
     * Réecriture de la méthode pour qu'elle retourne le size de la filteredList (autrement c'est le size de l'orginal)
     * @return
     */
    public int getCount(){
        return filteredData.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.row_person,null);
        }

        ImageButton editPerson = (ImageButton)convertView.findViewById(R.id.buttonEditPerson);
        ImageButton deletePerson = (ImageButton)convertView.findViewById(R.id.buttonDeletePerson);

        editPerson.setFocusable(false);
        editPerson.setFocusableInTouchMode(false);
        editPerson.setClickable(true);

        deletePerson.setFocusable(false);
        deletePerson.setFocusable(false);
        deletePerson.setClickable(true);

        Person p = new Person();
        p = filteredData.get(position);

            ((TextView) convertView.findViewById(R.id.viewId)).setText(p.getId().toString());
            ((TextView) convertView.findViewById(R.id.viewNom)).setText(p.getNom());
            ((TextView) convertView.findViewById(R.id.viewPrenom)).setText(p.getPrenom());
            ((TextView) convertView.findViewById(R.id.viewAddress)).setText(p.getAdresse());
            ((TextView) convertView.findViewById(R.id.viewCity)).setText(p.getVille());
//            ((TextView) convertView.findViewById(R.id.viewDateNaissance)).setText(Utilitaire.convertDateToString(p.getDateNaissance()));

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
            return convertView;

    }
    @Override
    public Filter getFilter(){
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence charSequence){
                FilterResults results = new FilterResults();

                if(charSequence == null || charSequence.length() == 0){
                    results.values = originalData;
                    results.count = originalData.size();
                } else {
                    List<Person> filterResultData = new ArrayList<>();
                    for(Person person : originalData){
                        if(person.getNom().startsWith(charSequence.toString())){
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
