#include <iostream>
#include <string>
#include <queue>
#include <sstream>

using namespace std;

class Node {
    public:
        int neighbors[300];
        int num_neighbors;
        int id;

        Node(int id = 0) {
            this->id = id;
            this->num_neighbors = 0;
        }

        void add_neighbor(int nId) {
            neighbors[num_neighbors] = nId;
            num_neighbors++;
        }

        void add_neighbors(int *n_ids, int size) {
            for(int i = 0; i < size; i++) {
                add_neighbor(n_ids[i]);
            }
        }
};

int decode(string line, int *n_ids) {
    unsigned int pos;
    if((pos = line.find("-")) == line.size())
        return 0;
    else
        line = line.substr(pos + 1);
    stringstream stream(line);
    string tmp;
    int count = 0;
    while(getline(stream, tmp, ',')) {
        n_ids[count] = atoi(tmp.c_str());
        count++;
    }
    return count;
}

int* find_path(Node *network, int start, int goal) {
    queue<int> q;
    bool visited[300] = {false};
    int disc_by[300];

    q.push(start);
    visited[start - 1] = true;

    while(q.size() > 0) {
        int curr_id = q.front();
        q.pop();
        if(curr_id == goal)
            return disc_by;
        else {
            int *neighbors = network[curr_id - 1].neighbors;
            int num_neighbors = network[curr_id - 1].num_neighbors;
            for(int n = 0; n < num_neighbors; n++) {
                if(!visited[neighbors[n] - 1]) {
                    disc_by[neighbors[n] - 1] = curr_id;
                    visited[neighbors[n] - 1] = true;
                    q.push(neighbors[n]);
                }
            }
        }
    }
    return NULL;
}

int main() {
    int num_nodes, num_cases;
    while(scanf("%d", &num_nodes) != EOF) {
        cout << "-----" << endl;
        Node network[300];
        string line;

        for(int i = 0; i < num_nodes; i++) {
            int n_ids[300];
            network[i].id = i + 1;
            cin >> line;
            int num_n = decode(line, n_ids);
            network[i].add_neighbors(n_ids, num_n);
        }

        scanf("%d", &num_cases);
        for(int i = 0; i < num_cases; i++) {
            int start, goal;
            scanf("%d %d", &start, &goal);
            int *path = find_path(network, start, goal);
            if(path == NULL)
                cout << "connection impossible" << endl;
            else {
                int curr = goal;
                string output = "";
                while(curr != start) {
                    output = " " + to_string(curr) + output;
                    curr = path[curr - 1];
                }
                output = to_string(start) + output;
                cout << output << endl;
            }
        }
    }

    return 0;
}
