#include <iostream>
#include <string>
#include <unordered_map>

using namespace std;

struct Element {
    int value;
    int team;
    Element* next = NULL;
};

int main() {
    Element temp;
    string line;
    int num_teams, team_size, value;
    Element* last_occ[1000];
    unordered_map<int, Element> elements;

    int scenario = 1;
    while(true) {
        cin >> num_teams;
        if(num_teams == 0)
            return 0;
        for(int i = 0; i < num_teams; i++) {
            cin >> team_size;
            for(int j = 0; j < team_size; j++) {
                cin >> value;
                temp.value = value;
                temp.team = i;
                elements[value] = temp;
            }
            last_occ[i] = NULL;
        }
        
        cout << "Scenario #" << scenario << endl;
        scenario++;

        Element* head = NULL;
        Element* tail = NULL;
        while(true) {
            cin >> line;
            if(line == "STOP") {
                break;
            }
            else if(line == "ENQUEUE") {
                cin >> value;
                Element &curr = elements[value];
                int team = curr.team;

                if(head == NULL && tail == NULL) {
                    head = &curr;
                    tail = &curr;
                    head->next = tail;
                    tail->next = NULL;
                }
                else {
                    if(last_occ[team] != NULL) {
                        Element *insert_point = last_occ[team];
                        curr.next = insert_point->next;
                        if(tail == insert_point) {
                            tail = &curr;
                        }
                        insert_point->next = &curr;
                    }
                    else {
                        tail->next = &curr;
                        tail = &curr;
                    }
                }
                last_occ[team] = &curr;    
            }
            else {
                cout << head->value << endl;
                if(last_occ[head->team] == head) {
                    last_occ[head->team] = NULL;
                }

                if(head == tail) {
                    head = NULL;
                    tail = NULL;
                }
                else {
                    head = head->next;
                }
            }
        }
        cout << endl;
    }
    return 0;
}
