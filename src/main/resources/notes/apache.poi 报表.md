## apache.poi 报表

### 贵族需求中常用到报表导出功能

1. springboot
2. gradle
3. apache.poi

####build.gradle添加

```
compile group: 'org.apache.poi', name: 'poi', version: '3.15'
```

#### 实例

```java
package com.ai;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zbj on 17/3/21.
 */
public class ExcelTest {

    public static void main(String[] args) throws IOException {
        String[] strings = new String[]{"姓名","年龄"};
        List<Student> list  = new ArrayList<>();
        list.add(new Student("张三","18"));
        list.add(new Student("李四","19"));
        dynamicCreateExcel("学生名单",strings,list);
    }

    public static void dynamicCreateExcel(String title, String[] headers, Collection data) throws IOException {
        FileOutputStream out = new FileOutputStream("workbook.xls");
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(title);
        /* 设置表头 */
        Row titleRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        /* 数据内容 */
        Iterator iterator = data.iterator();
        int index = 1;
        while (iterator.hasNext()){
            Object t = iterator.next();
            List<String> values = getObjectValues(t);
            Row row = sheet.createRow(index);
            for (int i = 0; i < values.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(values.get(i));
            }
            index++;
        }
        workbook.write(out);
        out.close();
    }

    public static List<String> getObjectValues(Object object) {
        List<String> values = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = fields[i].getGenericType().toString();
            Method method = null;
            try {
                method = object.getClass().getMethod("get" + name);
                String value = (String) method.invoke(object);
                values.add(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return values;
    }

    static class Student{

        private String name;

        private String age;

        public Student(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getAge() {
            return age;
        }
    }
}

```

