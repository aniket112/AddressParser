A command line API which takes a address as input and parses it into Street name and house number

Some of the examples are 

Input: string of address

Output: string of street and string of street-number as JSON object

1) "Winterallee 3" -> {"street": "Winterallee", "housenumber": "3"}
2) "Musterstrasse 45" -> {"street": "Musterstrasse", "housenumber": "45"}
3) "Blaufeldweg 123B" -> {"street": "Blaufeldweg", "housenumber": "123B"}

Consider more complicated cases
1)"Am Bächle 23" -> {"street": "Am Bächle", "housenumber": "23"}
2)"Auf der Vogelwiese 23 b" -> {"street": "Auf der Vogelwiese", "housenumber": "23 b"}

Consider other countries (complex cases)
1)"4, rue de la revolution" -> {"street": "rue de la revolution", "housenumber": "4"}
2)"200 Broadway Av" -> {"street": "Broadway Av", "housenumber": "200"}
3)"Calle Aduana, 29" -> {"street": "Calle Aduana", "housenumber": "29"}
4)"Calle 39 No 1540" -> {"street": "Calle 39", "housenumber": "No 1540"}
