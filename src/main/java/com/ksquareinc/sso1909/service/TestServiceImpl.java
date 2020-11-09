package com.ksquareinc.sso1909.service;

import com.ksquareinc.sso1909.domain.Test;
import com.ksquareinc.sso1909.repository.TestRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

    TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test update(Long id, Test test) {
        Test oldTest = findById(id);
        if (test.getDescription() != null){
            oldTest.setDescription(test.getDescription());
        }
        if (test.getImage() != null){
            oldTest.setImage(test.getImage());
        }
        if (test.getName() != null){
            oldTest.setImage(test.getImage());
        }
        return testRepository.save(oldTest);
    }


    @Override
    public Test findById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Test test) {
        testRepository.delete(test);
    }

    @Override
    public void deleteById(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public long count() {
        return testRepository.count();
    }
}
