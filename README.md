[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.jsonapi/japison/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.jsonapi/japison)

Japison
=============
A lightweight implementation of JSON API specification(http://jsonapi.org/).

##Maven Repo
```xml
<dependency>
    <groupId>com.github.wnameless.jsonapi</groupId>
    <artifactId>japison</artifactId>
    <version>0.2.0</version>
</dependency>
```

#Quick Start

Static import first
```java
import static com.github.wnameless.jsonapi.JsonApi.*;
```

Example resource
```java
public class Res {
    private Long id;
    private String data;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public Res(Long id, String data) { this.id = id; this.data = data; }
  }
Res res =  new Res();
res.setId(123);
res.setData("abc");
```

Create a resource document
```java
ResourceDocument resourceDoc = resourceDocument(res, "Res", res.getId().toString());
System.out.println(resourceDoc.toJson());
# {"data":{"type":"Res","id":"123","attributes":{"id":123,"data":"abc"}}}
```

Create a resources document
```java
ResourcesDocument resourcesDoc = resourcesDocument(Arrays.asList(res, new Res(456L, "def")), "Res",  r -> r.getId().toString());
System.out.println(resourcesDoc.toJson());
# {"data":[{"type":"Res","id":"123","attributes":{"id":123,"data":"abc"}},{"type":"Res","id":"456","attributes":{"id":456,"data":"def"}}]}
```

Create an errors document
```java
ErrorsDocument errors = errorsDocument();
errors.setErrors(Arrays.asList(error().withStatus("500").withTitle("Oops!").withDetail("Unknown?")));
System.out.println(errors.toJson());
# {"errors":[{"status":"500","title":"Oops!","detail":"Unknown?"}]}
```

Since v0.2.0, users can use ObjectMapperFactory to set their own Jackson ObjectMapper
```java
ObjectMapperFactory.setObjectMapper(customObjectMapper);
```
It will take affect globally within the entire Japison library
