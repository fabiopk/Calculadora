package com.example.fbio.calculadora;

public class Calculo {

    public enum Operation {
        PLUS, MINUS, TIMES, DIVIDE
    }

    public float getNum1() {
        return num1;
    }

    public void setNum1(float num1) {
        this.num1 = num1;
    }

    public float getNum2() {
        return num2;
    }

    public void setNum2(float num2) {
        this.num2 = num2;
    }

    public Operation getOp() {
        return op;
    }

    public void setOp(Operation op) {
        this.op = op;
    }

    private float num1;
    private float num2;

    private Operation op;

    public Calculo() {
        this.num1 = 0;
        this.num2 = 0;
        this.op = Operation.PLUS;
    }

    public Calculo(float num1, float num2, Operation op) {
        this.num1 = num1;
        this.num2 = num2;
        this.op = op;
    }

    float getResult(){

        switch (op){
            case PLUS:
                return num1+num2;
            case MINUS:
                return num1-num2;
            case TIMES:
                return num1*num2;
            case DIVIDE:
                return num1/num2;
        }
        return 0;
    }

    public void clearAll(){
        num1 = 0;
        num2 = 0;
    }
}
