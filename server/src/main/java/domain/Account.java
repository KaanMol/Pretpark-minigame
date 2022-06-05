package domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logging.Logger;

public record Account(String accountId) {

    public String toJson() throws JsonProcessingException {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.warn(ex, "Error while converting account to json");
            throw ex;
        }
    }

    public static Account fromJson(String json) throws JsonProcessingException {
        try {
            return new ObjectMapper().readValue(json, Account.class);
        } catch (JsonProcessingException ex) {
            Logger.warn(ex, "Error while converting json to account");
            throw ex;
        }
    }
}
