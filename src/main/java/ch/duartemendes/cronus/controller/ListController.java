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

import ch.duartemendes.cronus.domain.List;
import ch.duartemendes.cronus.service.ListService;

@RestController
@RequestMapping("/lists")
public class ListController {
    private ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public java.util.List<List> getAllLists() {
        return listService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List createList(@Valid @RequestBody List list) {
        return listService.createList(list);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(@PathVariable("id") Long id) {
        listService.deleteList(id);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List putList(@PathVariable("id") Long id, @Valid @RequestBody List list) {
        return listService.updateList(id, list);
    }
}
