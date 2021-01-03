package com.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.println("----------Original String----------");
        String string = "I am a string.     Yes, I am...1234 65";
        System.out.println(string);
        // replaces based on character data provided
        System.out.println("----------Replace I with You using replace----------");
        String updatedString = string.replace("I", "You");
        System.out.println(updatedString);
        // replaces based on regex data provided
        System.out.println("----------Replace I with You using replaceAll----------");
        String updatedString2 = string.replaceAll("I", "You");
        System.out.println(updatedString2);

        // . as a Regex
        System.out.println("----------Using . as Regex----------");
        String dotChar = string.replaceAll(".", "#*");
        System.out.println(". : " + dotChar);

        // ^ as a Regex
        System.out.println("----------Using ^ as Regex----------");
        String startChar = string.replaceAll("^", "#*");
        System.out.println("^ : " + startChar);
        String startChar2 = string.replaceAll("^I am", "#*");
        System.out.println("^I am : " + startChar2);

        // .matches in Regex
        System.out.println("----------Using matches to match string----------");
        System.out.println(string.matches("."));
        System.out.println(string.matches("^hello"));
        System.out.println(string.matches("^I am"));
        System.out.println(string.matches("I am an string. Yes, I am"));

        // $ as a Regex
        System.out.println("----------Using $ as Regex----------");
        String endChar = string.replaceAll("$", "#*");
        System.out.println("$ : " + endChar);
        String endChar2 = string.replaceAll("I am.$", "#*");
        System.out.println("I am.$ : " + endChar2);
        String endChar3 = string.replaceAll("I am\\.\\.\\.$", "#*");
        System.out.println("I am...$ : " + endChar3);

        // [] as a Regex
        System.out.println("----------Using [] as Regex----------");
        String withoutVowel = string.replaceAll("[aeiouAEIOU]", "*");
        System.out.println("[aeiouAEIOU] : " + withoutVowel);
        String vowelFollowed = string.replaceAll("[aeiouAEIOU][mn]", "*");
        System.out.println("[aeiouAEIOU][mn] : " + vowelFollowed);

        System.out.println("\"harry\".replaceAll(\"[Hh]arry\",\"Harry\") : " +
                "harry".replaceAll("[Hh]arry", "Harry"));
        System.out.println("\"Harry\".replaceAll(\"[Hh]arry\",\"Harry\") : " +
                "Harry".replaceAll("[Hh]arry", "Harry"));

        // [^] as a Regex
        System.out.println("----------Using [^] as Regex----------");
        System.out.println(string.replaceAll("[^aeiouAEIOU]", "*"));
        System.out.println(string.replaceAll("[^aeiouAEIOU][^aeiou]", "*"));
        System.out.println(string.replaceAll("[^a-z]", "*"));
        System.out.println(string.replaceAll("[^A-Z]", "*"));
        System.out.println(string.replaceAll("[^a-dA-D]", "*"));

        // (?i) as a Regex
        System.out.println("----------Using (?i) as Regex----------");
        System.out.println(string.replaceAll("(?i)[a-l]", "*"));

        System.out.println("\"Harry\".replaceAll(\"(?i)[h]arry\",\"Harry\") : " +
                "Harry".replaceAll("(?i)[h]arry", "Harry"));


        // \d as a Regex
        System.out.println("----------Using \\d as Regex----------");
        System.out.println(string.replaceAll("\\d", "*"));

        // \D as a Regex
        System.out.println("----------Using \\D as Regex----------");
        System.out.println(string.replaceAll("\\D", "*"));

        // \s as a Regex
        System.out.println("----------Using \\s as Regex----------");
        System.out.println(string.replaceAll("\\s", "*"));

        // \S as a Regex
        System.out.println("----------Using \\S as Regex----------");
        System.out.println(string.replaceAll("\\S", "*"));


        // Quantifiers
        System.out.println("----------Using {} as Regex----------");
        System.out.println(string.replaceAll("\\.{3}", "#"));

        System.out.println("----------Using + as Regex----------");
        System.out.println(string.replaceAll("\\.+", "#"));

        System.out.println("----------Using * as Regex----------");
        System.out.println(string.replaceAll("I am\\.*", "#"));

        System.out.println("\n\"sttrrriiiinnnnnnggggggggg\".replaceAll(\"r+i*n{5}\",\"*\")");
        System.out.println("sttrrriiiinnnnnnggggggggg".replaceAll("r+i*n{5}", "*"));


        // Pattern
        System.out.println("----------Using Pattern and Matcher to match Regex----------");
        StringBuilder htmlString = new StringBuilder("<h1>Heading 1</h1>");
        htmlString.append("<h2>sub heading</h2>");
        htmlString.append("<p>This is a paragraph 1</p>");
        htmlString.append("<p>This is a paragraph 2</p>");
        htmlString.append("<h2>sub heading2</h2>");
        htmlString.append("<p>This is a summary</p>");

//        String h2Pattern = ".*<h2>.*";
        String h2Pattern = "</*h2>";
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlString);
        System.out.println("Pattern Matches : " + matcher.matches());

        // Matcher Methods
        System.out.println("----------Using Matcher methods to match Regex----------");
        matcher.reset();
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("count : " + count + " starts at " + matcher.start() + " and ends at " + matcher.end());
        }


        // Group Patterns
        System.out.println("----------Using Group () Patterns to match Regex----------");
//        String h2GroupPattern = "(</*h2>)";
//        String h2GroupPattern = "(<h2>.*</h2>)";
//        String h2GroupPattern = "(<h2>.*?</h2>)";
        String h2GroupPattern = "(<h2>)(.*?)(</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlString);
        boolean groupMatches = groupMatcher.matches();
        System.out.println("Pattern Matches : " + groupMatches);
        groupMatcher.reset();
        count = 0;
        while (groupMatcher.find()) {
            count++;
//            System.out.println(groupMatcher.group() + " count : " + count + " starts at " + groupMatcher.start() + " and ends at " + groupMatcher.end());
            System.out.println(groupMatcher.group(2) + " count : " + count + " starts at " + groupMatcher.start() + " and ends at " + groupMatcher.end());
        }

        // And Or Not Patterns
        System.out.println("----------Using AND OR NOT to match Regex----------");
        System.out.println("harry".replaceAll("[Hh]arry", "Larry"));
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));

        System.out.println("tstvtrt".replaceAll("t[^v]", "*"));
        boolean matches = Pattern.matches("t[^v]", "tstvtrt");
        System.out.println(matches);

        Pattern tNotVPattern = Pattern.compile("t(?!v)");
        Matcher tNotVMatcher = tNotVPattern.matcher("tstvtrt");
        count = 0;
        while (tNotVMatcher.find()) {
            count++;
            System.out.println(tNotVMatcher.group() + " count : " + count + " starts at " + tNotVMatcher.start() + " and ends at " + tNotVMatcher.end());
        }

        String phone1 = "1234567890";
        String phone2 = "(800) 123-4567";
        Pattern phonePattern = Pattern.compile("^([(]{1}[0-9]{3}[)]{1}[ ]{1}[0-9]{3}[-]{1}[0-9]{4})$");
        Matcher phoneMatcher1 = phonePattern.matcher(phone1);
        System.out.println(phone1 + " matches: " + phoneMatcher1.matches());
        System.out.println(phone1 + " matches: " + phone1.matches("^([(]{1}[0-9]{3}[)]{1}[ ]{1}[0-9]{3}[-]{1}[0-9]{4})$"));
        Matcher phoneMatcher2 = phonePattern.matcher(phone2);
        System.out.println(phone2 + " matches: " + phoneMatcher2.matches());
        System.out.println(phone2 + " matches: " + phone2.matches("^([(]{1}[0-9]{3}[)]{1}[ ]{1}[0-9]{3}[-]{1}[0-9]{4})$"));

    }
}
