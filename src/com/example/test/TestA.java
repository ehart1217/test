
package com.example.test;


public class TestA {

    protected void a() throws RuntimeException {

    }

    static public class AChild extends TestA {
        @Override
        protected void a() throws NullPointerException {
            super.a();
        }
    }
}
