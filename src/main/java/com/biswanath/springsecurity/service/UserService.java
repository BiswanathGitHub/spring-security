package com.biswanath.springsecurity.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biswanath.springsecurity.entity.Role;
import com.biswanath.springsecurity.entity.User;
import com.biswanath.springsecurity.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	private UserRepository userRepository;

	@Autowired
	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean isPresent(String username, String password) {
		/*
		 * int count = userRepository.isPresent(username, password); if (count > 0) {
		 * return true; }
		 */
		return false;
	}

	@Transactional
	public void createAndSaveUsers() {

		/*
		 * BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		 * 
		 * List<Role> roles=new ArrayList<>(); Role role= new Role();
		 * 
		 * role.setName(RoleEnum.ADMIN.name());
		 * 
		 * 
		 * User u1=new User(); u1.setUsername("Biswa");
		 * u1.setPassword(encoder.encode("Biswa"));
		 * role.setUsers(Collections.singletonList(u1)); roles.add(role);
		 * u1.setRoles(roles);
		 * 
		 * userRepository.save(u1);
		 */

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=null;
		try {
			user = userRepository.findByUsername(username);
			System.out.print(user.getPassword());
		}catch(Exception e) {
			e.printStackTrace();
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

		return buildUserForAuthentication(user, authorities);

	}

	private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {

		List<GrantedAuthority> authorites = new ArrayList<GrantedAuthority>();
		// Build user's authorities
		for (Role userRole : userRoles) {
			authorites.add(new SimpleGrantedAuthority(userRole.getName()));
		}

		return authorites;
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		 return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
	}

}
