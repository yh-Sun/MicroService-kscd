package com.kscd.mediator.example.components;

import com.kscd.mediator.example.mediator.Mediator;

/**
 * EN: Common component interface.
 *
 * RU: Общий интерфейс компонентов.
 */
public interface Component {
    void setMediator(Mediator mediator);
    String getName();
}
