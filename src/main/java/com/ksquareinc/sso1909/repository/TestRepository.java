package com.ksquareinc.sso1909.repository;

import com.ksquareinc.sso1909.domain.Client;
import com.ksquareinc.sso1909.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface TestRepository extends JpaRepository<Test, Long>, Serializable {

}
