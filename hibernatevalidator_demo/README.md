1. 在需要参数验证的类上必须加@validated注解，否则校验不生效
2. @Valid作用
```java
public class Order {
    @Valid
    private User user;
    
}
```
3. 快速失败模式：在第一个校验不合格立即报错返回
```java
    @Bean
    public Validator validator(){
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }
```
4. 定义开关注解在合适的地方使用快速失败
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FastFailValidated.class)
public @interface EnableFastFailValidated {
}
```

5. 分组校验, 可以根据不同的规则进行分组校验，下列根据数据操作分组
```java
public class ValidationGroups {

    public interface Inset{};
    public interface Update{};
    public interface Delete{};
    public interface Select{};

}
// 使用规则
@NotEmpty(message = "用户名不能为空", groups = {ValidationGroups.Inset.class})
```