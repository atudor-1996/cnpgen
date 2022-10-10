package com.cnp.cnpgen;

import com.cnp.utils.CnpUtils;
import com.cnp.utils.County;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Cnpgen implements CnpUtils {
    private final int id;
    private String cnp;
    public static int counter = 1;


    public int getId() {
        return id;
    }

    public String getCnp() {
        return cnp;
    }

    @Override
    public String formatGender(String year) {

        int birthYear = Integer.parseInt(year);
        //System.out.println(birthYear);
        String gender = randomGender();

        if (birthYear >= 1800 && birthYear <= 1899) gender = Integer.toString(Integer.parseInt(gender) + 2);
        if (birthYear >= 2000 && birthYear <= 2099) gender = Integer.toString(Integer.parseInt(gender) + 4);
        return gender;

    }

    public String formatGender(String year, int gender) {

        int birthYear = Integer.parseInt(year);
        //System.out.println(birthYear);
        String genderStr = Integer.toString(gender);

        if (birthYear >= 1800 && birthYear <= 1899) genderStr = Integer.toString(Integer.parseInt(genderStr) + 2);
        if (birthYear >= 2000 && birthYear <= 2099) genderStr = Integer.toString(Integer.parseInt(genderStr) + 4);
        return genderStr;

    }

    @Override
    public String randomGender() {

        int val = (int) (Math.random() * 2 + 1);
        //System.out.println(val);
        return Integer.toString(val);
    }

    @Override
    public String randomDate() {
        LocalDate startInclusive = LocalDate.of(1900, 1, 1);
        LocalDate endExclusive = LocalDate.of(2099, 12, 30);
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay).toString();
    }

    public String randomDate(int beginYear, int endYear) {

        LocalDate startInclusive = LocalDate.of(beginYear, 1, 1);
        LocalDate endExclusive = LocalDate.of(endYear, 12, 30);
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay).toString();
    }

    @Override
    public String randomCounty() {
        int val = (int) Math.floor(Math.random() * (46) + 1);
        if (val < 10) return "0" + val;
        else return Integer.toString(val);
    }


    @Override
    public String checkSum(int sum) {
        int chkSum = sum % 11;
        return Integer.toString(chkSum == 10 ? 1 : chkSum);
    }

    @Override
    public int dotProductSum(String cnp) {
        int[] chkSumNumber = new int[]{2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
        char[] cnpArr = cnp.toCharArray();
        int sum = 0;
        for (int i = 0; i < chkSumNumber.length; i++) {
            sum += chkSumNumber[i] * (Character.getNumericValue(cnpArr[i]));
        }

        return sum;
    }

    @Override
    public void buildCnp() {

        String[] formattedDate = FormattedDate();
        StringBuilder cnpNoChkSum = new StringBuilder(formatGender(formattedDate[1]))
                .append(formattedDate[0])
                .append(randomCounty())
                .append(randomNNN());
        String chkSum = checkSum(dotProductSum(cnpNoChkSum.toString()));
        cnp = cnpNoChkSum.append(chkSum).toString();

    }

    public void buildCnp(Integer gender, Integer beginYear, Integer endYear, Integer county) throws RuntimeException {
        if (beginYear != null && endYear != null && (beginYear < 1900 || beginYear > 2099 || endYear < 1900 || endYear > 2099))
            throw new RuntimeException("Begin and end year must be in interval [1900,2099]");
        if (gender != null && gender != 1 && gender != 2) throw new RuntimeException("gender must be 1 or 2");
        if (county != null && (county < 1 || county > 46) && (county != 51 && county != 52))
            throw new RuntimeException("County must be in interval [1,46] or [51,52]");
        String[] formattedDate = FormattedDate(beginYear == null ? 1900 : beginYear, endYear == null ? 2099 : endYear);
        StringBuilder cnpNoChkSum = new StringBuilder(formatGender(formattedDate[1], gender == null ? Integer.parseInt(randomGender()) : gender))
                .append(formattedDate[0])
                .append(county == null ? randomCounty() : county)
                .append(randomNNN());
        String chkSum = checkSum(dotProductSum(cnpNoChkSum.toString()));
        cnp = cnpNoChkSum.append(chkSum).toString();

    }

    public String[] FormattedDate() {
        String[] dateArr = randomDate().split("-");

        return new String[]{dateArr[0].substring(2, 4) +
                dateArr[1] +
                dateArr[1], dateArr[0]};
    }

    public String[] FormattedDate(int beginYear, int endYear) {
        String[] dateArr = randomDate(beginYear, endYear).split("-");

        return new String[]{dateArr[0].substring(2, 4) +
                dateArr[1] +
                dateArr[1], dateArr[0]};
    }

    @Override
    public String randomNNN() {
        Random rand = new Random();
        int num = rand.nextInt(1000);
        if (num < 10) return "00" + num;
        if (num < 100) return "0" + num;
        return Integer.toString(num);

    }

    @Override
    public String toString() {
        return "Cnpgen{" +
                "cnp='" + cnp + '\'' +
                '}';
    }


    public Cnpgen() {
        id = counter++;
        buildCnp();
    }

    public Cnpgen(Integer gender, Integer beginYear, Integer endYear, Integer county) {
        id = counter++;
        buildCnp(gender, beginYear, endYear, county);
    }

    public static LinkedHashSet<Cnpgen> getCnpList(int number) {
        counter = 1;

        LinkedHashSet<Cnpgen> cnpSet = new LinkedHashSet<>();

        while (cnpSet.size() < number) {
            cnpSet.add(new Cnpgen());
        }


        return cnpSet;
    }


    public static LinkedHashSet<Cnpgen> getCnpList(int number, Integer gender, Integer beginYear, Integer endYear, Integer county) {
        counter = 1;

        LinkedHashSet<Cnpgen> cnpSet = new LinkedHashSet<>();

        while (cnpSet.size() < number) {
            cnpSet.add(new Cnpgen(gender, beginYear, endYear, county));
        }


        return cnpSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cnpgen cnpgen = (Cnpgen) o;

        return cnp.equals(cnpgen.cnp);
    }

    @Override
    public int hashCode() {
        return cnp.hashCode();
    }

    public String countyName(String code)
    {
        return County.countyMap.get(code);
    }
}
