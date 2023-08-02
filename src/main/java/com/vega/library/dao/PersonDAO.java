package com.vega.library.dao;

import com.vega.library.models.Book;
import com.vega.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Persons(full_name, yearofthebirth, email) VALUES (?, ?, ?)",
                person.getFull_name(), person.getYearOfTheBirth(), person.getEmail());
    }
    public void update(int id, Person updatedPerson)  {
        jdbcTemplate.update("UPDATE Persons SET full_name = ?, yearOfThebirth = ?, email = ? WHERE id = ?",
                updatedPerson.getFull_name(), updatedPerson.getYearOfTheBirth(), updatedPerson.getEmail(), id);
    }
    public void delete(int id)  {
        jdbcTemplate.update("DELETE FROM Persons WHERE id = ?", id);
    }
    public List<Person> index(){
       return jdbcTemplate.query("SELECT * FROM Persons", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Persons WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Person> show(String full_name){
        return jdbcTemplate.query("SELECT * FROM Persons WHERE full_name = ?", new Object[]{full_name}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    public List<Book> getBooksOfPerson(int id){
        return jdbcTemplate.query("SELECT * FROM Books WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }



}
