package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.persistence.IUserDAO;

@Service("userDetailsService")
public class UserBL implements UserDetailsService {

	@Autowired
	private IUserDAO userDAO;

	public UserBL() {
	}
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IUserBL#get(java.lang.String)
	 */
	public com.excilys.computerdatabase.model.User get(String login) {
		com.excilys.computerdatabase.model.User user = userDAO.get(login);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IUserBL#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		com.excilys.computerdatabase.model.User user = userDAO.get(username);
		if(user == null ) {
			return new User("toto", 
					"toto", true, 
                    true, true, true, getAuthorities(2));
		}
		return new User(user.getName(), 
				user.getPassword(), true, 
	                        true, true, true, getAuthorities(user.getRole().getId()));
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IUserBL#getAuthorities(java.lang.Integer)
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.IUserBL#getRoles(java.lang.Integer)
	 */
	public List<String> getRoles(Integer role) {
		List<String> roles = new ArrayList<String>();
		if (role.intValue() == 1) {
			roles.add("ROLE_ADMIN");
		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
		}
		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
