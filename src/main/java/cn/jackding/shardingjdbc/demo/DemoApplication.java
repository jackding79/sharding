package cn.jackding.shardingjdbc.demo;


import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.transaction.context.TransactionContexts;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;

public class DemoApplication {

    static String yamlFile ="E:\\idea\\code\\shardingsphere\\sharding\\src\\main\\resources\\application-sharding.yml";
    //  static  String yamlFile ="E:\\code\\sharding-master\\src\\main\\resources\\application-sharding.yml";
    public static void main(String[] args) throws Exception {

        insertUser();
    }

    public static void insertOrder() throws Exception{
        //TransactionTypeHolder.set(TransactionType.XA);
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(yamlFile));
        String sql = "insert into t_order values(?,?,?,?,?,?)";
        Connection connection =dataSource.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,765);
        ps.setString(2,"衣服");
        ps.setTime(3, Time.valueOf(LocalTime.now()));
        ps.setDouble(4,22.9);
        ps.setString(5,"1");
        ps.setInt(6,567);
        int num = ps.executeUpdate();
        //connection.rollback();
        connection.commit();
        System.out.println(num);
    }

    public static void insertUser() throws Exception{
        HintManager manager =HintManager.getInstance();
        manager.addDatabaseShardingValue("t_user",3);
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(yamlFile));
        String sql = "insert into t_user values(?,?,?)";
        Connection connection =dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,2);
        ps.setString(2,"jack");
        ps.setInt(3,9);
        ps.executeUpdate();
        connection.commit();
    }
}
