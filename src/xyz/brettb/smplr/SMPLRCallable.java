package xyz.brettb.smplr;

import java.util.List;

interface SMPLRCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
