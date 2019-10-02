package ch.duartemendes.cronus.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ch.duartemendes.cronus.domain.List;
import ch.duartemendes.cronus.domain.Role;
import ch.duartemendes.cronus.domain.User;
import ch.duartemendes.cronus.dto.UserDTO;

@Mapper
public interface UserMapper {
 
	UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

	UserDTO userToUserDto(User user);
	
	default Long roleToId(Role role) {
		return role.getId();
	}

	default Long listToId(List list) {
		return list.getId();
	}
}
