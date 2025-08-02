package dev.korol.Football.manager.task.controller;

import dev.korol.Football.manager.task.dto.PlayerDTO;
import dev.korol.Football.manager.task.dto.PlayerResponseDTO;
import dev.korol.Football.manager.task.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Korol Artur
 * 31.07.2025
 */


@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/")
    public ResponseEntity<PlayerResponseDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        PlayerResponseDTO createdPlayer = playerService.createPlayer(playerDTO);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayerResponseDTO>> getPlayers(){
        List<PlayerResponseDTO> playerResponseDTOs = playerService.getAllPlayers();
        return new ResponseEntity<>(playerResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable int playerId) {
        PlayerResponseDTO playerResponseDTO = playerService.getPlayerById(playerId);
        return new ResponseEntity<>(playerResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable int playerId, @Valid @RequestBody PlayerDTO playerDTO) {
        PlayerResponseDTO updatedPlayer = playerService.updatePlayer(playerDTO, playerId);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @PutMapping("/transfer/{playerId}")
    public ResponseEntity<PlayerResponseDTO> transferPlayer(@PathVariable int playerId, @RequestParam int toTeamId) {
        PlayerResponseDTO transferredPlayer = playerService.transferPlayer(playerId, toTeamId);
        return new ResponseEntity<>(transferredPlayer, HttpStatus.OK);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Integer> deletePlayer(@PathVariable int playerId) {
        playerService.deletePlayerById(playerId);
        return ResponseEntity.ok(playerId);
    }

}
