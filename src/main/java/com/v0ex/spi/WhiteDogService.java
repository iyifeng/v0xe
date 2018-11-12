package com.v0ex.spi;

/**
 * SPI服务发现-具体实现类
 * @author yifeng
 * @date 17/9/18
 */
public class WhiteDogService implements DogService{

    @Override
    public void sleep() {
        System.out.println("White Dog Sleep ...");
    }
}
