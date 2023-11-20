package com.uri.urimed.repository;

import com.uri.urimed.model.Person;
import com.uri.urimed.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findPersonByUsername(String username);

    @Query("SELECT p.role FROM Person p WHERE p.username = ?1")
    Role findPersonRoleByUsername(String username);
}
