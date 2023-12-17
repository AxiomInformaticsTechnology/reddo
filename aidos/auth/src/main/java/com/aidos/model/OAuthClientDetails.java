package com.aidos.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.google.common.base.Strings;

@Entity
@Table(name = "oauth_client_details")
public class OAuthClientDetails implements ClientDetails {

	private static final long serialVersionUID = -7312525931727375242L;

	@Id
	private String clientId;

	private String resourceIds;

	private String clientSecret;

	private String scope;

	private String authorizedGrantTypes;

	private String webServerRedirectUri;

	private String authorities;

	private Integer accessTokenValidity;

	private Integer refreshTokenValidity;

	private String additionalInformation;

	private String autoapprove;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

	@Override
	public Set<String> getResourceIds() {
		return new HashSet<String>(Arrays.asList(resourceIds.split(",")));
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public boolean isScoped() {
		return !Strings.isNullOrEmpty(scope);
	}

	@Override
	public Set<String> getScope() {
		return new HashSet<String>(Arrays.asList(scope.split(",")));
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return new HashSet<String>(Arrays.asList(authorizedGrantTypes.split(",")));
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return new HashSet<String>(Arrays.asList(webServerRedirectUri.split(",")));
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : this.authorities.split(",")) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValidity;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValidity;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		// TODO: determine how to use scope
		return Boolean.valueOf(autoapprove);
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		Map<String, Object> additionalInformation = new HashMap<String, Object>();
		int i = 0;
		for (String info : this.additionalInformation.split(",")) {
			additionalInformation.put(Integer.toString(i++), info);
		}
		return additionalInformation;
	}

}