package com.adfer.security.model;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
	ADMIN(
			Set.of(
					UserPermission.ADMIN_CREATE,
					UserPermission.ADMIN_DELETE,
					UserPermission.ADMIN_UPDATE,
					UserPermission.ADMIN_READ
			)
	),
	USER(Collections.emptySet());
	
	private final Set<UserPermission> permissions;

	private UserRole(Set<UserPermission> permissions) {
		this.permissions = permissions;
	}

	public List<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = permissions.stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermissionString()))
				.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return authorities;
	}
	
}
