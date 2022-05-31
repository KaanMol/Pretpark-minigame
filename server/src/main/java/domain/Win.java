package domain;

import java.time.LocalDateTime;

public record Win(String gameId, String cardId, LocalDateTime timestamp, int points) {
}
