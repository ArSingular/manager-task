package dev.korol.Football.manager.task;

import dev.korol.Football.manager.task.dto.PlayerResponseDTO;
import dev.korol.Football.manager.task.entity.Player;
import dev.korol.Football.manager.task.entity.Team;
import dev.korol.Football.manager.task.exception.EntityNotFoundException;
import dev.korol.Football.manager.task.exception.ServiceException;
import dev.korol.Football.manager.task.repository.PlayerRepository;
import dev.korol.Football.manager.task.repository.TeamRepository;
import dev.korol.Football.manager.task.service.implementation.PlayerServiceImpl;
import dev.korol.Football.manager.task.util.PlayerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Korol Artur
 * 01.08.2025
 */

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;



    @Test
    void transferPlayer_successful() {
        Team currentTeam = new Team();
        currentTeam.setTeamId(1);
        currentTeam.setBalance(new BigDecimal("1000000"));
        currentTeam.setTransferCommissionPercent(10);

        Team toTeam = new Team();
        toTeam.setTeamId(2);
        toTeam.setBalance(new BigDecimal("1000000"));

        Player player = new Player();
        player.setPlayerId(1);
        player.setAge(25);
        player.setExperience(5);
        player.setTeam(currentTeam);

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(teamRepository.findById(2)).thenReturn(Optional.of(toTeam));
        when(playerMapper.toPlayerResponseDTO(any(Player.class))).thenReturn(new PlayerResponseDTO());

        PlayerResponseDTO result = playerService.transferPlayer(1, 2);
        assertNotNull(result);
        verify(playerRepository).save(player);
        assertEquals(toTeam, player.getTeam());

        assertEquals(new BigDecimal("1020000.00"), currentTeam.getBalance());
        assertEquals(new BigDecimal("978000.00"), toTeam.getBalance());
    }

    @Test
    void transferPlayer_playerNotFoundException() {
        when(playerRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> playerService.transferPlayer(1, 2)
        );

        assertEquals("Player with id 1 not found", exception.getMessage());
    }

    @Test
    void transferPlayer_teamNotFoundException() {
        Team currentTeam = new Team();
        currentTeam.setTeamId(1);

        Player player = new Player();
        player.setPlayerId(1);
        player.setTeam(currentTeam);

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(teamRepository.findById(2)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> playerService.transferPlayer(1, 2)
        );

        assertEquals("Team with id 2 not found", exception.getMessage());
    }

    @Test
    void transferPlayer_sameTeam_throwsException() {
        Team team = new Team();
        team.setTeamId(1);

        Player player = new Player();
        player.setPlayerId(1);
        player.setTeam(team);

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> playerService.transferPlayer(1, 1)
        );

        assertEquals("Cannot transfer player to the same team", exception.getMessage());
    }

    @Test
    void transferPlayer_insufficientFunds_throwsException() {
        Team currentTeam = new Team();
        currentTeam.setTeamId(1);
        currentTeam.setBalance(new BigDecimal("0"));
        currentTeam.setTransferCommissionPercent(10);

        Team toTeam = new Team();
        toTeam.setTeamId(2);
        toTeam.setBalance(new BigDecimal("1000"));

        Player player = new Player();
        player.setPlayerId(1);
        player.setAge(25);
        player.setExperience(5);
        player.setTeam(currentTeam);

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(teamRepository.findById(2)).thenReturn(Optional.of(toTeam));

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> playerService.transferPlayer(1, 2)
        );

        assertEquals("Insufficient funds", exception.getMessage());
    }
}
