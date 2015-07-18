package com.prelim.piczon.loudoms.mycalculator.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prelim.piczon.loudoms.mycalculator.Model.Logic;
import com.prelim.piczon.loudoms.mycalculator.Presenter.Results_Presenter;
import com.prelim.piczon.loudoms.mycalculator.R;

import java.text.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LouDoms on 7/17/2015.
 */
public class ResultsActivity extends AppCompatActivity implements Results_Presenter{

    @Bind(R.id.txtFirstNum)
    TextView txtFirstNum;

    @Bind(R.id.txtSecondNum)
    TextView txtSecondNum;

    @Bind(R.id.txtOperand)
    TextView txtOperand;

    @Bind(R.id.txtResult)
    TextView txtResult;

    @Bind(R.id.resultsToolbar)
    Toolbar toolbar;

    Logic logic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        logic = getIntent().getParcelableExtra(DATA);
        if(logic != null){
            txtFirstNum.setText(logic.getFirstNum());
            txtOperand.setText(logic.getOperator());
            txtSecondNum.setText(logic.getSecondNum());
            String result = logic.getResult();
            try {
                validateResult(result);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setSuccessColor() {
        txtResult.setBackgroundColor(getResources().getColor(R.color.successColor));
    }

    @Override
    public void setErrorColor() {
        txtResult.setBackgroundColor(getResources().getColor(R.color.errorColor));
    }


    @Override
    public void validateResult(String result) throws ParseException {
        if(logic.getNumberFormat(result).doubleValue() == -1D){
            txtResult.setText("Error!!!");
            setErrorColor();
        }else {
            txtResult.setText(result);
            setSuccessColor();
        }
    }
}
