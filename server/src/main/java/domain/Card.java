package domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logging.Logger;

public record Card(String cardId, String accountId) {

    public String toJson() throws JsonProcessingException {
        try {
           return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.warn(ex, "Error while converting card to json");
            throw ex;
        }
    }

    public static Card fromJson(String json) throws JsonProcessingException {
        try {
            return new ObjectMapper().readValue(json, Card.class);
        } catch (JsonProcessingException ex) {
            Logger.warn(ex, "Error while converting json to card");
            throw ex;
        }
    }
}
