package ch.duartemendes.cronus.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ch.duartemendes.cronus.domain.Entry;
import ch.duartemendes.cronus.domain.List;
import ch.duartemendes.cronus.dto.EntryDTO;

@Mapper
public interface EntryMapper {
 
	EntryMapper INSTANCE = Mappers.getMapper( EntryMapper.class );

	EntryDTO entryToEntryDto(Entry entry);
	
	default Long listToId(List list) {
		return list.getId();
	}
}
