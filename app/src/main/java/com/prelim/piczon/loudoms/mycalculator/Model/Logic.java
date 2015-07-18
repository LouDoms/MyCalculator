package com.prelim.piczon.loudoms.mycalculator.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by LouDoms on 7/17/2015.
 */
public class Logic implements Parcelable {

    private String firstNum;
    private String secondNum;
    private String operator;
    private double result;


    public Logic(String firstNum, String secondNum, String operator) {
        setValues(firstNum, secondNum);
        setOperator(operator);
    }


    public Logic setValues(String first, String second){
        try {
            this.firstNum = String.valueOf(getNumberFormat(first));
            this.secondNum = String.valueOf(getNumberFormat(second));
        } catch (ParseException e) {
            this.firstNum = first;
            this.secondNum = second;
        }
        return this;
    }

    public Logic setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public String getFirstNum() {
        return firstNum;
    }

    public String getSecondNum() {
        return secondNum;
    }

    public String getOperator() {
        return operator;
    }

    public String getResult() {
        return roundUp(calculate());
    }

    public Number getNumberFormat(String number) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setGroupingUsed(true);
        return numberFormat.parse(number);
    }

    public String roundUp(double result){
        DecimalFormat decimalFormat = new DecimalFormat();
        return decimalFormat.format(result);
    }

    public double calculate(){
        double firstNum = 0;
        double secondNum = 0;
        String operator = null;
        try {
            firstNum = getNumberFormat(getFirstNum()).doubleValue();
            secondNum = getNumberFormat(getSecondNum()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        operator = String.valueOf(getOperator().charAt(0));
        double result = 0;

        if (!isTypeCompatible()) {
            return -1D;
        } else {
            switch (operator) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "*":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    result = firstNum / secondNum;
                    break;
            }
            return result;
        }
    }

    public boolean isTypeCompatible() {
        if (!TextUtils.isEmpty(getFirstNum()) && !TextUtils.isEmpty(getSecondNum()) && !TextUtils.isEmpty(getOperator())) {
            try {
                getNumberFormat(getFirstNum()).toString();
                getNumberFormat(getSecondNum()).toString();
            } catch (ParseException parseexception) {
                return false;
            }
            if (getOperator().length() == 1) {
                char c1 = getOperator().charAt(0);
                if (c1 == '+' || c1 == '-' || c1 == '*' || c1 == '/') {
                    return true;
                }
            }
        }
        return false;
    }


    protected Logic(Parcel in) {
        this.firstNum = in.readString();
        this.secondNum = in.readString();
        this.operator = in.readString();
        this.result = in.readDouble();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.firstNum);
        parcel.writeString(this.secondNum);
        parcel.writeString(this.operator);
        parcel.writeDouble(this.result);
    }

    public static final Creator<Logic> CREATOR = new Creator<Logic>() {

        public Logic createFromParcel(Parcel source) {
            return new Logic(source);
        }


        public Logic[] newArray(int size) {
            return new Logic[size];
        }
    };
}
