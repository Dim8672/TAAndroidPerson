package dao;

import Interface.DAO;
import business.Person;
import utilitaire.Utilitaire;

/**
 * Created by dimitri.mella on 15.12.2016.
 */

public class PersonDAO implements DAO {
    @Override
    public void create(Person person) {
        person.setId(Utilitaire.getNextId());
        Utilitaire.people.add(person);
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public void searchByNom(String nom) {

    }
}
