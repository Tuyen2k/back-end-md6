package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.MailStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMailRepository extends JpaRepository<MailStructure, Long> {
}
