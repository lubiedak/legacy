using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace Grapher
{
    public partial class Rysownik : Form
    {
        Dictionary<string, Graph> graphs;
        Bitmap canvas;
        Graphics g;
        GraphRenderer gr;

        public Rysownik()
        {
            InitializeComponent();
            canvas = new Bitmap(pictureBox1.ClientSize.Width, pictureBox1.ClientSize.Height);
            g = Graphics.FromImage(canvas);
        }

        private void selectFolderToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog fbd = new FolderBrowserDialog();
            DialogResult result = fbd.ShowDialog();
            canvas = new Bitmap(pictureBox1.ClientSize.Width, pictureBox1.ClientSize.Height);
            g = Graphics.FromImage(canvas);
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
            gr = new GraphRenderer();

            string[] files = Directory.GetFiles(fbd.SelectedPath);

            CreateGraphs(files);
            AddToList();
        }

        private void CreateGraphs(string[] files)
        {
            graphs = new Dictionary<string, Graph>();

            foreach (string file in files)
            {
                GraphReader gr = new GraphReader(file);
                graphs.Add(Path.GetFileNameWithoutExtension(file), gr.ConstructGraph());
            }
        }

        private void AddToList()
        {
            graphList.Items.Clear();
            graphList2.Items.Clear();
            foreach (String name in graphs.Keys)
            {
                graphList.Items.Add(name);
                graphList2.Items.Add(name);
            }
        }

        private void graphList_SelectedIndexChanged(object sender, EventArgs e)
        {
            printGraphBase(graphList.SelectedItem.ToString());
        }

        private void graphList2_SelectedIndexChanged(object sender, EventArgs e)
        {
            printGraphTree(graphList2.SelectedItem.ToString());
        }

        private void printGraphBase(string key)
        {
            g.Clear(Color.White);
            gr.Print(graphs[key], g);
            pictureBox1.Invalidate();
        }

        private void printGraphTree(string key)
        {
            gr.Print(graphs[key], g, true);
            pictureBox1.Invalidate();
            canvas.Save((graphList2.SelectedItem.ToString() == "" ? graphList.SelectedItem.ToString() : graphList2.SelectedItem.ToString()) + ".png", System.Drawing.Imaging.ImageFormat.Png);
        }

        private void pictureBox1_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.DrawImage(canvas, 0, 0, canvas.Width, canvas.Height);
        }

        private Image cropImage(Image img, Rectangle cropArea)
        {
            Bitmap bmpImage = new Bitmap(img);
            return bmpImage.Clone(cropArea, bmpImage.PixelFormat);
        }
    }
}
