package parsers.bank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Bank;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BankParserOfTheInnerJson {

    private static ObjectMapper objectMapper = getObjectMapper();

    private static ObjectMapper getObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        return defaultObjectMapper;
    }

    private static JsonNode getJsonNode(File file) throws IOException {
        JsonNode dataNode = objectMapper.readTree(file);
        return dataNode;
    }

    public static List<Bank> getBanksList(File file) throws IOException {
        JsonNode jsonNode = getJsonNode(file);
        List<Bank> list = objectMapper.readValue(String.valueOf(jsonNode.get("data")), new TypeReference<List<Bank>>(){});
        return list;
    }


}
