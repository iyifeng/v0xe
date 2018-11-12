package com.v0ex.spi;

/**
 * SPI服务发现
 * @author yifeng
 * @date 17/9/18
 */
public class BlackDogService implements DogService{

    @Override
    public void sleep() {
        System.out.println("Black Dog Sleep ...");
    }
}
