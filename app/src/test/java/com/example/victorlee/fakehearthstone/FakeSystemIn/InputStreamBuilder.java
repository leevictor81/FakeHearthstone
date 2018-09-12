package com.example.victorlee.fakehearthstone.FakeSystemIn;

/**
 * Created by Victor Lee on 8/5/2018.
 */

import java.util.LinkedList;
import java.util.Queue;

public class InputStreamBuilder {
    private Queue<String> values = new LinkedList<String>();

    public InputStreamBuilder addInstruction(String value) {
        this.values.add(value);
        return this;
    }

    public StubbedInputStream end() {
        return new StubbedInputStream(values);
    }
}