package com.service;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static final Pattern namePattern = Pattern.compile("^([A-Z])[a-zA-Z]+-?([A-Z][a-z]+)?");
    private static final Pattern emailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern usernamePattern = Pattern.compile("^([a-zA-Z])[a-zA-Z0-9-.]{3,}");
    private static final List<String> doctorCategories = List.of("pediatrician", "neurosurgeon", "dentist",
            "optometrist", "psychologist", "anesthesiologist");
    private static final List<String> nurseSpecializations = List.of("manipulative nursing", "critical care nursing", "oncology nursing",
            "public heath nursing");
    public static boolean firstOrLastNameIsValid(String firstName) {
        final Matcher nameMatcher = namePattern.matcher(firstName);
        if(firstName.isBlank() || firstName.isEmpty()) {
            return false;
        }
        return nameMatcher.matches();
    }

    public static boolean categoryIsValid(String category) {
        String res = doctorCategories.stream().filter(c -> c.equals(category.toLowerCase())).findAny().orElse(null);
        return res != null;
    }

    public static boolean specializationIsValid(String specialization) {
        String res = nurseSpecializations.stream().filter(c -> c.equals(specialization.toLowerCase())).findAny().orElse(null);
        return res != null;
    }

    public static boolean emailIsValid(String username){
        final Matcher emailMatcher = emailPattern.matcher(username);
        return emailMatcher.matches();
    }

    public static boolean usernameIsValid(String username){
        final Matcher emailMatcher = usernamePattern.matcher(username);
        return emailMatcher.matches();
    }
}
