package com.addressline.parser;

import java.util.regex.Pattern;

public class AddressRegexData {

    private final Pattern regex;
    private final int streetNameIndex;
    private final int streetNumberIndex;

    public Pattern getRegex() {
        return regex;
    }

    public int getStreetNameIndex() {
        return streetNameIndex;
    }

    public int getStreetNumberIndex() {
        return streetNumberIndex;
    }

    public AddressRegexData(String pattern, int streetNameIndex, int streetNumberIndex)
    {
        this.regex = Pattern.compile(pattern);
        this.streetNameIndex = streetNameIndex;
        this.streetNumberIndex = streetNumberIndex;
    }

}
