# springboot_kothlin

# 环境搭建

1. idea创建maven工程，在pom中引入springboot2.0、kothlin等依赖
```
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.0.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.6</java.version>
		<kotlin.version>1.2.21</kotlin.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.module</groupId>
			<artifactId>jackson-module-kotlin</artifactId>
		</dependency>
		<dependency>
			<groupId>org.jetbrains.kotlin</groupId>
			<artifactId>kotlin-stdlib-jdk8</artifactId>
		</dependency>
		<dependency>
			<groupId>org.jetbrains.kotlin</groupId>
			<artifactId>kotlin-reflect</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency>
	</dependencies>
```

2. 其次，引入kothlin maven插件
```
        <build>
    		<sourceDirectory>src/main/kotlin</sourceDirectory>
    		<testSourceDirectory>src/test/kotlin</testSourceDirectory>
    		<plugins>

    			<plugin>
    				<artifactId>kotlin-maven-plugin</artifactId>
                    <version>${kotlin.version}</version>
                    <executions>
                        <execution>
                            <id>compile</id>
                            <phase>compile</phase>
                            <goals>
                                <goal>compile</goal>
                            </goals>
                        </execution>
                        <execution>
                            <id>test-compile</id>
                            <phase>test-compile</phase>
                            <goals>
                                <goal>test-compile</goal>
                            </goals>
                        </execution>
                    </executions>
                    <groupId>org.jetbrains.kotlin</groupId>
    				<configuration>
    					<compilerPlugins>
    						<!-- Or "spring" for the Spring support -->
    						<plugin>all-open</plugin>
    						<plugin>spring</plugin>
    						<plugin>jpa</plugin>
    					</compilerPlugins>

    					<pluginOptions>
    						<option>all-open:annotation=com.my.Annotation</option>
    						<option>all-open:annotation=com.their.AnotherAnnotation</option>
    					</pluginOptions>
    				</configuration>
    				<dependencies>
    					<dependency>
    						<groupId>org.jetbrains.kotlin</groupId>
    						<artifactId>kotlin-maven-allopen</artifactId>
    						<version>${kotlin.version}</version>
    					</dependency>
    				</dependencies>
    			</plugin>

    			<plugin>
    				<groupId>org.springframework.boot</groupId>
    				<artifactId>spring-boot-maven-plugin</artifactId>
    			</plugin>
    		</plugins>
    	</build>
```

3.创建主类SpringBootKothlinApplication.kit
```

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SpringbootKothlinApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringbootKothlinApplication::class.javaObjectType, *args)
}
```

启动kothlin工程，如无报错，则基本环境搭建完毕。 

### ps: 也可以使用脚手架工程创建基本的开发环境（https://start.spring.io/），语言选择kothlin.

# 整合spring-data-jpa

1.在pom中添加mysql驱动、数据源、jpa依赖
```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.0.28</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
```
2. 创建application.yml文件
```
server:
  port: 1988

spring:
  application:
    name: springboot
  datasource:
    url: jdbc:mysql://rm-bp17330s3si44dsz7bo.mysql.rds.aliyuncs.com:3306/yanggy
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: Ygy250725
    initialSize: 100
    minIdle: 1
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    type: com.alibaba.druid.pool.DruidDataSource
  jpa:
    hibernate:
      ddl-auto: update
```
至此，jpa整合完毕。


下面进行简单的业务开发

1. 创建model

```
import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "k_users")
data class Users(  @Id
                   @GenericGenerator(name="systemUUID",strategy="uuid")
                   @GeneratedValue(generator="systemUUID")
                   @Column(name = "id", insertable = true, updatable = true, nullable = false)
                   var id: String?) : Serializable{

    @Column(name="name")
    var name: String

    @Column(name="age")
    var age : Int

    init {
        this.name = ""
        this.age = 0
    }
    //default constructor
    constructor() : this("") {

    }
}
```

2. 创建repository
```import org.hibernate.annotations.GenericGenerator
   import java.io.Serializable
   import javax.persistence.*

   /**
    * Created by yangguiyun on 2018/3/14.
    */

   @Entity
   @Table(name = "k_users")
   data class Users(  @Id
                      @GenericGenerator(name="systemUUID",strategy="uuid")
                      @GeneratedValue(generator="systemUUID")
                      @Column(name = "id", insertable = true, updatable = true, nullable = false)
                      var id: String?) : Serializable{

       @Column(name="name")
       var name: String

       @Column(name="age")
       var age : Int

       init {
           this.name = ""
           this.age = 0
       }
       //default constructor
       constructor() : this("") {

       }
   }

```
3. 创建controller
```
@RestController
@RequestMapping("/user")
open class UserController {
    @Autowired
    lateinit  var userRepository : UserRepository

    @PostMapping(value = "/addUser")
    fun save(@RequestBody user : Users) : Users {

        return userRepository.save(user)
    }
}
```

启动项，利用postman测试项目是否启动成功。
