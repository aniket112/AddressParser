package com.addressline.parser.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import com.addressline.parser.AddressRegexData;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigFileReader {
    private static final String PATTERN = "pattern";
    private static final String STREET_NAME_INDEX = "group1";
    private static final String STREET_NUMBER_INDEX = "group2";
    private static final String PATH = "src/main/resources/AddressRegexConfig.json";

    /**
     * Read the Regex expressions defined in the regex json file and unmarshall json data to AddressRegexData object
     * @return List of AddressRegexData
     */
    public static List<AddressRegexData> getRegexData() {
        ArrayList<AddressRegexData> addressRegexData = new ArrayList<>();

        try {
            JSONArray jsonArray = readFromJsonFile();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                addressRegexData.add(new AddressRegexData(jsonObject.getString(PATTERN), Integer.parseInt(jsonObject.getString(STREET_NAME_INDEX)), Integer.parseInt(jsonObject.getString(STREET_NUMBER_INDEX))));
            }

        } catch (IOException ioException) {
            System.out.println("Exception occurred while reading regex config file: " + ioException.toString());
        } catch (JSONException jsonException) {
            System.out.println("Json File Format is invalid: " + jsonException.toString());
        } catch (PatternSyntaxException patternSyntaxException) {
            System.out.println("Invalid Regex Expression defined in config file: " + patternSyntaxException.toString());
        }catch (Exception exception) {
            System.out.println("Exception occurred while reading json config file: " + exception.toString());
        }

        return addressRegexData;
    }

    private static JSONArray readFromJsonFile() throws IOException {
        File file = new File(PATH);
        String content = FileUtils.readFileToString(file, "utf-8");
        return new JSONArray(content);
    }
}
