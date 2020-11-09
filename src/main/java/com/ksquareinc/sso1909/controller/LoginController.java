package com.ksquareinc.sso1909.controller;

import com.ksquareinc.sso1909.domain.Client;
import com.ksquareinc.sso1909.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.Arrays.asList;

@RestController
public class LoginController {

  private final ApprovalStore approvalStore;
  private final ClientService clientService;
  private final TokenStore tokenStore;

  public LoginController(ApprovalStore approvalStore, ClientService clientService, TokenStore tokenStore) {
    this.approvalStore = approvalStore;
    this.clientService = clientService;
    this.tokenStore = tokenStore;
  }

  @ApiIgnore
  @RequestMapping(value = "/login",method = RequestMethod.GET)
  public ModelAndView loginPage() {
    return new ModelAndView("login");
  }

  @ApiIgnore
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView home() {
    return new ModelAndView("home");
  }

  @ApiIgnore
  @RequestMapping(value="/logout", method = RequestMethod.GET)
  public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null){
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return new ModelAndView("redirect:/");
  }

  @ApiIgnore
  @RequestMapping(value = "/dashboard")
  public ModelAndView dashboard(HttpServletRequest request, Map<String,Object> model,Principal principal){
    try{
      List<Approval> approvals=clientService.findAll().stream()
              .map(clientDetails -> approvalStore.getApprovals(principal.getName(),clientDetails.getId()))
              .flatMap(Collection::stream)
              .collect(Collectors.toList());
      model.put("approvals",approvals);
      model.put("clientDetails",clientService.findAll());
    }catch (NullPointerException npe) {
      System.err.println("Illegal Access to dashboard");
    }
    return new ModelAndView("index.html",model);
  }

  @RequestMapping(value="/approval/revoke",method= RequestMethod.POST)
  public ModelAndView revokeApproval(@ModelAttribute Approval approval){

    approvalStore.revokeApprovals(asList(approval));
    tokenStore.findTokensByClientIdAndUserName(approval.getClientId(),approval.getUserId())
            .forEach(tokenStore::removeAccessToken) ;
    return new ModelAndView("redirect:/");
  }

}
