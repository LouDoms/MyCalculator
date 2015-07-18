package com.prelim.piczon.loudoms.mycalculator.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.prelim.piczon.loudoms.mycalculator.Model.Logic;
import com.prelim.piczon.loudoms.mycalculator.Presenter.Main_Presenter;
import com.prelim.piczon.loudoms.mycalculator.Presenter.Operation_Presenter;
import com.prelim.piczon.loudoms.mycalculator.Presenter.Operation_PresenterImpl;
import com.prelim.piczon.loudoms.mycalculator.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Main_Presenter {

    Toolbar toolbar;
    AwesomeValidation awesomeValidation;
    Operation_Presenter operation_presenter;
    Logic logic;

    @Bind(R.id.firstNum)
    EditText edtFirstNum;

    @Bind(R.id.secondNum)
    EditText edtSecondNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operation_presenter = new Operation_PresenterImpl();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.addOp, R.id.minusOp, R.id.multiplyOp, R.id.divideOp})
    public void calculate(Button view){
        String first = this.edtFirstNum.getText().toString();
        String second = this.edtSecondNum.getText().toString();
        String operator = view.getText().toString();

        if(!operation_presenter.isFieldEmpty(first) && !operation_presenter.isFieldEmpty(second)){
            operation_presenter.onClickOperand(first, second, operator);
            startResultsActivity(operation_presenter.getLogic());
        }else if(operation_presenter.isFieldEmpty(first) && operation_presenter.isFieldEmpty(second)){
                setFirstFieldError();
                setSecondFieldError();
        }else if(operation_presenter.isFieldEmpty(first) && !operation_presenter.isFieldEmpty(second)){
                setFirstFieldError();
                edtSecondNum.setError(null);
                edtFirstNum.requestFocus();
        }else if(operation_presenter.isFieldEmpty(second) && !operation_presenter.isFieldEmpty(first)){
                setSecondFieldError();
                edtFirstNum.setError(null);
                edtSecondNum.requestFocus();
        }

    }

    @OnClick(R.id.clear)
    public void clear(){
        this.edtFirstNum.setText("");
        this.edtSecondNum.setText("");
        this.edtFirstNum.setError(null);
        this.edtSecondNum.setError(null);
        this.edtFirstNum.requestFocus();
    }

    @OnClick(R.id.exit)
    public void exit(){
        this.finish();
    }

    public void startResultsActivity(Logic logic){
        Intent i = new Intent(getBaseContext(), ResultsActivity.class);
        i.putExtra(DATA,logic);
        startActivity(i);
    }

    @Override
    public void setFirstFieldError() {
        awesomeValidation.addValidation(edtFirstNum, "first", "Put a value!");
        awesomeValidation.validate();
    }

    @Override
    public void setSecondFieldError() {
        awesomeValidation.addValidation(edtSecondNum, "second", "Put a value!");
        awesomeValidation.validate();
    }
}
