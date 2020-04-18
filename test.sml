# A random script written in SMPLR
# Runs a bit of fibonacci
func fib(n) {
  if (n < 2) return n;
  return fib(n - 1) + fib(n - 2);
}

var before = clock();
for (var i is 0; i <= 20; i is i + 1) {
    println i + ". " + fib(i);
}
var after = clock();
println after - before;
