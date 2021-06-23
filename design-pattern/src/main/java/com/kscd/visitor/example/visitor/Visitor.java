package com.kscd.visitor.example.visitor;

import com.kscd.visitor.example.shapes.Circle;
import com.kscd.visitor.example.shapes.CompoundShape;
import com.kscd.visitor.example.shapes.Dot;
import com.kscd.visitor.example.shapes.Rectangle;

public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}
