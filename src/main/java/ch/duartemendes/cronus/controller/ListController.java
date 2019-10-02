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
import ch.duartemendes.cronus.dto.ListDTO;
import ch.duartemendes.cronus.mapper.ListMapper;
import ch.duartemendes.cronus.service.ListService;

@RestController
@RequestMapping("/lists")
public class ListController {
    private ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    /***
     * Zeigt alle Listen an. Ab Nächster Version nur für Admin alle und für Jede Person eigene.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListDTO[] getAllLists() {
        return listService.findAll()
				.stream()
				.map(ListMapper.INSTANCE::listToListDto)
				.toArray(ListDTO[]::new);
    }

    /***
     * Erstellt eine neue Liste.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ListDTO createList(@Valid @RequestBody List list) {
        return ListMapper.INSTANCE.listToListDto(listService.createList(list));
    }
    
    /***
     * Löscht eine Liste. 
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(@PathVariable("id") Long id) {
        listService.deleteList(id);
    }
    
    /***
     * Dient dazu die Liste umzubenennen.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ListDTO putList(@PathVariable("id") Long id, @Valid @RequestBody List list) {
        return ListMapper.INSTANCE.listToListDto(listService.updateList(id, list));
    }
}
