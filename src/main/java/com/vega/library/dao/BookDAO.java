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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Books(title, author, year) VALUES(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Books WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE Books SET title = ?, author = ?, year = ? WHERE id = ?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Books WHERE id = ?", id);
    }

    public Optional<Book> show(String title){
        return jdbcTemplate.query("SELECT * FROM Books WHERE title = ?", new Object[]{title}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }
    public Optional<Person> getReserve(int id){
        return jdbcTemplate.query("SELECT Persons.* FROM Persons JOIN Books ON Persons.id = Books.person_id WHERE Books.id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                        .stream().findAny();
    }
    public void release(int id){
        jdbcTemplate.update("UPDATE Books SET person_id = NULL WHERE id = ?", id);
    }
    public void assign(int id, Person takenPerson){
        jdbcTemplate.update("UPDATE Books SET person_id = ? WHERE id = ?", takenPerson.getId(), id);
    }
}
