package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import business.Person;
import ch.hearc.ig.ta.saisieclient1.R;

/**
 * Created by dimitri.mella on 12.12.2016.
 */

public class PersonAdapter extends ArrayAdapter<Person> {


    public PersonAdapter(Context context, ArrayList<Person> people) {
        super(context, 0,people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_person,parent, false);
        }

        Person p = getItem(position);
        ((TextView) convertView.findViewById(R.id.viewNom)).setText(p.getNom());
        ((TextView) convertView.findViewById(R.id.viewPrenom)).setText(p.getPrenom());
        ((TextView) convertView.findViewById(R.id.viewAdress)).setText(p.getAdresse());

        return convertView;
    }
}
