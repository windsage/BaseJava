package com.jeff.base.graph;

/**
 * 1.构建矩阵
 * 2.深度搜索
 * 3.广度搜索
 * 4.Dijkstra算法找出最短路径
 * 5.Floyd算法找出最短路径
 * 6.Kruskal算法找出最小生成树
 * 7.Prim算法找出最小生成树
 */
public class MatrixUDG {

    private static int INF = Integer.MAX_VALUE;

    /**
     * 边的构造
     */
    private static class EData {
        char start, end;
        int weight;

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    /***************************以上都是内部类******************************/
    private char[] mVexs;
    private int[][] mMatrix;
    private int mEdgeNum;

    /**
     * 构造函数，生成邻接矩阵
     *
     * @param vexs
     * @param matrix
     */
    private MatrixUDG(char[] vexs, int[][] matrix) {
        //赋值字符数组
        int length = vexs.length;
        mVexs = new char[length];
        mMatrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            mVexs[i] = vexs[i];
            for (int j = 0; j < length; j++) {
                mMatrix[i][j] = matrix[i][j];
            }
        }
        mEdgeNum = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (mMatrix[i][j] != 0 && mMatrix[i][j] != INF)
                    mEdgeNum++;
            }
        }
    }

    /**
     * 深度搜索主方法
     */
    private void DFS() {
        int length = mVexs.length;
        System.out.print("深度搜索顺序是：");
        boolean[] visited = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
        System.out.println();
    }

    /**
     * 深度搜索递归方法
     *
     * @param index
     * @param visited
     */
    private void DFS(int index, boolean[] visited) {
        visited[index] = true;
        System.out.printf("%c ", mVexs[index]);
        for (int i = firstVisited(index); i >= 0; i = nextVisited(index, i)) {
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
    }

    /**
     * 查找index顶点第一个访问的顶点
     *
     * @param index
     * @return
     */
    private int firstVisited(int index) {
        for (int i = 0; i < mVexs.length; i++) {
            if (mMatrix[index][i] != 0 && mMatrix[index][i] != INF) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找index顶点访问current顶点之后需要访问的顶点
     *
     * @param index
     * @param current
     * @return
     */
    private int nextVisited(int index, int current) {
        for (int i = current + 1; i < mVexs.length; i++) {
            if (mMatrix[index][i] != 0 && mMatrix[index][i] != INF)
                return i;
        }
        return -1;
    }


    /**
     * 广度搜索
     */
    private void BFS() {
        System.out.print("广度搜索顺序是：");
        int head = 0, rear = 0;
        int[] queue = new int[mVexs.length];
        boolean[] visited = new boolean[mVexs.length];
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", mVexs[i]);
                queue[rear++] = i;
            }
            while (head != rear) {
                int j = queue[head++];
                for (int k = firstVisited(j); k >= 0; k = nextVisited(j, k)) {
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.printf("%c ", mVexs[k]);
                        queue[rear++] = k;
                    }
                }
            }
        }
        System.out.println();
    }


    /**
     * Kruskal算法求最小生成树
     */
    private void kruskal() {
        int index = 0;
        //这是最小生成树
        EData[] tree = new EData[mEdgeNum];
        //
        int[] p = new int[mVexs.length];
        EData[] edges = sortEdges(getEdges());
        for (EData edge : edges) {
            int start = getPosition(edge.start);
            int end = getPosition(edge.end);
            int m = getEnd(start, p);
            int n = getEnd(end, p);
            if (m != n) {
                p[m] = n;
                tree[index++] = edge;
            }
        }
        //输出结果
        int weight = 0;
        for (int i = 0; i < index; i++) {
            weight += tree[i].weight;
        }
        System.out.println("weight = " + weight);
        for (int i = 0; i < index; i++) {
            System.out.printf("%c,%c ", tree[i].start, tree[i].end);
        }
        System.out.print("\n");
    }

    private int getEnd(int index, int[] p) {
        while (p[index] != 0)
            index = p[index];
        return index;
    }

    /**
     * 获取字符在字符数组中的索引
     *
     * @param ch
     * @return
     */
    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.length; i++) {
            if (ch == mVexs[i])
                return i;
        }
        return -1;
    }

    /**
     * kruskal算法找出所有的边
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[mEdgeNum];
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = i + 1; j < mVexs.length; j++) {
                if (mMatrix[i][j] != 0 && mMatrix[i][j] != INF) {
                    edges[index++] = new EData(mVexs[i], mVexs[j], mMatrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * Kruskal算法根据权重从小到大排序
     *
     * @return
     */
    private EData[] sortEdges(EData[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (array[j].weight > array[j + 1].weight) {
                    EData edge = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = edge;
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
        return array;
    }


    /**
     * prime算法求最小生成树
     *
     * @param start
     */
    private void prime(int start) {
        char[] prims = new char[mVexs.length];
        int[] weights = new int[mVexs.length];
        int index = 0;
        for (int i = 0; i < mVexs.length; i++) {
            weights[i] = mMatrix[start][i];
        }
        weights[start] = 0;
        prims[index++] = mVexs[start];

        for (int i = 0; i < mVexs.length; i++) {
            if (start == i)
                continue;
            int k = 0, min = INF;
            for (int j = 0; j < mVexs.length; j++) {
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
            }
            prims[index++] = mVexs[k];
            weights[k] = 0;
            for (int j = 0; j < mVexs.length; j++) {
                if (weights[j] != 0 && mMatrix[k][j] < weights[j]) {
                    weights[j] = mMatrix[k][j];
                }
            }
        }
        int sum = 0;
        for (int i = 1; i < index; i++) {
            int min = INF;
            int n = getPosition(prims[i]);
            for (int j = 0; j < i; j++) {
                int m = getPosition(prims[j]);
                if (mMatrix[m][n] < min)
                    min = mMatrix[m][n];
            }
            sum = sum + min;
        }
        System.out.println("Prim算法求得最小生成树的长度是：" + sum);
    }

    /**
     * dijkstra算法求顶点vs到其余各顶点的最短路径
     *
     * @param vs
     */
    private void dijkstra(int vs) {
        boolean[] flag = new boolean[mVexs.length];
        int[] dist = new int[mVexs.length];
        int[] prev = new int[mVexs.length];
//        int index = 0;
        for (int i = 0; i < mVexs.length; i++) {
            dist[i] = mMatrix[vs][i];
        }
        //初始化
        flag[vs] = true;
        dist[vs] = 0;
        for (int i = 1; i < mVexs.length; i++) {
            //找出最小值
            int k = 0, min = INF;
            for (int j = 0; j < mVexs.length; j++) {
                if (!flag[j] && dist[j] < min) {
                    k = j;
                    min = dist[j];
                }
            }
            //更新flag标记
            flag[k] = true;
            //更新dist数组
            for (int j = 0; j < mVexs.length; j++) {
                int tmp = mMatrix[k][j] == INF ? INF : (min + mMatrix[k][j]);
                if (!flag[j] && dist[j] > tmp) {
                    dist[j] = tmp;
                    prev[j] = k;
                }
            }
        }
        System.out.print("Dijkstra算法求得" + mVexs[vs] + "顶点到");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(mVexs[i] + "顶点的距离是" + dist[i]);
        }

    }

    /**
     * Floyd算法求最短路径
     */
    private void floyd() {
        int[][] path = new int[mVexs.length][mVexs.length];
        int[][] dist = new int[mVexs.length][mVexs.length];

        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                dist[i][j] = mMatrix[i][j];
                path[i][j] = j;
            }
        }

        for (int k = 0; k < mVexs.length; k++) {
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {
                    int tmp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] > tmp) {
                        dist[i][j] = tmp;
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        System.out.printf("floyd: \n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%2d  ", dist[i][j]);
            System.out.printf("\n");
        }
    }


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        MatrixUDG udg = new MatrixUDG(vexs, matrix);
        udg.DFS();
        udg.BFS();
        udg.kruskal();
        udg.prime(1);
        udg.dijkstra(0);
        udg.floyd();
    }
}
