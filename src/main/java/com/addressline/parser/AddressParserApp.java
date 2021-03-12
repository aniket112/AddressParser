package com.addressline.parser;

import com.addressline.parser.util.ConfigFileReader;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressParserApp {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.print("Please Enter Street Name and Address: ");
    String address = scanner.nextLine();

    List<AddressRegexData> regexData = ConfigFileReader.getRegexData();

    if (regexData.size() == 0) {
      System.out.println("Error Occurred while reading the regex objects");
      return;
    }

    AddressLineParser addressLineParser = new AddressLineParser(regexData);
    Map<String, String> addressMap = addressLineParser.parseAddress(address);

    if (addressMap.size() == 0) {
      System.out.println("Unrecognized Address Format");
      return;
    }

    JSONObject jsonTestData = new JSONObject();
    jsonTestData.put("street", addressMap.get("street"));
    jsonTestData.put("housenumber", addressMap.get("housenumber"));

    System.out.println(jsonTestData);
  }
}
