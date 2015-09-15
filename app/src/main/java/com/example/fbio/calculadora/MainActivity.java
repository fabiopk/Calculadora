package com.example.fbio.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    public enum State {
        getnum1, getnum2, showresult
    }
    Calculo cal = new Calculo();
    EditText display;
    State state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (EditText) findViewById(R.id.editText);
        display.setText(Float.toString(cal.getNum1()));
        state = State.getnum1;
//        Toast.makeText(this, "state = getnum1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   public void clickCE(View view){
        // do nothing
        Toast.makeText(this, "CLEAR EVERYTHING", Toast.LENGTH_SHORT).show();
       cal.clearAll();
       display.setText("");
    }

    public void clickPlus(View view){
        setNumOp(Calculo.Operation.PLUS);
        refreshState();
    }

    public void clickMinus(View view){
        setNumOp(Calculo.Operation.MINUS);
        refreshState();
    }

    public void clickTimes(View view){
        setNumOp(Calculo.Operation.TIMES);
        refreshState();
    }

    public void clickDivide(View view){
        setNumOp(Calculo.Operation.DIVIDE);
        refreshState();
    }

    public void clickEquals(View view){
        if (state.equals(State.getnum2)) {
            cal.setNum2(Float.parseFloat(display.getText().toString()));
            showResult();
        }
        state = State.getnum1;
        display.setText(Float.toString(cal.getNum1()));
    }

    void setNumOp(Calculo.Operation op) {
        if (state.equals(State.getnum2)){
            try {
                cal.setNum2(Float.parseFloat(display.getText().toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Não é um número válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            try {
                cal.setNum1(Float.parseFloat(display.getText().toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Não é um número válido", Toast.LENGTH_SHORT).show();
            }
            cal.setOp(op);
        }
    }

    private void refreshState() {
        display.setText("");

        if (state.equals(State.getnum1)) {
            state = State.getnum2;
//            Toast.makeText(this, "state = getnum2", Toast.LENGTH_SHORT).show();
        } else if (state.equals(State.getnum2)){
            state = State.showresult;
            showResult();
//            Toast.makeText(this, "state = showresults", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResult() {
        display.setText(Float.toString(cal.getResult()));
        cal.setNum1(cal.getResult());
        cal.setNum2(0);
        state = State.getnum1;
//        Toast.makeText(this, "state = getnum1", Toast.LENGTH_SHORT).show();
    }
}
