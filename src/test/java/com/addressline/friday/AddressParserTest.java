package com.addressline.parser;

import com.addressline.parser.util.ConfigFileReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressParserTest {
    private static List<AddressRegexData> regexData;

    @BeforeAll
    public static void setup() {
        regexData = ConfigFileReader.getRegexData();
    }

    @Test
    public void TestAddressSeperatedWithSapce() {
        AddressLineParser addressLineParser = new AddressLineParser(regexData);
        Map<String, String> expectedData = getData("Winteralle", "3");
        assertTrue(expectedData.equals(addressLineParser.parseAddress("Winteralle 3")));
    }

    @Test
    public void TestAlphanumbericStreetNumber() {
        Map<String, String> expectedData = getData("Blaufeldweg", "123B");
        AddressLineParser addressLineParser = new AddressLineParser(regexData);
        assertTrue(expectedData.equals(addressLineParser.parseAddress("Blaufeldweg 123B")));
    }

    @Test
    public void TestSpaceInStreetName() {
        Map<String, String> expectedData = getData("Am Bächle", "23");
        AddressLineParser addressLineParser = new AddressLineParser(regexData);
        assertTrue(expectedData.equals(addressLineParser.parseAddress("Am Bächle 23")));
    }

    @Test
    public void TestSpaceInStreetNameAndNumber() {
        Map<String, String> expectedData = getData("Auf der Vogelwiese", "23 b");
        AddressLineParser addressLineParser = new AddressLineParser(regexData);
        assertTrue(expectedData.equals(addressLineParser.parseAddress("Auf der Vogelwiese 23 b")));
    }

    @Test
    public void TestCommaInAddress() {
        Map<String, String> expectedData = getData("Calle Aduana", "29");
        AddressLineParser addressLineParser = new AddressLineParser(regexData);
        assertTrue(expectedData.equals(addressLineParser.parseAddress("Calle Aduana, 29")));
    }

    @Test
    public void TestAddressStartingWithNumber() {
        AddressLineParser addressLineParser = new AddressLineParser(regexData);
        Map<String, String> expectedData;

        expectedData = getData("rue de la revolution", "4");
        assertTrue(expectedData.equals(addressLineParser.parseAddress("4, rue de la revolution")));

        expectedData = getData("Broadway Av", "200");
        assertTrue(expectedData.equals(addressLineParser.parseAddress("200 Broadway Av")));

    }

    @Test
    public void TestAddressWithNoFormat() {
        AddressLineParser addressLineParser = new AddressLineParser(regexData);
        Map<String, String> expectedData;

        expectedData = getData("Calle 39", "No 1540");
        assertTrue(expectedData.equals(addressLineParser.parseAddress("Calle 39 No 1540")));

        expectedData = getData("Calle 39", "no 1540");
        assertTrue(expectedData.equals(addressLineParser.parseAddress("Calle 39 no 1540")));

    }
    private HashMap<String, String> getData(String street, String number) {
        return new HashMap<String, String>() {
            {
                put("street", street);
                put("housenumber", number);
            }
        };
    }
}
