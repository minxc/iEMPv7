package org.minxc.emp.system.excel.editor.font;

import org.minxc.emp.system.excel.editor.IFontEditor;
import org.minxc.emp.system.excel.style.font.Font;

/**
 * 实现一些常用的字体<br/>
 * 该类用于设置斜体
 *
 * @author zxh
 */
public class ItalicFontEditor implements IFontEditor {

    public void updateFont(Font font) {
        font.italic(true);
    }

}
