package domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logging.Logger;

public record Win(String gameId, String nfcId, String timestamp, int points) {

    public String toJson() throws JsonProcessingException {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.warn(ex, "Error while converting win to json");
            throw ex;
        }
    }

    public static Win fromJson(String json) throws JsonProcessingException {
        try {
            return new ObjectMapper().readValue(json, Win.class);
        } catch (JsonProcessingException ex) {
            Logger.warn(ex, "Error while converting json to win");
            throw ex;
        }
    }
}
