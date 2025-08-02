package dev.korol.Football.manager.task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Korol Artur
 * 31.07.2025
 */

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId;

    private String teamName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "team")
    private List<Player> players = new ArrayList<>();

    private BigDecimal balance;

    private int transferCommissionPercent;
}
