package app.ui;

import java.awt.*;
import javax.swing.*;

/**
 * A FlowLayout extension that recalculates height dynamically to wrap grid cards inside JScrollPanes
 */
public class WrapLayout extends FlowLayout {
    public WrapLayout(int align, int hgap, int vgap) {
        super(align, hgap, vgap);
    }

    @Override
    public Dimension preferredLayoutSize(Container target) {
        return layoutSize(target, true);
    }

    @Override
    public Dimension minimumLayoutSize(Container target) {
        return layoutSize(target, false);
    }

    private Dimension layoutSize(Container target, boolean preferred) {
        synchronized (target.getTreeLock()) {
            int targetWidth = target.getSize().width;
            if (targetWidth == 0) targetWidth = Integer.MAX_VALUE;

            int hgap = getHgap();
            int vgap = getVgap();
            int maxwidth = targetWidth - (target.getInsets().left + target.getInsets().right + hgap * 2);

            int nmembers = target.getComponentCount();
            int x = 0, y = target.getInsets().top + vgap;
            int rowHeight = 0;

            for (int i = 0; i < nmembers; i++) {
                Component m = target.getComponent(i);
                if (m.isVisible()) {
                    Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();
                    if ((x == 0) || (x + d.width <= maxwidth)) {
                        if (x > 0) x += hgap;
                        x += d.width;
                        rowHeight = Math.max(rowHeight, d.height);
                    } else {
                        x = d.width;
                        y += vgap + rowHeight;
                        rowHeight = d.height;
                    }
                }
            }
            y += vgap + rowHeight + target.getInsets().bottom;
            return new Dimension(targetWidth, y);
        }
    }
}
