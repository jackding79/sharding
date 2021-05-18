package cn.jackding.shardingjdbc.demo;


import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
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

   // static String yamlFile ="E:\\idea\\code\\shardingjdbc\\src\\main\\resources\\application-sharding.yml";
      static  String yamlFile ="E:\\code\\sharding-master\\src\\main\\resources\\application-sharding.yml";
    public static void main(String[] args) throws Exception {
       /* DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(yamlFile));
        String sql = "select * from t_order where user_id = ? and order_id = ?";
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,3);
        ps.setInt(2,12);
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getString("user_id"));
        }*/
       insert();

    }

    public static void insert() throws Exception{
        //TransactionTypeHolder.set(TransactionType.XA);
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(yamlFile));
        String sql = "insert into t_user values(?,?,?,?,?,?)";
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

}
