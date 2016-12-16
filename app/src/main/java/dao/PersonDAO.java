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
        for(Person oldPerson : Utilitaire.people){
            if(person.getId().equals(oldPerson.getId())){
                oldPerson.setAdresse(person.getAdresse());
                oldPerson.setDateNaissance(person.getDateNaissance());
                oldPerson.setNom(person.getNom());
                oldPerson.setPrenom(person.getPrenom());
                oldPerson.setVille(person.getVille());
            }
        }
    }

    @Override
    public void delete(Person person) {
        Utilitaire.people.remove(person);
    }

    @Override
    public Person searchByNom(String nom) {
        return null;
    }

    @Override
    public Person searchById(Long id){
        for(Person person : Utilitaire.people){
            if(person.getId().equals(id)){
                return person;
            }
        }
        return null;
    }
}
