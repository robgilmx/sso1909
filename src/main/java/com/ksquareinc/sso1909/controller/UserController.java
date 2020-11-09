package com.ksquareinc.sso1909.controller;

import com.ksquareinc.sso1909.domain.User;
import com.ksquareinc.sso1909.domain.dto.UserDTO;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * Returns all registered users and an HTTP OK status code
     * @return
     */
    @RequestMapping( method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "getUsers (admin only)",
            notes = "Gets the info of all the users. Returns a list with all the users info",
            response = UserDTO.class,
            responseContainer = "List")
    public ResponseEntity<?> getUsers() {
        List<UserDTO> usersDTO = new ArrayList<>();
        userService.getUsers().forEach(user -> usersDTO.add(new UserDTO(user)));
        logger.info("Listing all users");
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    /**
     * Returns the complete user information of the user name provided along with an HTTP OK status code.
     * @param username
     * @return
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "getUser (admin only)",
            notes = "Gets the info of a user. Receives the user name in the path and returns the correspondent info",
            response = UserDTO.class)
    public ResponseEntity<?> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        logger.info("Returning user info of " + username);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    /**
     * Registers a new user to the server.
     * Returns the newly created user and an HTTP CREATED status code.
     * @param user
     * @return
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "addUser (admin only)",
            notes = "Creates a new user. Receives a User object with the user info in the request body "
                    + "does not requires roles specification in the request body.",
            response = UserDTO.class)
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return addUser(false, user);
    }

  @RequestMapping(value = "/createAdmin", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "addUser (admin only)",
            notes = "Creates a new Admin. Receives a User object with the user info in the request body "
                    + "does not requires roles specification in the request body.",
            response = UserDTO.class)
    public ResponseEntity<?> addAdmin(@RequestBody User admin) {
        return addUser(true, admin);
    }



    public ResponseEntity<?> addUser(boolean admin, User user) {
        logger.info("Adding user " + user.getUsername());
        user.setRoles(admin?
                Arrays.asList(RoleEnum.ROLE_ADMIN, RoleEnum.ROLE_USER)
                : Collections.singletonList(RoleEnum.ROLE_USER));
        User userCreated = userService.addUser(user);
        return new ResponseEntity<>(new UserDTO(userCreated), HttpStatus.CREATED);
    }


    /**
     * Updates the user information of the user name provided
     * @param username
     * @param user
     * @return
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "updateUser  (admin only)",
            notes = "Updates a user info. Receives the username in the path and a User object with the updated user info in the request body")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
        logger.info("Updated user info of " + username);
        User updateUser = userService.updateUser(username, user);
        return new ResponseEntity<>(new UserDTO(updateUser), HttpStatus.OK);
    }

    /**
     * Deletes the user with the user name provided and an HTTP OK status code.
     * @param username
     * @return
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "deleteUser  (admin only)",
            notes = "Deletes a user. Receives the name of the user to delete")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        logger.info("Deleted user " + username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/me", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAnyAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "me",
            notes = "Deletes a user. Receives the name of the user to delete")
    public ResponseEntity<?> getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = new UserDTO();
        List<RoleEnum> roleEnums = new ArrayList<>();
        auth.getAuthorities().stream().forEach(grantedAuthority -> {
            try {
                roleEnums.add( RoleEnum.valueOf(grantedAuthority.getAuthority()));
            }catch (IllegalArgumentException ignored){
            }
        });
        userDTO.setRoles(roleEnums);
        userDTO.setUsername(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
