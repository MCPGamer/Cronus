package ch.duartemendes.cronus.controller;

import java.util.List;

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
import ch.duartemendes.cronus.service.EntryService;

@RestController
@RequestMapping("/entries")
public class EntryController {
    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable("id") Long id) {
        entryService.deleteEntry(id);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entry putEntry(@PathVariable("id") Long id, @Valid @RequestBody Entry entry) {
        return entryService.updateEntry(id, entry);
    }
}
