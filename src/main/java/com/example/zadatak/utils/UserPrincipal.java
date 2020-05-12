package com.example.zadatak.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.zadatak.models.Korisnik;


public class UserPrincipal implements OAuth2User, UserDetails {
   
	private String id;
    private String email;
    private String lozinka;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    
    public UserPrincipal(String id, String email, String lozinka, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.lozinka = lozinka;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Korisnik korisnik) {
    	
    		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    			authorities.add(new SimpleGrantedAuthority(korisnik.getPermission().getAuthority()));
    		return new UserPrincipal( korisnik.getId(), korisnik.getEmail(), korisnik.getLozinka(), authorities);
    	
        
    }

    public static UserPrincipal create(Korisnik korisnik, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(korisnik);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }


    public String getId() {
        return id;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
    public String getPassword() {
        return lozinka;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}
