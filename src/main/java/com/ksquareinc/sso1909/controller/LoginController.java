package com.ksquareinc.sso1909.controller;

import com.ksquareinc.sso1909.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

  @Autowired
  ApprovalStore approvalStore;

  @RequestMapping("/login")
  public ModelAndView loginPage() {
    return new ModelAndView("login");
  }

  @RequestMapping("/")
  public ModelAndView home() {
    return new ModelAndView("home");
  }

  @RequestMapping(value="/logout", method = RequestMethod.GET)
  public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null){
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return new ModelAndView("redirect:/");
  }

  @RequestMapping("/dashboard")
  public ModelAndView dashboard(Map<String,Object> model, Principal principal){
    List<Client> clients = new ArrayList<>();
    clients.add(new Client("client_api", null, "secret", "read, write", "authorization_code","http://localhost:8080/dashboard", "user",12000, 12000, null, "FALSE"));
    model.put("approvals",approvalStore.getApprovals("admin", "client_api"));
    model.put("clientDetails",clients);
    return new ModelAndView("index.html",model);

  }
}
