1.  基本查询：
    构造查询数据。
    > db.test.findOne()
    {
         "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"),
         "name" : "stephen",
         "age" : 35,
         "genda" : "male",
         "email" : "stephen@hotmail.com"
    }
 
    --多条件查询。下面的示例等同于SQL语句的where name = "stephen" and age = 35
    > db.test.find({"name":"stephen","age":35})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35, "genda" : "male", "email" : "stephen@hotmail.com" }
 
    --返回指定的文档键值对。下面的示例将只是返回name和age键值对。
    > db.test.find({}, {"name":1,"age":1})
   { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35 }

 

    --指定不返回的文档键值对。下面的示例将返回除name之外的所有键值对。
    > db.test.find({}, {"name":0})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "age" : 35, "genda" : "male", "email" : "stephen@hotmail.com" }
 
2.  查询条件：
    MongoDB提供了一组比较操作符：$lt/$lte/$gt/$gte/$ne，依次等价于</<=/>/>=/!=。
    --下面的示例返回符合条件age >= 18 && age <= 40的文档。
    > db.test.find({"age":{"$gte":18, "$lte":40}})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35,"genda" : "male", "email" : "stephen@hotmail.com" }
 
    --下面的示例返回条件符合name != "stephen1"
    > db.test.find({"name":{"$ne":"stephen1"}})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35,"genda" : "male", "email" : "stephen@hotmail.com" }
 
    --$in等同于SQL中的in，下面的示例等同于SQL中的in ("stephen","stephen1")
    > db.test.find({"name":{"$in":["stephen","stephen1"]}})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35,"genda" : "male", "email" : "stephen@hotmail.com" }  
 
    --和SQL不同的是，MongoDB的in list中的数据可以是不同类型。这种情况可用于不同类型的别名场景。
    > db.test.find({"name":{"$in":["stephen",123]}})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35,"genda" : "male", "email" : "stephen@hotmail.com" } 
 
    --$nin等同于SQL中的not in，同时也是$in的取反。如：
    > db.test.find({"name":{"$nin":["stephen2","stephen1"]}})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35,"genda" : "male", "email" : "stephen@hotmail.com" }
 
    --$or等同于SQL中的or，$or所针对的条件被放到一个数组中，每个数组元素表示or的一个条件。
    --下面的示例等同于name = "stephen1" or age = 35
    > db.test.find({"$or": [{"name":"stephen1"}, {"age":35}]})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35,"genda" : "male", "email" : "stephen@hotmail.com" } 
 
    --下面的示例演示了如何混合使用$or和$in。
    > db.test.find({"$or": [{"name":{"$in":["stephen","stephen1"]}}, {"age":36}]})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35,"genda" : "male", "email" : "stephen@hotmail.com" } 
 
    --$not表示取反，等同于SQL中的not。
    > db.test.find({"name": {"$not": {"$in":["stephen2","stephen1"]}}})
    { "_id" : ObjectId("4fd58ecbb9ac507e96276f1a"), "name" : "stephen", "age" : 35,"genda" : "male", "email" : "stephen@hotmail.com" }

 

3.  null数据类型的查询：
    --在进行值为null数据的查询时，所有值为null，以及不包含指定键的文档均会被检索出来。
    > db.test.find({"x":null})
    { "_id" : ObjectId("4fd59d30b9ac507e96276f1b"), "x" : null }
    { "_id" : ObjectId("4fd59d49b9ac507e96276f1c"), "y" : 1 }
 
    --需要将null作为数组中的一个元素进行相等性判断，即便这个数组中只有一个元素。
    --再有就是通过$exists判断指定键是否存在。
    > db.test.find({"x": {"$in": [null], "$exists":true}})
    { "_id" : ObjectId("4fd59d30b9ac507e96276f1b"), "x" : null }
 
4.  正则查询：
    --MongoDB中使用了Perl规则的正则语法。如：
    > db.test.find()
    { "_id" : ObjectId("4fd59ed7b9ac507e96276f1d"), "name" : "stephen" }
    { "_id" : ObjectId("4fd59edbb9ac507e96276f1e"), "name" : "stephen1" }
    --i表示忽略大小写
    > db.test.find({"name":/stephen?/i})
    { "_id" : ObjectId("4fd59ed7b9ac507e96276f1d"), "name" : "stephen" }
    { "_id" : ObjectId("4fd59edbb9ac507e96276f1e"), "name" : "stephen1" } 
 
5.  数组数据查询：
    --基于数组的查找。
    > db.test.find()
    { "_id" : ObjectId("4fd5a177b9ac507e96276f1f"), "fruit" : [ "apple", "banana", "peach" ] }
    { "_id" : ObjectId("4fd5a18cb9ac507e96276f20"), "fruit" : [ "apple", "kumquat","orange" ] }
    { "_id" : ObjectId("4fd5a1f0b9ac507e96276f21"), "fruit" : [ "cherry", "banana","apple" ] }
    --数组中所有包含banana的文档都会被检索出来。
    > db.test.find({"fruit":"banana"})
    { "_id" : ObjectId("4fd5a177b9ac507e96276f1f"), "fruit" : [ "apple", "banana", "peach" ] }
    { "_id" : ObjectId("4fd5a1f0b9ac507e96276f21"), "fruit" : [ "cherry", "banana","apple" ] }
    --检索数组中需要包含多个元素的情况，这里使用$all。下面的示例中，数组中必须同时包含apple和banana，但是他们的顺序无关紧要。
    > db.test.find({"fruit": {"$all": ["banana","apple"]}})
    { "_id" : ObjectId("4fd5a177b9ac507e96276f1f"), "fruit" : [ "apple", "banana", "peach" ] }
    { "_id" : ObjectId("4fd5a1f0b9ac507e96276f21"), "fruit" : [ "cherry", "banana", "apple" ] } 
    --下面的示例表示精确匹配，即被检索出来的文档，fruit值中的数组数据必须和查询条件完全匹配，即不能多，也不能少，顺序也必须保持一致。
    > db.test.find({"fruit":["apple","banana","peach"]})
    { "_id" : ObjectId("4fd5a177b9ac507e96276f1f"), "fruit" : [ "apple", "banana", peach" ] } 
    --下面的示例将匹配数组中指定下标元素的值。数组的起始下标是0。
    > db.test.find({"fruit.2":"peach"})
    { "_id" : ObjectId("4fd5a177b9ac507e96276f1f"), "fruit" : [ "apple", "banana", peach" ] } 
    --可以通过$size获取数组的长度，但是$size不能和比较操作符联合使用。
    > db.test.find({"fruit": {$size : 3}})
    { "_id" : ObjectId("4fd5a177b9ac507e96276f1f"), "fruit" : [ "apple", "banana", "peach" ] }
    { "_id" : ObjectId("4fd5a18cb9ac507e96276f20"), "fruit" : [ "apple", "kumquat","orange" ] }
    { "_id" : ObjectId("4fd5a1f0b9ac507e96276f21"), "fruit" : [ "cherry", "banana","apple" ] } 
    --如果需要检索size > n的结果，不能直接使用$size，只能是添加一个额外的键表示数据中的元素数据，在操作数据中的元素时，需要同时更新size键的值。
    --为后面的实验构造数据。
    > db.test.update({}, {"$set": {"size":3}},false,true)
    > db.test.find()
    { "_id" : ObjectId("4fd5a18cb9ac507e96276f20"), "fruit" : [ "apple", "kumquat", "orange" ], "size" : 3 }
    { "_id" : ObjectId("4fd5a1f0b9ac507e96276f21"), "fruit" : [ "cherry", "banana", "apple" ], "size" : 3 } 
    --每次添加一个新元素，都要原子性的自增size一次。
    > test.update({},{"$push": {"fruit":"strawberry"},"$inc":{"size":1}},false,true)
    > db.test.find()
    { "_id" : ObjectId("4fd5a18cb9ac507e96276f20"), "fruit" : [ "apple", "kumquat", "orange", "strawberry" ], "size" : 4 }
    { "_id" : ObjectId("4fd5a1f0b9ac507e96276f21"), "fruit" : [ "cherry", "banana", "apple", "strawberry" ], "size" : 4 }
    --通过$slice返回数组中的部分数据。"$slice":2表示数组中的前两个元素。
    > db.test.find({},{"fruit": {"$slice":2}, "size":0})
    { "_id" : ObjectId("4fd5a18cb9ac507e96276f20"), "fruit" : [ "apple", "kumquat" ]}
    { "_id" : ObjectId("4fd5a1f0b9ac507e96276f21"), "fruit" : [ "cherry", "banana" ]} 
    --通过$slice返回数组中的部分数据。"$slice":-2表示数组中的后两个元素。
    > db.test.find({},{"fruit": {"$slice":-2}, "size":0})
    { "_id" : ObjectId("4fd5a18cb9ac507e96276f20"), "fruit" : [ "orange", "strawberry" ] }
    { "_id" : ObjectId("4fd5a1f0b9ac507e96276f21"), "fruit" : [ "apple", "strawberry" ] }
    --$slice : [2,1]，表示从第二个2元素开始取1个，如果获取数量大于2后面的元素数量，则取后面的全部数据。
    > db.test.find({},{"fruit": {"$slice":[2,1]}, "size":0})
    { "_id" : ObjectId("4fd5a18cb9ac507e96276f20"), "fruit" : [ "orange" ] }
    { "_id" : ObjectId("4fd5a1f0b9ac507e96276f21"), "fruit" : [ "apple" ] }
 
6.  内嵌文档查询：
    --为后面的示例构造测试数据。
    > db.test.find()
    { "_id" : ObjectId("4fd5ada3b9ac507e96276f22"), "name" : { "first" : "Joe", "last" : "He" }, "age" : 45 }
    --当嵌入式文档为数组时，需要$elemMatch操作符来帮助定位某一个元素匹配的情况，否则嵌入式文件将进行全部的匹配。
    --即检索时需要将所有元素都列出来作为查询条件方可。
    > db.test.findOne()
    {
         "_id" : ObjectId("4fd5af76b9ac507e96276f23"),
         "comments" : [
                 {
                         "author" : "joe",
                         "score" : 3
                 },
                 {
                         "author" : "mary",
                         "score" : 6
                 }
         ]
    }
    > db.test.find({"comments": {"$elemMatch": {"author":"joe","score":{"$gte":3}}}}
    { "_id" : ObjectId("4fd5af76b9ac507e96276f23"), "comments" : [ { "author" : "joe", "score" : 3 }, { "author" : "mary", "score" : 6 } ] }
 
7.  游标：
    数据库使用游标来返回find()的执行结果，客户端对游标可以进行有效的控制，如：限定结果集的数量、跳过部分结果、基于任意键的任意方向的排序等。
    下面的例子将用于准备测试数据。
    > db.testtable.remove()
    > for (i = 0; i < 10; ++i) {
    ... db.testtable.insert({x:i})
    ... }
    我们可以通过cursor提供的hasNext()方法判断是否还有未读取的数据，再通过next()方法读取结果集中的下一个文档。如：
    > var c = db.testtable.find()
    > while (c.hasNext()) {
    ... print(c.next().x)
    ... }
    0
    1
    2
    3
    4
    5
    6
    7
    8
    9
    当调用find()的时候，shell并不立即查询数据库，而是等待真正开始要求获得结果的时候才发送查询，这样在执行之前可以给查询附加额外的选项。几乎所有的游标方法都返回本身，因此可以像下面这样将游标的方法链式组合起来。如：
    > var c1 = db.testtable.find().sort({"x":1}).limit(1).skip(4);
    > var c2 = db.testtable.find().limit(1).sort({"x":1}).skip(4);
    > var c3 = db.testtable.find().skip(4).limit(1).sort({"x":1});
    此时，查询并未执行，所有这些函数都是在构造查询，当执行下面的语句时，查询将被真正执行，
    > c.hasNext()
    查询被发送到服务器，MongoDB服务器每次将返回一批数据，当本批被全部迭代后再从服务器读取下一批数据，直至查询结果需要的数据被全部迭代。
 
    对于上面的示例，limit(1)表示输出结果仅为一个，如果小于1，则不输出，即limit(n)函数限定的是最多输出结果。skip(4)表示跳过查询结果中的前4个文档，如果结果小于4，则不会返回任何文档。sort({"x":1})用于设定排序条件，即按照x键以升序(1)的方式排序，如果需要降序排序可以改为：sort({"x":-1})。sort也可以支持多键排序，如：sort({username:1, age:-1})即先按照username进行升序排序，如果username的值相同，再以age键进行降序排序。这里需要指出的是，如果skip过多的文档，将会导致性能问题。 