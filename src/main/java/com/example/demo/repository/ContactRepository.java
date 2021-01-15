package com.example.demo.repository;

import com.example.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findContactByFirstName(String firstName);

    @Query("SELECT s FROM Contact s WHERE s.firstName =:firstName AND s.lastName =:lastName")
    Contact findContactByNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    void deleteContactByFirstName(String firstName);

}
