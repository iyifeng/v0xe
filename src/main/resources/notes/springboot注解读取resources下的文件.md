### springboot注解读取resources下的文件

```
package com.v0ex.ies.es.serivce;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import sun.misc.IOUtils;

import java.io.IOException;

/**
 * 处理resources文件夹下的静态文件
 *
 * @author zbj
 * @date 17/6/20
 */
@Service
public class ResourcesService {

    @Value(value = "classpath:*.json")
    private Resource resource;

    public String getDairyMappingJson(){
        String result = "";
        try {
            result = new String(IOUtils.readFully(resource.getInputStream(), -1, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

```

