package com.prelim.piczon.loudoms.mycalculator.Presenter;

import android.text.TextUtils;

import com.prelim.piczon.loudoms.mycalculator.Model.Logic;

/**
 * Created by LouDoms on 7/17/2015.
 */
public class Operation_PresenterImpl implements Operation_Presenter {

    Logic logic;

    @Override
    public boolean isFieldEmpty(String field){
        if(TextUtils.isEmpty(field)){
            return true;
        }
        return false;
    }

    @Override
    public Logic getLogic() {
        return logic;
    }

    @Override
    public void onClickOperand(String firstNum, String secondNum, String operator){
        this.logic = new Logic(firstNum, secondNum, operator);
    }

}
