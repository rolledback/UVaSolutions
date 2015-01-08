#include <iostream>

using namespace std;

int find_min_swaps(int *train, int length) {
    int numSwaps = 0;
    for(int i = 0; i < length - 1; i++) {
        for(int j = i + 1; j < length; j++)
            if(train[j] < train[i])
                numSwaps++;
    }
    return numSwaps;
}

int main() {
    string line;
    int num_cases;

    cin >> num_cases;
    for(int i = 0; i < num_cases; i++) {
        int num_cars;
        cin >> num_cars;
        int *train = new int[num_cars];
        for(int j = 0; j < num_cars; j++) {
            cin >> train[j];
        }
        int optimal = find_min_swaps(train, num_cars);
        cout << "Optimal train swapping takes " << optimal << " swaps." << endl;
    }
    return 0;
}
