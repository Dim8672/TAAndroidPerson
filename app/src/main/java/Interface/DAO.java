package Interface;

import business.Person;

/**
 * Created by dimitri.mella on 15.12.2016.
 */

public interface DAO {
    public void create(Person person);
    public void update(Person person);
    public void delete(Person person);
    public Person searchByNom(String nom);
    public Person searchById(Long id);
}
