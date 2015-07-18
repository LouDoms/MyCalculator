package com.prelim.piczon.loudoms.mycalculator.Presenter;

import com.prelim.piczon.loudoms.mycalculator.Model.Logic;

/**
 * Created by LouDoms on 7/17/2015.
 */
public interface Operation_Presenter {

    public Logic getLogic();
    public boolean isFieldEmpty(String field);
    public void onClickOperand(String firstNum, String secondNum, String operator);


}
