package com.ksquareinc.sso1909.service;

import com.ksquareinc.sso1909.domain.Test;

import java.util.List;

public interface TestService {
    List<Test> findAll();
    Test save(Test Test);
    Test update(Long id, Test Test);
    Test findById(Long id);
    void delete(Test Test);
    void deleteById(Long id);
    long count();

}
