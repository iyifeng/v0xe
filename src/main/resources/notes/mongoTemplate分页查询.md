## mongoTemplate分页查询

```java
package com.ai.kara.openapi.service.mongo.impl;

import com.ai.kara.domain.File;
import com.ai.kara.message.base.BaseMessage;
import com.ai.kara.openapi.service.mongo.IImMessageFileMongoSV;
import com.ai.kara.openapi.service.mongo.utils.MongoDataPageable;
import com.ai.kara.openapi.service.mongo.utils.PageModel;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zbj on 16/11/23.
 */
@Service
public class IImMessageFileMongoSV implements IImMessageFileMongoSV {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BaseMessage findByMessageId(String messageId) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(messageId)),
                BaseMessage.class, "imMessage");
    }

    @Override
    public BaseMessage findByChannel(String channelId) {
        return mongoTemplate.findOne(new Query(Criteria.where("channel").is(channelId)),
                BaseMessage.class);
    }

    @Override
    public BaseMessage findByTypeAndChannel(String type, String channelId) {
        return mongoTemplate.findOne(
                new Query(Criteria.where("type").is(type).and("channel").is(channelId)),
                BaseMessage.class, "imMessage");
    }

    @Override
    public PageModel<BaseMessage> getImMessages(PageModel<BaseMessage> page, DBObject queryObject) {
        Query query = new BasicQuery(queryObject);
        // 查询总数
        int count = (int) mongoTemplate.count(query, BaseMessage.class);
        page.setRowCount(count);

        // 排序
        query.with(new Sort(Sort.Direction.ASC, "id"));
        query.skip(page.getSkip()).limit(page.getPageSize());
        List<BaseMessage> datas = mongoTemplate.find(query, BaseMessage.class);
        page.setDatas(datas);
        return page;
    }

    @Override
    public List<BaseMessage> findMessageByPage(int pageNo, int pageSize) {
        int startNum = (pageNo - 1) * pageSize;
        return mongoTemplate.find(new Query().skip(startNum).limit(pageSize), BaseMessage.class);
    }

    @Override
    public Page<BaseMessage> channelFilesQuery(Integer pageNum, Integer pageSize,
            String channelId) {
        MongoDataPageable pageable = new MongoDataPageable();
        Query query = new Query(
                Criteria.where("channel").is(channelId).and("type").in("file", "image"));
        List<Sort.Order> orders = new ArrayList<Sort.Order>(); // 排序
        orders.add(new Sort.Order(Sort.Direction.DESC, "ts"));
        Sort sort = new Sort(orders);

        // 开始页
        pageable.setPageNo(pageNum);
        // 每页条数
        pageable.setPageSize(pageSize);
        // 排序
        pageable.setSort(sort);
        // 查询出一共的条数
        Long count = mongoTemplate.count(query, BaseMessage.class);
        // 查询
        List<BaseMessage> list = mongoTemplate.find(query.with(pageable), BaseMessage.class);
        // 将集合与分页结果封装
        Page<BaseMessage> pagelist = new PageImpl<BaseMessage>(list, pageable, count);

        return pagelist;
    }

    @Override
    public Page<BaseMessage> myFilesQuery(Integer pageNum, Integer pageSize, String channelId,
            String accountId) {
        MongoDataPageable pageable = new MongoDataPageable();
        Query query = new Query(Criteria.where("channel").is(channelId).and("user").is(accountId)
                .and("type").in("file", "image"));
        List<Sort.Order> orders = new ArrayList<Sort.Order>(); // 排序
        orders.add(new Sort.Order(Sort.Direction.DESC, "ts"));
        Sort sort = new Sort(orders);
        // 开始页
        pageable.setPageNo(pageNum);
        // 每页条数
        pageable.setPageSize(pageSize);
        // 排序
        pageable.setSort(sort);
        // 查询出一共的条数
        Long count = mongoTemplate.count(query, BaseMessage.class);
        // 查询
        List<BaseMessage> list = mongoTemplate.find(query.with(pageable), BaseMessage.class);
        // 将集合与分页结果封装
        Page<BaseMessage> pagelist = new PageImpl<BaseMessage>(list, pageable, count);

        return pagelist;
    }

    @Override
    public void createCollectionsName(String collectionName){
        if (mongoTemplate.collectionExists(collectionName)){
            return;
        }
        mongoTemplate.createCollection(collectionName);
    }

    @Override
    public void insertFileData(File file,String collectionName) {
        if (mongoTemplate.collectionExists(collectionName)){
            mongoTemplate.insert(file,collectionName);
        }
    }

    public void addFileChannels(String channelId){
        Query query = new Query(Criteria.where("id").is("E41F1BA999114BCE848CA1F25D53944C"));
        Update update = new Update();
        update.addToSet("channels",channelId);
        WriteResult writeResult = mongoTemplate.upsert(query,update,File.class,"im_image");
        System.out.println("=========================");
        System.out.println(writeResult.toString());
    }
}

```



-----

```java
package com.ai.kara.openapi.service.mongo.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by zbj on 16/11/23.
 */
public class MongoDataPageable implements Serializable,Pageable{

    private static final long serialVersionUID = 1L;

    // 当前页
    private Integer pageNo = 1;

    // 当前页面条数
    private Integer pageSize = 10;

    // 排序条件
    private Sort sort;

    @Override
    public int getPageNumber() {
        return getPageNo();
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return (getPageNumber() - 1) * getPageSize();
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public void setSort(Sort sort) {
        this.sort = sort;
    }
}

```

---

```
package com.ai.kara.openapi.service.mongo.utils;

import java.util.List;

/**
 * Created by zbj on 16/11/23.
 */
public class PageModel<T> {
    //结果集
    private List<T> datas;

    //查询记录数
    private int rowCount;

    //每页多少条数据
    private int pageSize=20;

    //第几页
    private int pageNo=1;

    //跳过几条数
    private int skip=0;

    /**
     * 总页数
     * @return
     */
    public int getTotalPages(){
        return(rowCount+pageSize-1)/pageSize;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }


    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}

```