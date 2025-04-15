package com.yupi.mcp.mcpserver.util;

import cn.hutool.http.HtmlUtil;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.BulletList;
import org.commonmark.node.Code;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.renderer.text.CoreTextContentNodeRenderer;
import org.commonmark.renderer.text.TextContentNodeRendererContext;
import org.commonmark.renderer.text.TextContentWriter;

/**
 * commonmark 自定义节点渲染器
 * 目前已自定义了链接（只保留[]的内容）、图片（移除）、引用（只保留内容）
 *
 * @author cq
 * @since 2024/06/05
 */
public class CommonMarkCustomNodeRenderer extends CoreTextContentNodeRenderer {
    protected final TextContentNodeRendererContext context;
    private final TextContentWriter textContent;

    public CommonMarkCustomNodeRenderer(TextContentNodeRendererContext context) {
        super(context);
        this.context = context;
        this.textContent = context.getWriter();
    }

    /**
     * 处理引用（只保留内容）
     *
     * @param blockQuote 引用
     */
    @Override
    public void visit(BlockQuote blockQuote) {
        this.visitChildren(blockQuote);
        if (this.context.stripNewlines()) {
            if (blockQuote.getNext() != null) {
                this.textContent.whitespace();
            }
        } else if (blockQuote.getNext() != null) {
            this.textContent.line();
        }
    }

    /**
     * 处理链接（只保留[]中的内容）
     *
     * @param link 链接
     */
    @Override
    public void visit(Link link) {
        if (link.getFirstChild() != null) {
            this.visitChildren(link);
        }
    }

    /**
     * 图片直接丢弃
     *
     * @param image 图像
     */
    @Override
    public void visit(Image image) {
    }

    /**
     * 处理 html 内联标签（只保留文字，丢弃标签）
     *
     * @param htmlInline HTML 内联
     */
    @Override
    public void visit(HtmlInline htmlInline) {
        String text = HtmlUtil.cleanHtmlTag(htmlInline.getLiteral());
        if (this.context.stripNewlines()) {
            this.textContent.writeStripped(text);
        } else {
            this.textContent.write(text);
        }
    }

    /**
     * 处理 HTML 块标签（只保留文字，丢弃标签）
     *
     * @param htmlBlock HTML 块
     */
    @Override
    public void visit(HtmlBlock htmlBlock) {
        String text = HtmlUtil.cleanHtmlTag(htmlBlock.getLiteral());
        if (this.context.stripNewlines()) {
            this.textContent.writeStripped(text);
        } else {
            this.textContent.write(text);
        }
    }

    /**
     * 处理代码块（直接丢弃）
     *
     * @param fencedCodeBlock 代码块
     */
    @Override
    public void visit(FencedCodeBlock fencedCodeBlock) {
    }

    @Override
    public void visit(Code code) {
        textContent.write(code.getLiteral());
    }

    @Override
    public void visit(BulletList bulletList) {
        bulletList.setBulletMarker(' ');
        super.visit(bulletList);
    }
}