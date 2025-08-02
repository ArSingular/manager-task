package dev.korol.Football.manager.task.service.implementation;

import dev.korol.Football.manager.task.dto.PlayerDTO;
import dev.korol.Football.manager.task.dto.PlayerResponseDTO;
import dev.korol.Football.manager.task.entity.Player;
import dev.korol.Football.manager.task.entity.Team;
import dev.korol.Football.manager.task.exception.EntityNotFoundException;
import dev.korol.Football.manager.task.exception.ServiceException;
import dev.korol.Football.manager.task.repository.PlayerRepository;
import dev.korol.Football.manager.task.repository.TeamRepository;
import dev.korol.Football.manager.task.service.PlayerService;
import dev.korol.Football.manager.task.util.PlayerMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Korol Artur
 * 31.07.2025
 */

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;


    @Override
    @Transactional
    public PlayerResponseDTO createPlayer(PlayerDTO playerDTO) {
        int teamId = playerDTO.getPlayerTeamId();
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team with id " + teamId + " was not found"));

        if(playerDTO.getAge() <= playerDTO.getExperience()){
            throw new ServiceException("Player's age must be greater than experience");
        }
        Player player = playerMapper.toPlayer(playerDTO);
        player.setTeam(team);

        Player savedPlayer = playerRepository.save(player);

        return playerMapper.toPlayerResponseDTO(savedPlayer);
    }

    @Override
    public PlayerResponseDTO getPlayerById(int playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player with id " + playerId + " not found"));
        return playerMapper.toPlayerResponseDTO(player);
    }

    @Override
    public PlayerResponseDTO updatePlayer(PlayerDTO playerDTO, int playerId) {
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player with id " + playerId + " not found"));
        playerMapper.updatePlayerFromDTO(playerDTO, existingPlayer);
        Player updatedPlayer = playerRepository.save(existingPlayer);
        return playerMapper.toPlayerResponseDTO(updatedPlayer);
    }

    @Override
    public void deletePlayerById(int playerId) {
        playerRepository.deleteById(playerId);
    }

    @Override
    public List<PlayerResponseDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(playerMapper::toPlayerResponseDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PlayerResponseDTO transferPlayer(int playerId, int toTeamId) {
        Player currentPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player with id " + playerId + " not found"));
        Team currentTeam = currentPlayer.getTeam();
        if (currentTeam.getTeamId() == toTeamId) {
            throw new ServiceException("Cannot transfer player to the same team");
        }
        Team toTeam = teamRepository.findById(toTeamId)
                .orElseThrow(() -> new EntityNotFoundException("Team with id " + toTeamId + " not found"));

        BigDecimal transferPrice = BigDecimal.valueOf(currentPlayer.getExperience() * 100000L)
                .divide(BigDecimal.valueOf(currentPlayer.getAge()), 2, RoundingMode.HALF_UP);
        BigDecimal commission = transferPrice.multiply(BigDecimal.valueOf(currentTeam.getTransferCommissionPercent()))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal totalPrice = transferPrice.add(commission);

        if (toTeam.getBalance().compareTo(totalPrice) < 0) {
            throw new ServiceException("Insufficient funds");
        }
        currentTeam.setBalance(currentTeam.getBalance().add(transferPrice));
        toTeam.setBalance(toTeam.getBalance().subtract(totalPrice));

        currentPlayer.setTeam(toTeam);
        playerRepository.save(currentPlayer);

        return playerMapper.toPlayerResponseDTO(currentPlayer);
    }
}
