#include <iostream>


class Vehicle {
private:
    int wheels_;

public:
    Vehicle(int wheels) : wheels_(wheels) {};
    int get_wheels() {
        return wheels_;
    }
};

template <typename Result, typename Cls, typename... Args, typename Obj>
    Route::Handler bind(Result (Cls::*func)(Args...), std::shared_ptr<Obj> objPtr) {

    return [=](const Rest::Request &request, Http::ResponseWriter response) {
        (objPtr.get()->*func)(request, std::move(response));
        return Route::Result::Ok;
    };
}

int main() {
    using namespace std;

    cout << "Hello" << endl;

    Vehicle v(4);


    int i = 3;
    int j = 5;

    auto f1 = [i, &j] { return i + j; };
    cout << f1() << endl;

    i = 22;
    j = 44;

    cout << f1() << endl;

    
    int time2plus3 = [](int x) {
        return [](int y) {
            return y * 2;
        }(x) + 3;
    }(5);

    cout << time2plus3 << endl;

    return 0;

}
