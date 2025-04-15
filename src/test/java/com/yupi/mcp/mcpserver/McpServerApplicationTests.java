package com.yupi.mcp.mcpserver;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.mcp.mcpserver.dto.ArticlePublishRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishResponse;
import com.yupi.mcp.mcpserver.dto.NewsDTO;
import com.yupi.mcp.mcpserver.dto.NowCoderDTO;
import com.yupi.mcp.mcpserver.properties.WebAuthProperties;
import com.yupi.mcp.mcpserver.service.article.ArticleService;

import com.yupi.mcp.mcpserver.service.article.NowCoderService;
import com.yupi.mcp.mcpserver.service.article.QueryNewsService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;


@SpringBootTest
class McpServerApplicationTests {


    @Qualifier("articleCsdnService")
    @Autowired
    private ArticleService articleCsdnService;
    @Autowired
    @Qualifier("articleJuejinService")
    private ArticleService articleJuejinService;
    @Autowired
    @Qualifier("articleCnblogService")
    private ArticleService articleCnblogService;
    @Autowired
    private WebAuthProperties webAuthProperties;
    @Autowired
    private NowCoderService nowCoderService;
    @Autowired
    private QueryNewsService queryNewsService;


    @Test
    void tes() {
//        NowCoderDTO nowCoderDTO = nowCoderService.crawlExperienceQuestion();
//        System.out.println(nowCoderDTO.getContent());

        NewsDTO content = queryNewsService.getNews();
        System.out.println(content);

    }

    @Test
    void contextLoads() {
        String title = "JUnit 5的框架介绍";
        String description = "面我来用比较口语化的方式和你聊聊JUnit 5的用法，从导包、简单Demo，到注意事项和一些小技巧，咱们一步步说清楚～";
        String content = "下面我来用比较口语化的方式和你聊聊JUnit 5的用法，从导包、简单Demo，到注意事项和一些小技巧，咱们一步步说清楚～\n" +
                "\n" +
                "---\n" +
                "\n" +
                "### 1. 导入依赖（导包）\n" +
                "\n" +
                "首先，如果你是用Maven构建项目，就需要在`pom.xml`里加上JUnit 5的依赖。基本上只需要添加下面这段配置就行（记得scope设为test哦）：\n" +
                "\n" +
                "```xml\n" +
                "<dependency>\n" +
                "  <groupId>org.junit.jupiter</groupId>\n" +
                "  <artifactId>junit-jupiter-engine</artifactId>\n" +
                "  <version>5.8.2</version>\n" +
                "  <scope>test</scope>\n" +
                "</dependency>\n" +
                "```\n" +
                "\n" +
                "这样Maven就会自动下载JUnit 5相关的jar包。除了这个，如果你需要参数化测试，还可以加上`junit-jupiter-params`依赖～  \n" +
                "\uE200cite\uE202turn0search2\uE201\n" +
                "\n" +
                "---\n" +
                "\n" +
                "### 2. 编写Demo\n" +
                "\n" +
                "咱们写个简单的单元测试来验证一个方法。假设你有个类叫`HelloWorld`，它有个方法`sayHello()`返回一个字符串。你可以这么写测试类：\n" +
                "\n" +
                "```java\n" +
                "import org.junit.jupiter.api.*;\n" +
                "\n" +
                "class HelloWorldTest {\n" +
                "\n" +
                "    @BeforeAll\n" +
                "    static void initAll() {\n" +
                "        System.out.println(\"所有测试开始前的初始化...\");\n" +
                "    }\n" +
                "\n" +
                "    @BeforeEach\n" +
                "    void init() {\n" +
                "        System.out.println(\"每个测试前的准备工作...\");\n" +
                "    }\n" +
                "\n" +
                "    @DisplayName(\"测试 sayHello 方法\")\n" +
                "    @Test\n" +
                "    void testSayHello() {\n" +
                "        HelloWorld hw = new HelloWorld();\n" +
                "        String result = hw.sayHello();\n" +
                "        // 使用断言来检查返回结果是否符合预期\n" +
                "        Assertions.assertEquals(\"Hello, world!\", result);\n" +
                "    }\n" +
                "\n" +
                "    @AfterEach\n" +
                "    void tearDown() {\n" +
                "        System.out.println(\"每个测试结束后的清理工作...\");\n" +
                "    }\n" +
                "\n" +
                "    @AfterAll\n" +
                "    static void tearDownAll() {\n" +
                "        System.out.println(\"所有测试结束后的清理工作...\");\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "这里几个注解的意思简单说一下：\n" +
                "- **@BeforeAll** 和 **@AfterAll**：分别在所有测试开始前和结束后只运行一次（记得它们必须是静态方法）。\n" +
                "- **@BeforeEach** 和 **@AfterEach**：每个测试方法执行前后各执行一次。\n" +
                "- **@DisplayName**：可以给测试类或方法自定义一个更友好的名字，这样在测试报告里看起来更清晰。\n" +
                "- **@Test**：标记一个方法为测试方法。  \n" +
                "\uE200cite\uE202turn0search0\uE201  \n" +
                "在JUnit 5里，有个好处就是测试方法和类不用非得写成public，这和之前JUnit 4有点区别，写成包级私有也OK。\n" +
                "\n" +
                "---\n" +
                "\n" +
                "### 3. 注意事项\n" +
                "\n" +
                "在使用JUnit 5时，有几点小细节需要注意：\n" +
                "\n" +
                "- **测试方法必须是无参且返回void**。如果有参数了（除非你用参数化测试），JUnit 5会报错。\n" +
                "- **@BeforeAll 和 @AfterAll方法一定要是static**，否则运行时会出错。\n" +
                "- **测试方法不需要public修饰符**，包级可见就够了。这点可以减少一些不必要的暴露～  \n" +
                "  \uE200cite\uE202turn0search5\uE201\n" +
                "- 如果你不想某个测试执行，可以加上**@Disabled**注解，这样JUnit在跑测试时就会跳过它。\n" +
                "- **尽量写有意义的断言**。使用`Assertions`提供的断言方法（比如`assertEquals`、`assertTrue`等），而不要仅仅依赖打印信息来判断测试结果。\n" +
                "\n" +
                "---\n" +
                "\n" +
                "### 4. 使用技巧\n" +
                "\n" +
                "除了基本用法之外，还有一些小技巧可以帮助你写出更好、更高效的测试代码：\n" +
                "\n" +
                "- **参数化测试**：用`@ParameterizedTest`配合`@ValueSource`、`@CsvSource`等可以让同一个测试方法跑多个数据组合。例如：\n" +
                "\n" +
                "  ```java\n" +
                "  @ParameterizedTest\n" +
                "  @ValueSource(ints = {2, 4, 6})\n" +
                "  void testEvenNumbers(int num) {\n" +
                "      Assertions.assertEquals(0, num % 2);\n" +
                "  }\n" +
                "  ```\n" +
                "  这样就不用写多个类似的方法啦。  \n" +
                "  \uE200cite\uE202turn0search8\uE201\n" +
                "\n" +
                "- **重复测试**：如果需要重复执行某个测试方法，可以用`@RepeatedTest`。例如：\n" +
                "\n" +
                "  ```java\n" +
                "  @RepeatedTest(3)\n" +
                "  void repeatedTest() {\n" +
                "      System.out.println(\"这个测试会执行三次\");\n" +
                "  }\n" +
                "  ```\n" +
                "\n" +
                "- **分组断言**：使用`assertAll`可以把多个断言放在一块执行，这样即使有个别断言失败，也能把所有失败信息都显示出来，方便调试。例如：\n" +
                "\n" +
                "  ```java\n" +
                "  @Test\n" +
                "  void groupAssertions() {\n" +
                "      int[] nums = {0, 1, 2, 3};\n" +
                "      Assertions.assertAll(\"检查数组各项\",\n" +
                "          () -> Assertions.assertEquals(0, nums[0]),\n" +
                "          () -> Assertions.assertEquals(1, nums[1]),\n" +
                "          () -> Assertions.assertEquals(2, nums[2])\n" +
                "      );\n" +
                "  }\n" +
                "  ```\n" +
                "\n" +
                "- **动态测试**：JUnit 5还支持动态生成测试案例，通过`@TestFactory`返回一组`DynamicTest`，这对于测试一些运行时数据生成的场景非常有用。\n" +
                "\n" +
                "- **使用Lambda表达式**：在断言消息里使用Lambda表达式可以延迟构造消息，有助于性能优化，尤其是当构造断言信息比较耗时时。  \n" +
                "  \uE200cite\uE202turn0search4\uE201\n" +
                "\n" +
                "---\n" +
                "\n" +
                "### 小结\n" +
                "\n" +
                "简单来说，JUnit 5让单元测试变得更灵活、更现代化。咱们只需要：\n" +
                "- 在项目中导入JUnit 5依赖（比如用Maven配置）。\n" +
                "- 编写测试类，利用注解（@Test、@BeforeAll、@DisplayName等）来组织测试代码。\n" +
                "- 注意测试方法的格式（无参、void、非public也OK）。\n" +
                "- 掌握参数化测试、重复测试、分组断言等技巧，让测试代码更高效、更易读。\n" +
                "\n" +
                "这样你就能写出既稳健又便于维护的单元测试啦！希望这些讲解对你有帮助～\n" +
                "\n" +
                "---\n" +
                "\n" +
                "参考资料：  \n" +
                "\uE200cite\uE202turn0search0\uE201  \n" +
                "\uE200cite\uE202turn0search8\uE201  \n" +
                "\uE200cite\uE202turn0search9\uE201";
        ArticlePublishRequest articlePublishRequest = new ArticlePublishRequest();
        articlePublishRequest.setTitle(title);
        articlePublishRequest.setDescription(description);
        articlePublishRequest.setContent(content);
        ArticlePublishResponse articlePublishResponse = articleCsdnService.publishArticle(articlePublishRequest);
        System.out.println(JSONUtil.toJsonStr(articlePublishResponse));

    }
}
