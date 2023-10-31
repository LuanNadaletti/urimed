package com.uri.urimed.repository;

import com.uri.urimed.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
