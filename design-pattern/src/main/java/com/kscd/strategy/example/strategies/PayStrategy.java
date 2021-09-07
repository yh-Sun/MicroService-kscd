package com.kscd.strategy.example.strategies;

/**
 * EN: Common interface for all strategies.
 *
 * RU: Общий интерфейс всех стратегий.
 *
 * 通用的支付方法接口
 */
public interface PayStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}

