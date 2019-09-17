package com.ksquareinc.sso1909.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity(name = "oauth_client_details")
public class Client implements Serializable {

	@Id
	@Column(name="client_id", unique = true, nullable = false)
	private String id;
	
	@Column(name="resource_ids")
	private String resourceId;
	
	@Column(name="client_secret", nullable = false)
	private String secret;
	
	@Column(name="scope")
	private String scope;
	
	@Column(name="authorized_grant_types")
	private String grantTypes;
	
	@Column(name="web_server_redirect_uri")
	private String redirectUri;
	
	@Column(name="authorities")
	private String authorities;
	
	@Column(name="access_token_validity")
	private int accessTokenValidity;
	
	@Column(name="refresh_token_validity")
	private int refreshTokenValidity;
	
	@Column(name="additional_information")
	private String additionalInfo;
	
	@Column(name="autoapprove")
	private String autoapprove;

	@Transient
    private static final long serialVersionUID = 1L;

    public Client() {
		
	}
	
	public Client(String id, String resourceId, String secret, String scope, String grantTypes,
				  String redirectUri, String authorities, int accessTokenValidity, int refreshTokenValidity,
				  String additionalInfo, String autoapprove) {
		this.id = id;
		this.resourceId = resourceId;
		this.secret = secret;
		this.scope = scope;
		this.grantTypes = grantTypes;
		this.redirectUri = redirectUri;
		this.authorities = authorities;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.additionalInfo = additionalInfo;
		this.autoapprove = autoapprove;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return accessTokenValidity == client.accessTokenValidity &&
				refreshTokenValidity == client.refreshTokenValidity &&
				id.equals(client.id) &&
				Objects.equals(resourceId, client.resourceId) &&
				secret.equals(client.secret) &&
				Objects.equals(scope, client.scope) &&
				Objects.equals(grantTypes, client.grantTypes) &&
				Objects.equals(redirectUri, client.redirectUri) &&
				Objects.equals(authorities, client.authorities) &&
				Objects.equals(additionalInfo, client.additionalInfo) &&
				Objects.equals(autoapprove, client.autoapprove);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, resourceId, secret, scope, grantTypes, redirectUri, authorities, accessTokenValidity, refreshTokenValidity, additionalInfo, autoapprove);
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

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
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

	@Override
	public String toString() {
		return "Client{" +
				"id='" + id + '\'' +
				", resourceId='" + resourceId + '\'' +
				", secret='" + secret + '\'' +
				", scope='" + scope + '\'' +
				", grantTypes='" + grantTypes + '\'' +
				", redirectUri='" + redirectUri + '\'' +
				", authorities='" + authorities + '\'' +
				", accessTokenValidity=" + accessTokenValidity +
				", refreshTokenValidity=" + refreshTokenValidity +
				", additionalInfo='" + additionalInfo + '\'' +
				", autoapprove='" + autoapprove + '\'' +
				'}';
	}


}
