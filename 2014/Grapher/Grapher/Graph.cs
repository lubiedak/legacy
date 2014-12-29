using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Grapher
{
    class Graph
    {
        List<Node> nodes_;
        List<Line> lines_;
        bool wages_;

        public Graph()
        {
            nodes_ = new List<Node>();
            lines_ = new List<Line>();
        }
        public Graph(bool wages)
        {
            wages_ = wages;
            nodes_ = new List<Node>();
            lines_ = new List<Line>();
        }

        public void AddNode(Node node)
        {
            nodes_.Add(node);
        }
        public void AddLine(string a, string b, int wage)
        {
            lines_.Add( new Line(   nodes_.Find(x => x.Name() == a), 
                                    nodes_.Find(x => x.Name() == b),
                                    wage));
        }
        public List<Node> GetNodes() { return nodes_; }
        public List<Line> GetLines() { return lines_; }

        public bool HasWages() { return wages_; }
    }

    class Node
    {
        public int x_;
        public int y_;
        public String name_;

        public Node() { }
        public Node(string name)
        {
            name_ = name;
        }
        public Node(string name, int x, int y)
        {
            name_ = name;
            x_ = x;
            y_ = y;
        }

        public string Name() { return name_; }

        
    }

    class Line
    {
        public Node a_;
        public Node b_;
        public int wage_;

        public Line(Node a, Node b, int wage)
        {
            a_ = a;
            b_ = b;
            wage_ = wage;
        }
    }
}
