package com.example.se.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class datasourceConfig {
    /**
     * Connect SQL server database with this project
     * @return
     * Build data source
     */
    @Bean
    public DataSource dataSource(){
        //Port: 1433
        //Database: SE
        //SQL server username: sa
        //SQL server password: 123
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url("jdbc:sqlserver://localhost:1433;databaseName=SE;trustServerCertificate=true;");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("nhan132134@");
        return dataSourceBuilder.build();
    }

}
