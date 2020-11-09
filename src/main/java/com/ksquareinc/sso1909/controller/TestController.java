package com.ksquareinc.sso1909.controller;

import com.ksquareinc.sso1909.domain.JsonResponse;
import com.ksquareinc.sso1909.domain.Test;
import com.ksquareinc.sso1909.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/example/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * Returns all registered tests and an HTTP OK status code
     * @return
     */
    @RequestMapping( method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "List Tests",
            notes = "Gets the info of all the tests. Returns a list with all the tests info",
            response = Test.class,
            responseContainer = "List")
    public ResponseEntity<?> getTests() {
        logger.info("Listing all tests");
        return new ResponseEntity<>(testService.findAll(), HttpStatus.OK);
    }

    /**
     * Returns the complete test information of the user name provided along with an HTTP OK status code.
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get Test",
            notes = "Gets the info of a test. Receives the test id in the path and returns the correspondent info",
            response = Test.class)
    public ResponseEntity<?> getTest(@PathVariable Long id) {
        logger.info("Returning test info for id " + id);
        Test test = testService.findById(id);
        if (test != null){
            return new ResponseEntity<>(testService.findById(id), HttpStatus.OK);
        }else{
            JsonResponse jsonResponse = new JsonResponse(HttpStatus.NOT_FOUND,
                    "There is no " + Test.class.getSimpleName() + " with id = " + id);
            return new ResponseEntity<>(jsonResponse, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Registers a new test to the server.
     * Returns the newly created user and an HTTP CREATED status code.
     * @param test
     * @return storedTest
     */
    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Add Test",
            notes = "Creates a new test. Receives a User object with the user info in the request body "
                    + "does not requires roles specification in the request body.",
            response = Test.class)
    public ResponseEntity<?> addTest(@RequestBody Test test) {
        logger.debug("adding a test");
        return new ResponseEntity<>(testService.save(test), HttpStatus.OK);
    }

    /**
     * Updates or creates the test information of the id provided
     * @param id
     * @param test
     * @return updatedTest
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Add or Update a test entity",
            notes = "Updates a test info or creates a new one. Receives the id on the path and test object with the updated data in the request body")
    public ResponseEntity<?> addOrUpdateUser(@PathVariable long id, @RequestBody Test test) {
        logger.info("Updating test info of test with id" + test.getId());
        test.setId(id);
        return new ResponseEntity<>(testService.save(test), HttpStatus.OK);
    }
    /**
     * Updates the test partial information of the id provided
     * @param id
     * @param test
     * @return updatedTest
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Update a test entity",
            notes = "Updates a test info. Receives the id on the path and test object with the updated data in the request body")
    public ResponseEntity<?> partialUpdateUser(@PathVariable long id, @RequestBody Test test) {
        logger.info("Updating test info of test with id" + test.getId());
        if (testService.findById(id) == null){
            return new ResponseEntity<>(
                    new JsonResponse(HttpStatus.NOT_FOUND, "There is not a " + Test.class.getSimpleName())
                            + " with id = " + id + " in the database.",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(testService.update(id, test), HttpStatus.OK);
    }

    /**
     * Deletes the user with the user name provided and an HTTP OK status code.
     * @param id
     * @return JsonResponse
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Delete User",
            notes = "Deletes a user. Receives the name of the user to delete")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        testService.deleteById(id);
        logger.info("Deleted user with id = " + id);
        if (testService.findById(id) == null) {
            return new ResponseEntity<>(
                    new JsonResponse(HttpStatus.NOT_FOUND, "There is not a " + Test.class.getSimpleName())
                            + " with id = " + id + " in the database.",
                    HttpStatus.NOT_FOUND
            );
        }
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage(Test.class.getSimpleName() + " with id = " + id + " was successfully deleted.");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
