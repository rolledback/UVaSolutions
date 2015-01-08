#include <iostream>
#include <sstream>

using namespace std;

int main() {
    string line;
    int m_size;

    while(true) {
        bool corrupt = false;
        getline(cin, line);
        stringstream(line) >> m_size;
        if(m_size == 0)
            return 0;

        int odd_row = -1;
        int odd_col = -1;
        int *col_sums = new int[m_size];
        fill(col_sums, col_sums + m_size, 0);

        for(int i = 0; i < m_size; i++) {
            int sum = 0;
            getline(cin, line);
            for(int j = 0; j < m_size; j++) {
                int value = (int)(line[j * 2]) - 48;
                sum += value;
                col_sums[j] += value;
            }
            if(sum % 2 == 1) {
                if(odd_row != -1) {
                    corrupt = true;
                }
                odd_row = i + 1;
            }
        }
        if(corrupt) {
            cout << "Corrupt" << endl;
            delete[] col_sums;
            continue;
        }

        for(int c = 0; c < m_size; c++) {
            if(col_sums[c] % 2 == 1) {
                if(odd_col != -1) {
                    corrupt = true;
                    break;
                }
                odd_col = c + 1;
            }
        }
        if(corrupt) {
            cout << "Corrupt" << endl;
            delete[] col_sums;
            continue;
        }
        
        if(odd_row != -1 && odd_col != -1)
            cout << "Change bit (" << odd_row << "," << odd_col << ")" << endl;
        else if((odd_row != -1 && odd_col == -1) || (odd_row == -1 && odd_col != -1))
            cout << "Corrupt" << endl;
        else
            cout << "OK" << endl;
        delete[] col_sums;
    }

    return 0;
}

