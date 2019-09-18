package com.ksquareinc.sso1909.controller;

import com.ksquareinc.sso1909.domain.User;
import com.ksquareinc.sso1909.domain.enums.RoleEnum;
import com.ksquareinc.sso1909.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController ("api/users")
public class UserController {

//    private static Logger logger = LoggerFactory.getLogger(UserController.class);
//
//    @Autowired
//    private UserService userService;
//
//    /**
//     * Returns all registered users and an HTTP OK status code
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.GET,
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @ApiOperation(value = "getUsers",
//            notes = "Gets the info of all the users. Returns a list with all the users info",
//            response = User.class,
//            responseContainer = "List")
//    public ResponseEntity<?> getUsers() {
//        List<User> usersDTO = userService.getUsers();
//        logger.info("Listing all users");
//        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
//    }
//
//    /**
//     * Returns the complete user information of the user name provided along with an HTTP OK status code.
//     * @param username
//     * @return
//     */
//    @RequestMapping(value = "/{username}", method = RequestMethod.GET,
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @ApiOperation(value = "getUser",
//            notes = "Gets the info of a user. Receives the user name in the path and returns the correspondent info",
//            response = User.class)
//    public ResponseEntity<?> getUser(@PathVariable String username) {
//        User user = userService.getUser(username);
//        logger.info("Returning user info of " + username);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    /**
//     * Registers a new user to the server.
//     * Returns the newly created user and an HTTP CREATED status code.
//     * @param user
//     * @return
//     */
//    @RequestMapping(value = "/{admin}", method = RequestMethod.POST,
//            produces = {MediaType.APPLICATION_JSON_VALUE},
//            consumes = {MediaType.APPLICATION_JSON_VALUE})
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @ApiOperation(value = "addUser",
//            notes = "Creates a new user. Receives a User object with the user info in the request body "
//                    + "and a boolean path variable wich determines if the rol of the user is going to be user or admin.",
//            response = User.class)
//    public ResponseEntity<?> addUser(@PathVariable boolean admin, @RequestBody User user) {
//        logger.info("Adding user " + user.getUsername());
//        user.setRoles(admin?
//                Arrays.asList(RoleEnum.ROLE_ADMIN, RoleEnum.ROLE_USER)
//                : Collections.singletonList(RoleEnum.ROLE_USER));
//        User userCreated = userService.addUser(user);
//        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
//    }
//
//
//    /**
//     * Updates the user information of the user name provided
//     * @param username
//     * @param user
//     * @return
//     */
//    @RequestMapping(value = "/{username}", method = RequestMethod.PUT,
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @ApiOperation(value = "updateUser",
//            notes = "Updates a user info. Receives the username in the path and a User object with the updated user info in the request body")
//    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
//        logger.info("Updated user info of " + username);
//        User updateUser = userService.updateUser(username, user);
//        return new ResponseEntity<>(updateUser, HttpStatus.OK);
//    }
//
//    /**
//     * Deletes the user with the user name provided and an HTTP OK status code.
//     * @param username
//     * @return
//     */
//    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE,
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @ApiOperation(value = "deleteUser",
//            notes = "Deletes a user. Receives the name of the user to delete")
//    public ResponseEntity<?> deleteUser(@PathVariable String username) {
//        userService.deleteUser(username);
//        logger.info("Deleted user " + username);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
