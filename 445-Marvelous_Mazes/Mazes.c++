#include <iostream>
#include <string>

using namespace std;

void translate_line(string line) {
    for(unsigned int i = 0; i < line.size(); i++) {
        if(line[i] == '!')
            cout << endl;
        else {
            int num_times = 0;
            while((int)(line[i]) >= 48 && (int)(line[i]) <= 57) {
                num_times += (int)(line[i]) - 48;
                i++;
            }
            char to_print = line[i];
            if(to_print == 'b')
                to_print = ' ';
            for(int j = 0; j < num_times; j++)
                cout << to_print;
        }
    }
    cout << endl;
}

int main() {
    string line;
    while(cin) {
        getline(cin, line);
        if(line.length() == 0 && cin.eof() != 1)
            cout << endl;
        else if(line.length() > 0) {
            translate_line(line);
        }
    }
    return 0;
}
