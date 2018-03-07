package tsp;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JComponent;

class MyCanvas extends JComponent {
    ArrayList<Vertex> vList;
    int gen;
    public MyCanvas(ArrayList<Vertex> t) {
        this.vList = t;
        //this.gen = gen;
    }
    public MyCanvas(ArrayList<Vertex> t, int gen) {
        this.vList = t;
        this.gen = gen;
    }
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,530,530);
        g.setColor(Color.GREEN);

        g.drawString("Generation: " + this.gen, 0, 510);
        g.setColor(Color.BLACK);


        int vListLastIndex = vList.size()-1;
        g.setFont(g.getFont().deriveFont(12f));

        g.setColor(Color.BLUE);


        g.drawString("("+(int)vList.get(0).x + ", " + (int)vList.get(0).y+ ")", (int)vList.get(0).x, (int)vList.get(0).y);


        g.drawOval(vList.get(0).x,(int)vList.get(0).y,5,5);
        g.fillOval(vList.get(0).x,(int)vList.get(0).y,5,5);
        g.drawLine(vList.get(0).x,vList.get(0).y, vList.get(0+1).x,vList.get(0+1).y);


        g.setColor(Color.BLACK);

        for(int a=1;a< vListLastIndex;a++) {
            if(vList.get(a).numberOfOders==0)
                g.drawString("("+vList.get(a).x + ", " + vList.get(a).y+"cID:"+ vList.get(a).customerId+  ")", vList.get(a).x, vList.get(a).y);
            else
                g.drawString("("+vList.get(a).x + ", " + vList.get(a).y+"cID:"+ vList.get(a).customerId+"#orders:"+ vList.get(a).numberOfOders+   ")", vList.get(a).x, vList.get(a).y);

            if(vList.get(a).numberOfOders>0)
                g.setColor(Color.MAGENTA);

            g.drawOval(vList.get(a).x,vList.get(a).y,5,5);
            g.fillOval(vList.get(a).x,vList.get(a).y,5,5);

            if(vList.get(a).numberOfOders>0)
                g.setColor(Color.BLACK);

            g.drawLine(vList.get(a).x,vList.get(a).y, vList.get(a+1).x,vList.get(a+1).y);
        }
        //g.setColor(Color.RED);
        if(vList.get(vListLastIndex).numberOfOders==0)
            g.drawString(vList.get(vListLastIndex).x + ", " + vList.get(vListLastIndex).y + "cID:"+ vList.get(vListLastIndex).customerId, vList.get(vListLastIndex).x, vList.get(vListLastIndex).y);
        else
            g.drawString(vList.get(vListLastIndex).x + ", " + vList.get(vListLastIndex).y + "cID:"+ vList.get(vListLastIndex).customerId+"#orders:"+ vList.get(vListLastIndex).numberOfOders+   ")", vList.get(vListLastIndex).x, vList.get(vListLastIndex).y);

        if(vList.get(vListLastIndex).numberOfOders>0)
            g.setColor(Color.MAGENTA);

        g.fillOval(vList.get(vListLastIndex).x,vList.get(vListLastIndex).y,5,5);
        g.drawOval(vList.get(vListLastIndex).x,vList.get(vListLastIndex).y,5,5);
       // g.drawRect (10, 10, 200, 200);
    }
}


