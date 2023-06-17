package com.supinfo.jee.casino.gambler;

import com.supinfo.jee.casino.party.Party;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Gambler {

    @Id
    @GeneratedValue
    private Long id;
    private String pseudo;
    @NotEmpty
    private String password;
    private long balance;
    private int bet;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gambler")
    private List<Party> partyList = new ArrayList<>();

    public Gambler(String pseudoParam, String passwordParam) {
        this.pseudo = pseudoParam;
        this.password = passwordParam;
    }
    public Gambler(String pseudoParam, String passwordParam, long balanceParam, int betParam) {
        this.pseudo = pseudoParam;
        this.password = passwordParam;
        this.balance = balanceParam;
        this.bet = betParam;
    }
}
