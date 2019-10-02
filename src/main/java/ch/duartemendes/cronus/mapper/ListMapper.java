package ch.duartemendes.cronus.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ch.duartemendes.cronus.domain.Entry;
import ch.duartemendes.cronus.domain.List;
import ch.duartemendes.cronus.domain.User;
import ch.duartemendes.cronus.dto.ListDTO;

@Mapper
public interface ListMapper {
 
	ListMapper INSTANCE = Mappers.getMapper( ListMapper.class );

	ListDTO listToListDto(List list);
	
	default Long userToId(User user) {
		return user.getId();
	}

	default Long entryToId(Entry entry) {
		return entry.getId();
	}
}
