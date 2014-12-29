using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace Grapher
{
    class GraphRenderer
    {
        float rescale;
        int r;
        int line_size;
        public GraphRenderer()
        {
            rescale = 2;
            r = 11;
            line_size = 2;
        }

        public void setScale(int scale) { rescale = 0.2f * scale; }
        public void setCircleSize(int size) { r = size; }
        public void setLineSize(int size) { line_size = size; }


        public void Print(Graph graph, Graphics g, bool special = false)
        {
            Pen p;
            SolidBrush br, fontcolor;
            Font f, fl;
            if (special)
            {
                p = new Pen(Color.Red, line_size);

                br = new SolidBrush(Color.White);
                fontcolor = new SolidBrush(Color.Red);
                f = new Font("Arial", 12, FontStyle.Italic | FontStyle.Bold);
                fl = new Font("Arial", 11, FontStyle.Bold);
            }
            else
            {
                p = new Pen(Color.Black, 1);
                br = new SolidBrush(Color.White);
                fontcolor = new SolidBrush(Color.Black);
                f = new Font("Arial", 12, FontStyle.Italic);
                fl = new Font("Arial", 11 );
            }
            List<Line> lines = graph.GetLines();

            if (graph.HasWages())
            {
                foreach (Line line in lines)
                {
                    float ax = rescale * line.a_.x_;
                    float bx = rescale * line.b_.x_;
                    float ay = rescale * line.a_.y_;
                    float by = rescale * line.b_.y_;
                    int size = 16;
                    float middx = (ax+bx)/2;
                    float middy = (ay + by)/2;

                    g.DrawLine(p, ax, ay, bx, by);
                    g.FillEllipse(br, middx - size / 2, middy - size / 2, size, size);
                    SizeF s = g.MeasureString(line.wage_.ToString(), fl);
                    g.DrawString(line.wage_.ToString(), fl, fontcolor, middx - s.Width / 2, middy - s.Height / 2 +1);
                }
            }
            else
            {
                foreach (Line line in lines)
                {
                    g.DrawLine(p, rescale * line.a_.x_, rescale * line.a_.y_, rescale * line.b_.x_, rescale * line.b_.y_);
                }
            }

            foreach (Node node in graph.GetNodes())
            {
                float x = node.x_ * rescale;
                float y = node.y_ * rescale;
                g.FillEllipse(br, x - r, y - r, 2 * r, 2 * r);
                g.DrawEllipse(p, x - r, y - r, 2 * r, 2 * r);
                SizeF s = g.MeasureString(node.name_, f);
                g.DrawString(node.name_, f, fontcolor, x - s.Width / 2, y - s.Height / 2);
            }
        }
    }
}
