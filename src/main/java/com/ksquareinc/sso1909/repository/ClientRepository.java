package com.ksquareinc.sso1909.repository;

import com.ksquareinc.sso1909.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

public interface ClientRepository extends JpaRepository<Client, String>, Serializable {

}
