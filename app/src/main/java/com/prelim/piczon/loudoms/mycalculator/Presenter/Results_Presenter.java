package com.prelim.piczon.loudoms.mycalculator.Presenter;

import java.text.ParseException;

/**
 * Created by LouDoms on 7/18/2015.
 */
public interface Results_Presenter {
    static final String DATA = "logicData";
    public void setSuccessColor();
    public void setErrorColor();
    public void validateResult(String result) throws ParseException;
}
