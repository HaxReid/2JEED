package com.supinfo.jee.casino.game;

import com.supinfo.jee.casino.gambler.*;
import com.supinfo.jee.casino.launches.LaunchController;
import com.supinfo.jee.casino.launches.LaunchInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GameController {

    private final GamblerManager gamblerManager;
    private final GamblerRepository gamblerRepository;

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<GameOutputDto> newGame(@RequestBody GameInputDto newGame) {
        /*
        Retrieve or create user associated to this new game. If gambler already exists, then provide values for balance and bet.
         */
        String pseudo = newGame.getPseudo();
        GameOutputDto result = new GameOutputDto(pseudo);
        Gambler gambler = this.gamblerManager.getGambler(pseudo);
        result.setBalance(gambler.getBalance());
        result.setBet(gambler.getBet());

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LaunchController.class).play(new LaunchInputDto())).withRel("launches");
        return EntityModel.of(result, link);
    }

    @PostMapping("/authenticates")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void authenticate(@RequestBody GameInputDto newGame) throws EmptyPasswordException, PseudoAlreadyExistsException {
        if (this.gamblerRepository.existsByPseudo(newGame.getPseudo())){
            this.gamblerManager.authenticateGambler(newGame.getPseudo(), newGame.getPassword());
        }else{
            this.gamblerManager.register(newGame.getPseudo(), newGame.getPassword());
        }
    }
}
