package com.Division;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static  ArrayList<String> Division(double dividend, double divisor, int precision){
        boolean isMinusResult = (dividend < 0 || divisor < 0) && !(dividend < 0 && divisor < 0);
        boolean isFirstPart = true;
        ArrayList<String> result = new ArrayList<>();
        String[] tempArr = RemoveDecimalPoint(Math.abs(dividend)+"", Math.abs(divisor)+"");
        Long preDividend = Long.parseLong(tempArr[0]) ,
                  preDivisor = Long.parseLong(tempArr[1]);
        int positionResult = 0;

        for(int i = 0; i < precision; i++){
            if(isFirstPart){
                if(preDividend - preDivisor == 0){
                    result.add("1");
                    break;
                }

                while(preDividend - preDivisor >= 0){
                    positionResult++;
                    preDividend -= preDivisor;
                }

                if(preDividend != 0){
                    result.add(positionResult+"");
                    result.add(".");
                    isFirstPart = false;
                    positionResult = 0;
                    preDividend *= 10;
                }
                else{
                    result.add(positionResult+"");
                    return result;
                }
            }
            else{
                while(preDividend - preDivisor >= 0){
                    positionResult++;
                    preDividend -= preDivisor;
                    if(preDividend == 0)break;
                }

                if(preDividend - preDivisor < 0){
                    preDividend *= 10;
                }

                result.add(positionResult + "");
                positionResult = 0;
            }
        }

        if(isMinusResult){
            result.add(0, "-");
            return result;
        }
        else{
            return result;
        }
    }

    private static String[] RemoveDecimalPoint(String numString1, String numString2){
        String[] result = new String[2];

        int num1DPL = numString1.split("\\.")[1].length();
        int num2DPL = numString2.split("\\.")[1].length();
        numString1 = numString1.replace(".","");
        numString2 = numString2.replace(".","");
        int maxDPL = Math.max(num1DPL,num2DPL);
        boolean isNum1HasMaxDPL = num1DPL > num2DPL;

        if(isNum1HasMaxDPL){
            int length = maxDPL - num2DPL;
            for(int i = 0; i<length; i++){
                numString2 = numString2.concat("0");
            }
        }
        else{
            int length = maxDPL - num1DPL;
            for(int i = 0; i<length; i++){
                numString1 = numString1.concat("0");
            }
        }

        result[0] = numString1;
        result[1] = numString2;

        return result;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true){
            StringBuilder sb = new StringBuilder();
            double dividend, divisor;
            int precision;
            System.out.println("==================");
            System.out.println("Dividend:");
            dividend = s.nextDouble();
            System.out.println("Divisor:");
            divisor = s.nextDouble();
            System.out.println("Precision:");
            precision = s.nextInt();
            for(String str : Division(dividend,divisor,precision)){
                sb.append(str);
            }
            System.out.println("THE RESULT IS: " + sb.toString());
            System.out.println("==================");
            System.out.println();
        }
    }
}
