1. 集成lombok注意
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <encoding>UTF-8</encoding>
        <annotationProcessorPaths>
            <path>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>${org.mapstruct.version}</version>
            </path>
            <path>
                <!-- Lombok 在编译时会通过这个插件生成代码 -->
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

2. 如何复用其它Mapper的实现
```java
    CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);
    
    @Mapping(source = "carList", target = "cars", qualifiedByName = "mapCar")
    PersonDTO personToPersonDTO(Person person, List<Car> carList);

    @Named("mapCar")
    default CarDTO mapCar(Car car){
        return CAR_MAPPER.carToCarDTO(car);
    }
```

3. 不使用spring方式注入
4. 使用defaultExpression="java(defaultId())"配合default函数避免多次使用qualifiedByName报错
```java
    default String defaultId(){
        return UUID.randomUUID().toString();
    }
```
5. 前置与后置处理方法可以校验数据，初始化
6. 更改配置后使用mvn clean重新生成文件