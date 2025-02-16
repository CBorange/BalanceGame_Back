package com.saks.balance.backend.entity;
import com.saks.balance.states.GlobalStates;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class GameTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalStates.GameState state;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal bet;

    @Column(name = "bet_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalStates.GameBetType betType;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    private LocalDateTime deleteDate;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;
}
