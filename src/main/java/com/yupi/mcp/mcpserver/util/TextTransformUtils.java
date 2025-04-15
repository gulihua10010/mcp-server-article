package com.yupi.mcp.mcpserver.util;


import io.micrometer.common.util.StringUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.CoreHtmlNodeRenderer;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.text.TextContentRenderer;

/**
 * 文本转换工具类
 *
 * @author liyupi
 */
public class TextTransformUtils {

    /**
     * markdown 转为文本
     *
     * @param markdown
     * @return
     */
    public static String markdownToText(String markdown) {
        if (StringUtils.isBlank(markdown)) {
            return "";
        }
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        TextContentRenderer textContentRenderer = TextContentRenderer.builder().build();
        return textContentRenderer.render(document);
    }

    /**
     * markdown 转为HTML
     *
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown) {
        if (StringUtils.isBlank(markdown)) {
            return "";
        }
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer textContentRenderer = HtmlRenderer.builder().build();
        return textContentRenderer.render(document);
    }


    /**
     * 自定义 markdown 转纯文本
     *
     * @param markdown markdown
     * @return {@link String }
     */
    public static String markdownToTextCustom(String markdown) {
        if (StringUtils.isBlank(markdown)) {
            return "";
        }
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        TextContentRenderer textContentRenderer = TextContentRenderer
                .builder()
                .nodeRendererFactory(CommonMarkCustomNodeRenderer::new)
                .build();
        return textContentRenderer.render(document);
    }

}
