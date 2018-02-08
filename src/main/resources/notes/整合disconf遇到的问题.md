## 整合disconf遇到的问题

```java
Warning:<i><b>root project 'xxxxx': Web Facets/Artifacts will not be configured properly</b>
Details: org.gradle.api.artifacts.ResolveException: Could not resolve all dependencies for configuration ':runtime'.
Caused by: org.gradle.internal.resolve.ArtifactNotFoundException: Could not find jms.jar (javax.jms:jms:1.1).
Searched in the following locations:
    http://0.0.0.0:18765/nexus/content/groups/public/javax/jms/jms/1.1/jms-1.1.jar</i>
```

引起该问题的原因主要是jar包冲突，解决办法

1. 在zookeeper3.3.6中，使用如下示例解决，

```java
 compile ('eu.medsea.mimeutil:mime-util:2.1.3'){
        exclude group: 'javax.jms', module: 'jms'
        exclude group: 'com.sun.jmx', module: 'jmxri'
        exclude group: 'com.sun.jdmk', module: 'jmxtools'
    }
```
2. 在日志Log4j或者Salf4j的使用过程中，也会出现该问题，解决方案同样是

```java
{
        exclude group: 'javax.jms', module: 'jms'
        exclude group: 'com.sun.jmx', module: 'jmxri'
        exclude group: 'com.sun.jdmk', module: 'jmxtools'
    }
```

3. 还有一种情况就是确实缺少jms的jar，添加jar包即可