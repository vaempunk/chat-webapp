package dev.vaem.websockets.web.util.mapper;

import dev.vaem.websockets.domain.entity.Role;

public interface RoleMapper {
    static String roleNameToAuthority(Role.Name roleName) {
        return "ROLE_" + roleName.name();
    }

    static Role.Name roleNameFromAuthority(String authority) {
        if (authority.startsWith("ROLE_")) {
            return Role.Name.valueOf(authority.substring(5));
        }
        return null;
    }
}
