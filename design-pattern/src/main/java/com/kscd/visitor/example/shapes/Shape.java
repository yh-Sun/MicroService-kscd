package com.kscd.visitor.example.shapes;

import com.kscd.visitor.example.visitor.Visitor;

public interface Shape {
    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);
}
