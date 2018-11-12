import com.v0ex.spi.DogService;

import java.util.ServiceLoader;

/**
 * SPI服务发现,测试类
 *
 * @author yifeng
 * @date 17/9/18
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<DogService> serviceLoaders = ServiceLoader.load(DogService.class);
        for (DogService dogService : serviceLoaders){
            dogService.sleep();
        }
    }
}
