package ru.ulteam8.currencynow.interfaces;

public interface OnCompletePublisher {
    void addListener(OnCompleteListener listener);
    void removeListener(OnCompleteListener listener);
    void notifyListener();
}
