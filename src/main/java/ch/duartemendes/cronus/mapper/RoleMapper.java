package ch.duartemendes.cronus.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ch.duartemendes.cronus.domain.Role;
import ch.duartemendes.cronus.domain.User;
import ch.duartemendes.cronus.dto.RoleDTO;

@Mapper
public interface RoleMapper {
 
	RoleMapper INSTANCE = Mappers.getMapper( RoleMapper.class );

	RoleDTO roleToRoleDto(Role user);
	
	default Long userToId(User user) {
		return user.getId();
	}
}
