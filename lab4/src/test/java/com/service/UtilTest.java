package com.service;

import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;



public class UtilTest {

    @Test
    public void firstNameMashaIsValid() {
        assertTrue(Util.firstOrLastNameIsValid("Masha"));
        assertTrue(Util.firstOrLastNameIsValid("Vitalii"));
        assertTrue(Util.firstOrLastNameIsValid("Max"));
        assertTrue(Util.firstOrLastNameIsValid("Nasty"));
        assertTrue(Util.firstOrLastNameIsValid("Natalie"));
        assertTrue(Util.firstOrLastNameIsValid("Anna-Elise"));
    }

    @Test
    public void assertFalseIfNameIsBlankOrEmpty() {
        assertFalse(Util.firstOrLastNameIsValid(""));
        assertFalse(Util.firstOrLastNameIsValid("        "));
    }

    @Test
    public void firstNameMashaIsNotValid() {
        assertFalse(Util.firstOrLastNameIsValid("Masha22"));
        assertFalse(Util.firstOrLastNameIsValid(".Vitalii"));
        assertFalse(Util.firstOrLastNameIsValid("Max-1"));
        assertFalse(Util.firstOrLastNameIsValid("nasty"));
        assertFalse(Util.firstOrLastNameIsValid("N*atalie"));
        assertFalse(Util.firstOrLastNameIsValid("Fox1"));
    }

    @Test
    public void lastNameMashaIsValid() {
        assertTrue(Util.firstOrLastNameIsValid("Huzii"));
        assertTrue(Util.firstOrLastNameIsValid("Steward"));
        assertTrue(Util.firstOrLastNameIsValid("Stone"));
        assertTrue(Util.firstOrLastNameIsValid("Galko"));
        assertTrue(Util.firstOrLastNameIsValid("Marchenko"));
        assertTrue(Util.firstOrLastNameIsValid("March-Smith"));
    }

    @Test
    public void lastNameMashaIsNotValid() {
        assertFalse(Util.firstOrLastNameIsValid(".Huzii"));
        assertFalse(Util.firstOrLastNameIsValid("S9teward"));
        assertFalse(Util.firstOrLastNameIsValid("Stone."));
        assertFalse(Util.firstOrLastNameIsValid("Galko99"));
        assertFalse(Util.firstOrLastNameIsValid("marchenko"));
        assertFalse(Util.firstOrLastNameIsValid("March-smith"));
        assertFalse(Util.firstOrLastNameIsValid("March-S#mith"));
    }

    @Test
    public void categoryIsValid() {
        assertTrue(Util.categoryIsValid("pediatrician"));
        assertTrue(Util.categoryIsValid("neurosurgeon"));
        assertTrue(Util.categoryIsValid("dentist"));
        assertTrue(Util.categoryIsValid("Dentist"));
        assertTrue(Util.categoryIsValid("optometrist"));
        assertTrue(Util.categoryIsValid("psychologist"));
        assertTrue(Util.categoryIsValid("Psychologist"));
    }

    @Test
    public void categoryIsNotValid() {
        assertFalse(Util.categoryIsValid("pedatrician"));
        assertFalse(Util.categoryIsValid("neurosurgon"));
        assertFalse(Util.categoryIsValid("Pentist"));
        assertFalse(Util.categoryIsValid("aptometrist"));
        assertFalse(Util.categoryIsValid("psychologst"));
        assertFalse(Util.categoryIsValid("Psychologis"));
        assertFalse(Util.categoryIsValid("oncologist"));
    }
    @Test
    public void specializationIsValid() {
        assertTrue(Util.specializationIsValid("manipulative nursing"));
        assertTrue(Util.specializationIsValid("critical care nursing"));
        assertTrue(Util.specializationIsValid("Critical care nursing"));
        assertTrue(Util.specializationIsValid("public heath nursing"));
    }

    @Test
    public void specializationNotIsValid() {
        assertFalse(Util.specializationIsValid("dentist"));
        assertFalse(Util.specializationIsValid("optometrist"));
        assertFalse(Util.specializationIsValid("general nursing"));
        assertFalse(Util.specializationIsValid("General nursing"));
        assertFalse(Util.specializationIsValid("hhhh,,,"));
    }

    @Test
    public void emailIsValid() {
        assertTrue(Util.emailIsValid("vital.xvv@gmail.com"));
        assertTrue(Util.emailIsValid("apple@ukr.net"));
        assertTrue(Util.emailIsValid("santiago11@mail.ru"));
        assertTrue(Util.emailIsValid("catty.girl@gov.ua"));
    }

    @Test
    public void emailIsNotValid() {
        assertFalse(Util.emailIsValid("vital?xvv@gmail.com"));
        assertFalse(Util.emailIsValid("vital-xvv"));
        assertFalse(Util.emailIsValid("nastycros"));
        assertFalse(Util.emailIsValid("Jack"));
        assertFalse(Util.emailIsValid("stonie*folks@gmail.com"));
        assertFalse(Util.emailIsValid("stoniefolks@gmail"));
        assertFalse(Util.emailIsValid("perl11"));
    }

    @Test
    public void usernameIsValid() {
        assertTrue(Util.usernameIsValid("maxsteel12"));
        assertTrue(Util.usernameIsValid("Maxsteel"));
        assertTrue(Util.usernameIsValid("may-fox"));
        assertTrue(Util.usernameIsValid("may-fox12"));
        assertTrue(Util.usernameIsValid("fox11-12"));}

    @Test
    public void usernameIsNotValid() {
        assertFalse(Util.usernameIsValid("maxste/el12"));
        assertFalse(Util.usernameIsValid("Ma#xsteel"));
        assertFalse(Util.usernameIsValid("m"));
        assertFalse(Util.usernameIsValid(".may-fox12"));
        assertFalse(Util.usernameIsValid("2fox11-12"));
    }
}