package com.cnp.utils;

import java.time.LocalDate;

public interface CnpUtils {
    String randomGender();
    String formatGender();
    String randomDate();
    String randomCounty();
    String checkSum(int sum);
    int dotProductSum(String cnp);
    void buildCnp();
    String randomNNN();

}
