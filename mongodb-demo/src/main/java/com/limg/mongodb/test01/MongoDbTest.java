package com.limg.mongodb.test01;

import cn.hutool.json.JSONUtil;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

/**
 *
 */
@Slf4j
public class MongoDbTest {

    private static final String DATABASE = "ling";


    public MongoCollection<Document> getDb(String collectionName) {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        // 1.创建链接
        ConnectionString connString = new ConnectionString("mongodb://192.168.186.128:27017/ling");
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient.getDatabase(DATABASE).getCollection(collectionName);
    }

    @Test
    public void insert() {
        String collectionName = "shop";
        MongoCollection<Document> collection = getDb(collectionName);
        Document document = new Document();
        document.append("name", "手机");
        document.append("price", 8000);
        InsertOneResult insertOneResult = collection.insertOne(document);
        System.out.println(JSONUtil.toJsonStr(insertOneResult));
    }

    @Test
    public void update() {
        String collectionName = "shop";
        MongoCollection<Document> collection = getDb(collectionName);
        Bson condition = Filters.and(Filters.eq("name", "手机"));
        Bson update = new Document("$set", new Document().append("price", 7000));
        collection.updateMany(condition, update);
        System.out.println("修改成功");
    }

    @Test
    public void delete() {
        String collectionName = "shop";
        MongoCollection<Document> collection = getDb(collectionName);
        Bson condition = Filters.and(Filters.eq("name", "手机"));
        collection.deleteMany(condition);
        System.out.println("删除成功");
    }

    @Test
    public void findAll() {
        String collectionName = "shop";
        MongoCollection<Document> collection = getDb(collectionName);
        FindIterable<Document> documents = collection.find();
        documents.forEach(item -> {
            System.out.println(item.get("name") + "--" + item.get("price"));
        });
    }

    @Test
    public void findByConditation() {
        String collectionName = "shop";
        MongoCollection<Document> collection = getDb(collectionName);
        Bson condition = Filters.and(Filters.eq("name", "手机"));
        FindIterable<Document> documents = collection.find(condition);
        documents.forEach(item -> {
            System.out.println(item.get("name"));
        });
    }

}
