## Elasticsearch安装

OSX系统安装

```
brew update
brew install elasticsearch
```

成功以后

```
To have launchd start elasticsearch now and restart at login:
  brew services start elasticsearch
Or, if you don't want/need a background service you can just run:
  elasticsearch
```

检测是否成功运行

```
curl -XGET 'localhost:9200/?pretty'
```

## Kibana安装

OSX系统安装

```
brew install kibana
```

成功后

```
To have launchd start kibana now and restart at login:
  brew services start kibana
Or, if you don't want/need a background service you can just run:
  kibana
```
访问地址
```
http://localhost:5601
```

## 分词器拼音插件安装

```
https://github.com/medcl/elasticsearch-analysis-ik
```
