package com.v0ex.spi;

/**
 * SPI服务发现,服务类
 * 当服务的提供者,提供了一种接口的实现后,在包META-INF/services目录里,
 * 同时创建一个以服务接口命名的文件,该文件里是接口的具体实现类,
 * 而当外部程序装载配置这个模块时，就能通过该jar下面META-INFO/services文件里的配置文件找到具体的实现类,
 * 并装载实例化，完成模块的注入
 * @author yifeng
 * @date 17/9/18
 */
public interface DogService {

    void sleep();
}
