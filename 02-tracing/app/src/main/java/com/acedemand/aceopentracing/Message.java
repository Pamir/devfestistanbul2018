package com.acedemand.aceopentracing;

public class Message {
    private String color;
    private int a;
    private int b;

    public Message(String color, int a, int b) {
        this.color = color;
        this.a = a;
        this.b = b;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
