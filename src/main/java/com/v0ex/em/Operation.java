package com.v0ex.em;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * @author bugcoder
 */
public enum Operation {

    PLUS,MINUS,TIMES,DIVIDE;

    private static final Map<String,Operation> stringToEnum = new HashedMap();

    static {
        for (Operation op : values()){
            stringToEnum.put(op.toString(),op);
        }
    }

    public static Operation fromString(String symbol){
        return stringToEnum.get(symbol);
    }

    double apply(double x,double y){
        switch (this){
            case PLUS: return x+y;
            case MINUS:return x-y;
            case TIMES:return x*y;
            case DIVIDE:return x/y;
        }
        throw new AssertionError("unknow op :"+this);
    }

    public static Operation inverse(Operation op){
        switch (op){
            case PLUS: return Operation.MINUS;
            case MINUS: return Operation.PLUS;
            case TIMES: return Operation.DIVIDE;
            case DIVIDE: return Operation.TIMES;
            default: throw new AssertionError("unknow op : "+op);
        }
    }

    public static void main(String[] args) {
        double x = 2;
        double y = 4;
        for (Operation op : Operation.values()){
            System.out.printf("%f %s %f = %f%n",x,op,y,op.apply(x,y));
        }

    }
}
