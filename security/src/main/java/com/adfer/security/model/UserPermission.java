package com.adfer.security.model;

public enum UserPermission {
	
	ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");
   
	private final String permissionString;

	private UserPermission(String permissionString) {
		this.permissionString = permissionString;
	}

	public String getPermissionString() {
		return permissionString;
	}
	
	
}
