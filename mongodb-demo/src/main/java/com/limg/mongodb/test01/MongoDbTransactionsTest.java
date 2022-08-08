package com.limg.mongodb.test01;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.*;
import org.bson.Document;


public class MongoDbTransactionsTest {

    public static void main(String[] args) {
        /*
          副本集.
          String uri = "mongodb://mongodb0.example.com:27017,mongodb1.example.com:27017/admin?replicaSet=myRepl";
          分片
          String uri = "mongodb://mongos0.example.com:27017,mongos1.example.com:27017:27017/admin";
         */
        String uri = "mongodb://192.168.254.210:28017,192.168.254.210:28018,192.168.254.210:28019/test?connect=replicaSet&slaveOk=true&replicaSet=rs0";
        final MongoClient client = MongoClients.create(uri);

//        /*
//            Create collections.
//         */
//
//        client.getDatabase("mydb1").getCollection("foo")
//                .withWriteConcern(WriteConcern.MAJORITY).insertOne(new Document("abc", 0));
//        client.getDatabase("mydb2").getCollection("bar")
//                .withWriteConcern(WriteConcern.MAJORITY).insertOne(new Document("xyz", 0));

        /* Step 1: 创建一个session. */

        final ClientSession clientSession = client.startSession();

        /* Step 2: 定义session选项. */

        TransactionOptions txnOptions = TransactionOptions.builder()
                .readPreference(ReadPreference.primary())
                .readConcern(ReadConcern.LOCAL)
                .writeConcern(WriteConcern.MAJORITY)
                .build();

        /* Step 3: session内操作. */

        TransactionBody txnBody = new TransactionBody<String>() {
            public String execute() {
                MongoCollection<Document> coll1 = client.getDatabase("mydb1").getCollection("foo");
                MongoCollection<Document> coll2 = client.getDatabase("mydb2").getCollection("bar");

                coll1.insertOne(clientSession, new Document("abc", 2));
                coll2.insertOne(clientSession, new Document("xyz", 9990));
                System.out.println(1 / 0);
                return "Inserted into collections in different databases";
            }
        };
        try {
            /*
               Step 4: 事务执行，
            */
            clientSession.withTransaction(txnBody, txnOptions);
        } catch (RuntimeException e) {
            // 失败行为
            e.printStackTrace();
        } finally {
            clientSession.close();
        }

    }
}
