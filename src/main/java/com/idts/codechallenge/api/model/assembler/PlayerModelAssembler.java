package com.idts.codechallenge.api.model.assembler;

import com.idts.codechallenge.api.controller.PlayerController;
import com.idts.codechallenge.api.model.Player;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@NonNullApi
public class PlayerModelAssembler implements RepresentationModelAssembler<Player, EntityModel<Player>> {

    private static final String PLAYERS_REL = "players";

    @Override
    public EntityModel<Player> toModel(Player player) {
        return EntityModel.of(player,
                linkTo(methodOn(PlayerController.class).getById(player.getId())).withSelfRel(),
                linkTo(methodOn(PlayerController.class).getAll()).withRel(PLAYERS_REL));
    }

    public EntityModel<Player> toModelByLevel(Player player) {
        return EntityModel.of(player,
                linkTo(methodOn(PlayerController.class).getByLevel(player.getLevel())).withSelfRel(),
                linkTo(methodOn(PlayerController.class).getAll()).withRel(PLAYERS_REL));
    }

    public EntityModel<Player> toModelByGame(Player player) {
        return EntityModel.of(player,
                linkTo(methodOn(PlayerController.class).getByGame(player.getGame())).withSelfRel(),
                linkTo(methodOn(PlayerController.class).getAll()).withRel(PLAYERS_REL));
    }

    public EntityModel<Player> toModelByGeography(Player player) {
        return EntityModel.of(player,
                linkTo(methodOn(PlayerController.class).getByGeography(player.getGeography())).withSelfRel(),
                linkTo(methodOn(PlayerController.class).getAll()).withRel(PLAYERS_REL));
    }

    public EntityModel<Player> toModelByLevelAndGame(Player player) {
        return EntityModel.of(player,
                linkTo(methodOn(PlayerController.class)
                        .getByLevelAndGame(player.getLevel(), player.getGame())).withSelfRel(),
                linkTo(methodOn(PlayerController.class).getAll()).withRel(PLAYERS_REL));
    }
    
    public EntityModel<Player> toModelAssociateGame(Player player) {
        return EntityModel.of(player,
                linkTo(methodOn(PlayerController.class).associateGameToPlayer(player.getId(), player.getGame())).withSelfRel(),
                linkTo(methodOn(PlayerController.class).getAll()).withRel(PLAYERS_REL));
    }
}
