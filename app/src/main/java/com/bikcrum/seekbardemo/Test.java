package com.bikcrum.seekbardemo;

/**
 * Created by LENEVO on 1/18/2018.
 */

public class Test {

    InterfaceTest interfaceTest = new InterfaceTest() {
        @Override
        public void onClick() {

        }

        @Override
        public void onLongClick() {

        }

        @Override
        public void onPress() {

        }
    };

    Test() {
        Example example = new Example();

        example.setInterface(interfaceTest);
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onLongClick() {

    }

    @Override
    public void onPress() {

    }
}
