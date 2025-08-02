package dev.korol.Football.manager.task;

import dev.korol.Football.manager.task.entity.Player;
import dev.korol.Football.manager.task.entity.Team;
import dev.korol.Football.manager.task.repository.PlayerRepository;
import dev.korol.Football.manager.task.repository.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class FootballManagerTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballManagerTaskApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, TeamRepository teamRepository) {
		return args -> {
			Team teamA = new Team();
			teamA.setTeamName("Team A");
			teamA.setBalance(new BigDecimal("1000000"));
			teamA.setTransferCommissionPercent(10);
			teamRepository.save(teamA);

			Team teamB = new Team();
			teamB.setTeamName("Team B");
			teamB.setBalance(new BigDecimal("500000"));
			teamB.setTransferCommissionPercent(5);
			teamRepository.save(teamB);

			Player player1 = new Player();
			player1.setName("John");
			player1.setSurname("Doe");
			player1.setAge(25);
			player1.setExperience(5);
			player1.setTeam(teamA);
			playerRepository.save(player1);

			Player player2 = new Player();
			player2.setName("Jane");
			player2.setSurname("Smith");
			player2.setAge(30);
			player2.setExperience(3);
			player2.setTeam(teamB);
			playerRepository.save(player2);
		};
	}

}
