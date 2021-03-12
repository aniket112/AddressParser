package com.addressline.parser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class AddressLineParser {
    private List<AddressRegexData> regexObjects;

    public AddressLineParser(List<AddressRegexData> regexObjects) {
        this.regexObjects = regexObjects;
    }

    /**
     * Match the given address for each reqex expression defined in the regexconfig file until match is found
     * @param address
     * @return Map of Street and Housenumber
     */
    public Map<String,String> parseAddress(String address) {
        Map<String, String> addressMap = new HashMap<>();

        for(AddressRegexData regexObject: regexObjects) {
            Matcher match = regexObject.getRegex().matcher(address);
            if (match.matches())
            {
                String number = match.group(regexObject.getStreetNumberIndex());
                String street = match.group(regexObject.getStreetNameIndex());
                addressMap.put("street", street);
                addressMap.put("housenumber", number);
                break;
            }
        }

        return addressMap;
    }
}

