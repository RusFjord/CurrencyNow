package ru.ulteam8.currencynow.models;

import android.content.Context;

import ru.ulteam8.currencynow.interfaces.CurrencyRefs;

public class CurrencyPair implements CurrencyRefs {

    private CurrencyRefs firstCurrency;
    private CurrencyRefs secondCurrency;
    private float coefficient = 1.0f;

    public CurrencyPair(CurrencyRefs firstCurrency, CurrencyRefs secondCurrency) {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
    }

    public CurrencyPair(CurrencyRefs firstCurrency, CurrencyRefs secondCurrency, float coefficient) {
        this(firstCurrency, secondCurrency);
        this.coefficient = coefficient;
    }

    public CurrencyRefs getFirstCurrency() {
        return firstCurrency;
    }

    public CurrencyRefs getSecondCurrency() {
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

    @Override
    public String getSymbol() {
        StringBuilder result = new StringBuilder();
        if (firstCurrency != null) {
            result.append(firstCurrency.getSymbol());
            result.append(" - ");
        }
        if (secondCurrency != null) {
            result.append(secondCurrency.getSymbol());
        }
        return result.toString();
    }
}
