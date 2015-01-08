#include <iostream>

using namespace std;

bool on_left = true;

string replace_quotes(string line) {
    unsigned int i;
    string formatted_line = "";

    for(i = 0; i < line.size(); i++) {
        char curr = line[i];
        if(curr == '"') {
            if(on_left)
                formatted_line += "``";
            else
                formatted_line +="''";
            on_left = !on_left;
        }
        else
            formatted_line += curr;
    }
    return formatted_line;
}

int main() {
    istream& input = cin;
    ostream& output = cout;
    string line;

    while(input) {
        getline(input, line);
        output << replace_quotes(line);
        if(input)
            output << endl;
    }

    return 0;
}

