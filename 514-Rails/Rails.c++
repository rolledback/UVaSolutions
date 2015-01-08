#include <iostream>
#include <stack>
#include <queue>
#include <sstream>

using namespace std;

void cvrt_str(string line, int* dest, int len) {
    stringstream stream(line);
    for(int i = 0; i < len; i++)
        stream >> dest[i];
}

bool check_config(int* config, int len) {
    if(len <= 2)
        return true;
    queue<int> incoming;
    for(int i = 0; i < len; i++)
        incoming.push(i + 1);

    int x = 0;
    stack<int> station;
    while(incoming.size() > 0) {
        int car = incoming.front();
        station.push(car);
        incoming.pop();
        while(station.size() > 0 && station.top() == config[x]) {
            station.pop();
            x++;
        }
    }
    if(station.size() == 0)
        return true;
    return false;
}

int main() {
    int train_length;
    string line;
    while(true) {
        getline(cin, line);
        train_length = stoi(line);
        if(train_length == 0)
            break;
        while(true) {
            getline(cin, line);
            if(line == "0")
                break;
            int *config = new int[train_length];
            cvrt_str(line, config, train_length);
            if(check_config(config, train_length))
                cout << "Yes" << endl;
            else
                cout << "No" << endl;
        }
        cout << endl;
    }    
    return 0;
}
