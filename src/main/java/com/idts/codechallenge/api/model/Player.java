package com.idts.codechallenge.api.model;

import com.idts.codechallenge.api.constants.Game;
import com.idts.codechallenge.api.constants.Level;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

import static com.idts.codechallenge.api.constants.Level.NOOB;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 15, message = "Name should be between 2-20 characters")
    private String name;

    @NotNull(message = "geography is mandatory")
    @NotBlank(message = "geography is mandatory")
    @Size(min = 3, max = 15, message = "geography should be between 3-15 characters")
    private String geography;

    private Level level;

    private Game game;

    public Player(String name, String geography, Level level, Game game) {
        this.name = name;
        this.geography = geography;
        this.level = level;
        this.game = game;
    }

    public Player(String name, String geography, Game game) {
        this.name = name;
        this.geography = geography;
        this.game = game;
        this.level = NOOB;
    }

    public Player(String name, String geography) {
        this.name = name;
        this.geography = geography;
        this.level = NOOB;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Player player))
            return false;
        return Objects.equals(this.id, player.id) && Objects.equals(this.name, player.name)
                && Objects.equals(this.geography, player.geography) && Objects.equals(this.level, player.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.geography, this.level);
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", geography='" + this.geography + '\'' +
                ", level='" + this.level + '\'' +
                ", game='" + this.game + '\''+ '}';
    }
}
