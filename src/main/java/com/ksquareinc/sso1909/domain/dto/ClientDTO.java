package com.ksquareinc.sso1909.domain.dto;

import com.ksquareinc.sso1909.domain.Client;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;


public class ClientDTO implements Serializable {

	private String id;

	private String resourceId;

	private String scope;

	private String grantTypes;

	private String redirectUri;

	private String authorities;

	private int accessTokenValidity;

	private int refreshTokenValidity;

	private String additionalInfo;

	private String autoapprove;


    public ClientDTO() {

	}

	public ClientDTO(Client client){
    	id = client.getId();
    	resourceId = client.getResourceId();
    	scope = client.getScope();
    	grantTypes = client.getGrantTypes();
    	redirectUri = client.getRedirectUri();
    	authorities = client.getAuthorities();
    	accessTokenValidity = client.getAccessTokenValidity();
    	refreshTokenValidity = client.getRefreshTokenValidity();
    	additionalInfo = client.getAdditionalInfo();
    	autoapprove = client.getAutoapprove();
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public int getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(int accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public int getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(int refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}


}
