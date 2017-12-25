package com.jeff.base.proxy;

public class SubjectImpl implements Subject {
    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }

    @Override
    public String sayByeBye() {
        return "Bye Bye beautiful";
    }
}
