---

title: RocketMQ学习笔记-1
date: 2016-10-12
---








## RocketMQ学习笔记-消息有序性保证

Message Order

When DefaultMQPushConsumer is employed, you may decide to consume messages orderly or concurrently.

- Orderly

  Consuming messages orderly means messages are consumed the same order they are sent by producers for each message queue. If you are dealing with scenario that global order is mandatory, make sure the topic you use has only one message queue.

  > **Warn**: If consuming orderly is specified, the maximum concurrency of message consuming is the number of message queues subscribed by the consumer group.

- Concurrently

  When consuming concurrently, maximum concurrency of message consuming is only limited by thread pool specified for each consumer client.

  > **Warn**: Message order is no longer guaranteed in this mode. 

1. 上面大概就是说要保证消息的有序，生产者和消息者之间只用一个消息队列
2. 还有一点需要保证的是：生产者producer发送的消息是有序的，而且要保证发送到同一队列

---

ConsumeOrderlyStatus.SUCCESS（提交），ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT（挂起一会再消费），在此之前还有一个变量ConsumeOrderlyContext context的setAutoCommit()是否自动提交。
当SUSPEND_CURRENT_QUEUE_A_MOMENT时，autoCommit设置为true或者false没有区别，本质跟消费相反，把消息从msgTreeMapTemp转移回msgTreeMap，等待下次消费。

当SUCCESS时，autoCommit设置为true时比设置为false多做了2个动作，consumeRequest.getProcessQueue().commit()和this.defaultMQPushConsumerImpl.getOffsetStore().updateOffset(consumeRequest.getMessageQueue(), commitOffset, false);
ProcessQueue.commit() ：本质是删除msgTreeMapTemp里的消息，msgTreeMapTemp里的消息在上面消费时从msgTreeMap转移过来的。
this.defaultMQPushConsumerImpl.getOffsetStore().updateOffset() ：本质是把拉消息的偏移量更新到本地，然后定时更新到broker。

那么少了这2个动作会怎么样呢，随着消息的消费进行，msgTreeMapTemp里的消息堆积越来越多，消费消息的偏移量一直没有更新到broker导致consumer每次重新启动后都要从头开始重复消费。
就算更新了offset到broker，那么msgTreeMapTemp里的消息堆积呢？不知道这算不算bug。
所以，还是把autoCommit设置为true吧。

---

### 消息顺序发送-生产者

```java
public class Producer {
    public static void main(String[] args) throws IOException {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
 
            producer.setNamesrvAddr("192.168.0.104:9876");
 
            producer.start();
 
            String[] tags = new String[] { "TagA", "TagC", "TagD" };
 
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(date);
            for (int i = 0; i < 10; i++) {
                // 加个时间后缀
                String body = dateStr + " Hello RocketMQ " + i;
                Message msg = new Message("TopicTestjjj", tags[i % tags.length], "KEY" + i, body.getBytes());
 
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        return mqs.get(id);
                    }
                }, 0);//0是队列的下标
 
                System.out.println(sendResult + ", body:" + body);
            }
 
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.in.read();
    }
}
```



### 消息顺序发送-消费者

```java
/**
 * 顺序消息消费，带事务方式（应用可控制Offset什么时候提交）
 */
public class Consumer {
 
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name_3");
        consumer.setNamesrvAddr("192.168.0.104:9876");
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
 
        consumer.subscribe("TopicTestjjj", "TagA || TagC || TagD");
 
        consumer.registerMessageListener(new MessageListenerOrderly() {
 
            Random random = new Random();
 
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
                System.out.print(Thread.currentThread().getName() + " Receive New Messages: " );
                for (MessageExt msg: msgs) {
                    System.out.println(msg + ", content:" + new String(msg.getBody()));
                }
                try {
                    //模拟业务逻辑处理中...
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
 
        consumer.start();
 
        System.out.println("Consumer Started.");
    }
 }
```



