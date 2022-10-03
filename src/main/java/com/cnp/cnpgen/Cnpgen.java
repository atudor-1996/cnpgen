package com.cnp.cnpgen;

import com.cnp.utils.CnpUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Cnpgen implements CnpUtils {
    private String cnp;
    private LocalDate birthDate;

    @Override
    public String formatGender() {

        int birthYear = birthDate.getYear();
        System.out.println(birthYear);
        String gender = randomGender();

        if(birthYear >= 1800 && birthYear <= 1899) gender = Integer.toString(Integer.parseInt(gender)+2);
        if(birthYear >= 2000 && birthYear <= 2099) gender = Integer.toString(Integer.parseInt(gender)+4);
        return gender;

    }

    @Override
    public String randomGender() {

        int val = (int) ( Math.random() * 2 + 1);
        //System.out.println(val);
        return Integer.toString(val);
    }

    @Override
    public String randomDate() {
        LocalDate startInclusive = LocalDate.of(1900,1,1);
        LocalDate endExclusive = LocalDate.of(2099,12,30);
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        birthDate = LocalDate.ofEpochDay(randomDay);
        return birthDate.toString();
    }

    public String randomDate(int beginYear, int endYear) {
        //TODO make throw mechanism -- for new functionality
        if(beginYear < 1900 || beginYear > 2099 || endYear < 1900 || endYear > 2099) return "NotInRange";
        LocalDate startInclusive = LocalDate.of(beginYear,1,1);
        LocalDate endExclusive = LocalDate.of(endYear,12,30);
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        String val = LocalDate.ofEpochDay(randomDay).toString();
        birthDate = LocalDate.ofEpochDay(randomDay);
        return val;
    }

    @Override
    public String randomCounty() {
        int val = (int)Math.floor(Math.random()*(46)+1);
        if(val < 10)return "0" + val;
        else return Integer.toString(val);
    }

    @Override
    public String checkSum(int sum) {
        int chkSum = sum % 11;
        return Integer.toString(chkSum == 10 ? 1 : chkSum);
    }

    @Override
    public int dotProductSum(String cnp) {
        int[] chkSumNumber = new int[] {2,7,9,1,4,6,3,5,8,2,7,9};
        char[] cnpArr = cnp.toCharArray();
        int sum=0;
        for(int i=0;i<chkSumNumber.length;i++)
        {
            sum += chkSumNumber[i]*(Character.getNumericValue(cnpArr[i]));
        }

        return sum;
    }

    @Override
    public void buildCnp() {

        String formattedDate = getFormattedDate();
        StringBuilder cnpNoChkSum = new StringBuilder(formatGender())
                .append(formattedDate)
                .append(randomCounty())
                .append(randomNNN());
        String chkSum = checkSum(dotProductSum(cnpNoChkSum.toString()));
        cnp =  cnpNoChkSum.append(chkSum).toString();

    }

    public String getFormattedDate()
    {
        String[] dateArr = randomDate().split("-");
        String formattedDate = dateArr[0].substring(2, 4) +
                dateArr[1] +
                dateArr[1];

        return formattedDate;
    }

    @Override
    public String randomNNN() {
        Random rand = new Random();
        int num = rand.nextInt(1000) +1;
        if( num < 10) return "00" + num;
        if(num < 100)return "0" + num;
        return Integer.toString(num);

    }

    @Override
    public String toString() {
        return "Cnpgen{" +
                "cnp='" + cnp + '\'' +
                '}';
    }

    public String getCnp() {
        return cnp;
    }

    public Cnpgen() {
        buildCnp();
    }

    public HashSet<String> getCnpList(int number)
    {
        HashSet<String> cnpList = new HashSet<>();
        while(cnpList.size() < number)
        {
            cnpList.add(new Cnpgen().getCnp());
        }

        return cnpList;
    }
}
