package ch.duartemendes.cronus.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.duartemendes.cronus.domain.Entry;
import ch.duartemendes.cronus.dto.EntryDTO;
import ch.duartemendes.cronus.mapper.EntryMapper;
import ch.duartemendes.cronus.service.EntryService;

@RestController
@RequestMapping("/entries")
public class EntryController {
    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    /***
     * Holt alle Einträge. Ab nächster Version auf Liste Limitiert.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EntryDTO[] getAllEntries() {
        return entryService.findAll()
				.stream()
				.map(EntryMapper.INSTANCE::entryToEntryDto)
				.toArray(EntryDTO[]::new);
    }


    /***
     * Erstellen eines Neuen Eintrags
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDTO createEntry(@Valid @RequestBody Entry entry) {
        return EntryMapper.INSTANCE.entryToEntryDto(entryService.createEntry(entry));
    }
    

    /***
     * Löschen eines Eintrags
     */    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable("id") Long id) {
        entryService.deleteEntry(id);
    }
    

    /***
     * Verändern eines Eintrags.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntryDTO putEntry(@PathVariable("id") Long id, @Valid @RequestBody Entry entry) {
        return EntryMapper.INSTANCE.entryToEntryDto(entryService.updateEntry(id, entry));
    }
}
