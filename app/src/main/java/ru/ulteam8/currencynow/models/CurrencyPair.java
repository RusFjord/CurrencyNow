package ru.ulteam8.currencynow.models;

import android.content.Context;

import ru.ulteam8.currencynow.interfaces.Refs;

public class CurrencyPair implements Refs {

    private Refs firstCurrency;
    private Refs secondCurrency;
    private float coefficient = 1.0f;

    public CurrencyPair(Refs firstCurrency, Refs secondCurrency) {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
    }

    public CurrencyPair(Refs firstCurrency, Refs secondCurrency, float coefficient) {
        this(firstCurrency, secondCurrency);
        this.coefficient = coefficient;
    }

    public Refs getFirstCurrency() {
        return firstCurrency;
    }

    public Refs getSecondCurrency() {
        return secondCurrency;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public float getRatioFirstToSecond(float amount) {
        float result = 1.0f;
        if ((firstCurrency != null) && (secondCurrency != null)) {
            result = amount * coefficient;
        }
        return result;
    }

    public float getRatioSecondToFirst(float amount) {
        float result = 1.0f;
        if ((firstCurrency != null) && (secondCurrency != null) && (coefficient != 0.0f)) {
            result = amount / coefficient;
        }
        return result;
    }

    @Override
    public String getRepresentation(Context context) {
        StringBuilder result = new StringBuilder();
        if (firstCurrency != null) {
            result.append(firstCurrency.getRepresentation(context));
            result.append(" - ");
        }
        if (secondCurrency != null) {
            result.append(secondCurrency.getRepresentation(context));
        }
        return result.toString();
    }
}
