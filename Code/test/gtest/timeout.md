
## [How to timeout tests in gtest](http://antonlipov.blogspot.com/2015/08/how-to-timeout-tests-in-gtest.html)

If you want to limit the time to run the test, you can use the following macro:

```c
#include <future>
#define TEST_TIMEOUT_BEGIN   std::promise<bool> promisedFinished; \
                              auto futureResult = promisedFinished.get_future(); \
                              std::thread([](std::promise<bool>& finished) {

#define TEST_TIMEOUT_FAIL_END(X)  finished.set_value(true); \
                                   }, std::ref(promisedFinished)).detach(); \
                                   EXPECT_TRUE(futureResult.wait_for(std::chrono::milliseconds(X)) != std::future_status::timeout);

#define TEST_TIMEOUT_SUCCESS_END(X)  finished.set_value(true); \
                                      }, std::ref(promisedFinished)).detach(); \
                                      EXPECT_FALSE(futureResult.wait_for(std::chrono::milliseconds(X)) != std::future_status::timeout);
```

Example:
```c
TEST(some_unit, LongCalculationTimeout)
{
  TEST_TIMEOUT_BEGIN
    EXPECT_EQ(10, long_calculation_function());
  TEST_TIMEOUT_FAIL_END(1000)
}
```


Test will not pass and will be aborted, If execution of long_calculation_function() takes more time than 1000 ms.

In rare cases, we want to test that the function will take at least some time. In that case you can use TEST_TIMEOUT_SUCCESS_END instead of TEST_TIMEOUT_FAIL_END. Test will run for a specified time and if the code is still running after this time, test will pass and program starts next test.

### Comments
**Geoffrey HunterJune 12, 2017 at 2:29 PM**
If you add a "&" to the lambda in the TEST_TIMEOUT_BEGIN macro, you can capture (and use) local variables such as "this" in-between the BEGIN and END macros.

e.g.
```c
#define TEST_TIMEOUT_BEGIN std::promise promisedFinished; \
auto futureResult = promisedFinished.get_future(); \
std::thread([&](std::promise& finished) {
```
