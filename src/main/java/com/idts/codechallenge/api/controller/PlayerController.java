package com.idts.codechallenge.api.controller;

import com.idts.codechallenge.api.constants.Game;
import com.idts.codechallenge.api.constants.Level;
import com.idts.codechallenge.api.customExceptions.PlayerNotFoundException;
import com.idts.codechallenge.api.dao.PlayerRepository;
import com.idts.codechallenge.api.model.Player;
import com.idts.codechallenge.api.model.assembler.PlayerModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerRepository playerRepository;
    private final PlayerModelAssembler playerModelAssembler;

    public PlayerController(PlayerRepository playerRepository, PlayerModelAssembler playerModelAssembler) {
        this.playerRepository = playerRepository;
        this.playerModelAssembler = playerModelAssembler;
    }

    @PostMapping("/saveNewPlayer")
    public ResponseEntity<?> saveNewPlayer(@RequestBody Player player) {

        EntityModel<Player> playerEntityModel = playerModelAssembler.toModel(playerRepository.save(player));

        return ResponseEntity.created(playerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(playerEntityModel);
    }

    @GetMapping("/getAll")
    public CollectionModel<EntityModel<Player>> getAll() {
        List<EntityModel<Player>> players = playerRepository.findAll().stream()
                .map(playerModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).getAll()).withSelfRel());
    }

    @GetMapping("/getById/{id}")
    public EntityModel<Player> getById(@PathVariable Long id) {
         Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        return playerModelAssembler.toModel(player);
    }

    @GetMapping("/getByLevel/{level}")
    public CollectionModel<EntityModel<Player>> getByLevel(@PathVariable Level level) {
        List<EntityModel<Player>> players = playerRepository.findByLevel(level).stream()
                .map(playerModelAssembler::toModelByLevel)
                .collect(Collectors.toList());

        return CollectionModel.of(players);
    }

    @GetMapping("/getByGame/{game}")
    public CollectionModel<EntityModel<Player>> getByGame(@PathVariable Game game) {
        List<EntityModel<Player>> players = playerRepository.findByGame(game).stream()
                .map(playerModelAssembler::toModelByGame)
                .collect(Collectors.toList());

        return CollectionModel.of(players);
    }

    @GetMapping("/getByGeography/{geography}")
    public CollectionModel<EntityModel<Player>> getByGeography(@PathVariable String geography) {
        List<EntityModel<Player>> players = playerRepository.findByGeography(geography).stream()
                .map(playerModelAssembler::toModelByGeography)
                .collect(Collectors.toList());

        return CollectionModel.of(players);
    }

    @GetMapping("/getByLevelAndGame/{level}/{game}")
    public CollectionModel<EntityModel<Player>> getByLevelAndGame(@PathVariable Level level, @PathVariable Game game) {
        List<EntityModel<Player>> players = playerRepository.findByLevelAndGame(level, game).stream()
                .map(playerModelAssembler::toModelByLevelAndGame)
                .collect(Collectors.toList());

        return CollectionModel.of(players);
    }

    @PutMapping("/associateGameToPlayer/{id}/{game}")
    public ResponseEntity<?> associateGameToPlayer(@PathVariable Long id, @PathVariable Game game) {
        Player playerToUpdate = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
        playerToUpdate.setGame(game);

        EntityModel<Player> playerEntityModel = playerModelAssembler.toModelAssociateGame(playerRepository.save(playerToUpdate));

        return ResponseEntity.created(playerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(playerEntityModel);
    }

}
