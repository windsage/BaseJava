package com.jeff.base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FruitColor {

    enum Color {RED, GREEN, BLUE}

    Color color() default Color.RED;
}
