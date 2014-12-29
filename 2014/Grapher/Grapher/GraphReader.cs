using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


namespace Grapher
{
    class GraphReader
    {
        bool wages_;
        string path_;

        public GraphReader(String path)
        {
            wages_ = false;
            path_ = path;
        }

        public Graph ConstructGraph()
        {
            List<string> file_data = new List<string> (File.ReadAllLines(path_));
            if (file_data.Count > 0)
            {
                wages_ = Convert.ToBoolean(file_data[0]);

                file_data.RemoveAt(0);
            }
            Graph g = new Graph(wages_);

            foreach (string line in file_data)
            {
                g.AddNode(ParseNode(line));
            }

            foreach (string line in file_data)
            {
                ParseLines(g, line);
            }
            return g;
        }

        Node ParseNode(string line) //name, x, y, nam1, wage1, nam2, wage2,
        {
            string[] data = line.Split(',');
            return new Node(data[0], Convert.ToInt32(data[1]), Convert.ToInt32(data[2]));
        }

        void ParseLines(Graph g, string line)
        {
            string[] data = line.Split(',');

            for (int i = 3; i < data.Count(); i += wages_ ? 2 : 1) //omijanie wag
            {
                g.AddLine(data[0], data[i], wages_ ? Convert.ToInt32(data[i + 1]) : 0);
            }
        }
    }
}
